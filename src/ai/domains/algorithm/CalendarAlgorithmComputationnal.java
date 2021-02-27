package ai.domains.algorithm;

/**
 * This class implements CalendarAlgorithm and overrides its methods
 * to calculate who Levels have to be studied on a specific day.
 * 
 * @author Andrea Dal Molin
 *
 */
public class CalendarAlgorithmComputationnal implements CalendarAlgorithm{

	private final int levelNbr;
	
	/**
	 * Contructor that initialises the number of Levels in the Box so thay
	 * the Algorithm know how many Levels it has to go through
	 */
	public CalendarAlgorithmComputationnal(final int levelNbr) {
		this.levelNbr = levelNbr;
	}

	@Override
	/**
	 * Getter for the number of Levels
	 * @return the numberOfLevels
	 */
	public int getNumberOfLevels() {
		return levelNbr;
	}

	@Override
	/**
	 * Calculates what Levels have to be revised on a given day. This algorithm
	 * is the result of the formulas given on the documentation of the 
	 * CalendarAlgorithm interface. It is important to point out that a maximum 
	 * of two Levels can be studied for each revision session. This documentation 
	 * will provide further explanation on how this algorithm achieves the search 
	 * for the Levels.
	 * 
	 * -------------------------------------------------------------------------
	 * INPUT DATA
	 * 
	 * - @param final int day: this is the int value that will determine for what
	 * day the algorithm has to search Levels to study (IN). This parameter will
	 * not be changed so it can be used as final.
	 * 
	 * The getLevelsOfDay method is part of the CalendarAlgorithmComputationnal
	 * class and as such has access to the following attributes:
	 * 
	 * -int levelNbr: this integer value represents how many Levels are inside the
	 * Box and it will be used by this method (IN) to iterate through each Level 
	 * (explained in the "how" part).
	 * 
	 * -------------------------------------------------------------------------
	 * OUTPUT DATA
	 * 
	 * @return levelsOfDay, an integer array of variable length (1 or 2). The lenght
	 * will be 1 if only one Level has to be studied on that day, 2 if two Levels are
	 * due on that day (OUT).
	 * 
	 * Yet again, the getLevelsOfDay method is part of the CalendarAlgorithmComputationnal
	 * class and as such has access to the levelNbr attribute. This will only be used
	 * to iterate in a for loop and won't be modified.
	 * 
	 * -------------------------------------------------------------------------
	 * PRECONDITIONS (requires)
	 * 
	 * day != null
	 * day > 0
	 * levelNbr > 1 (although this is verified by the Box constructor)
	 * 
	 * -------------------------------------------------------------------------
	 * POSTCONDITIONS (ensures)
	 * 
	 * levelsOfDay != null
	 * levelsOfDay.length() > 0 && levelsOfDay.length() < 3
	 * levelsOfDay.length() = 1 || levelsOfDay.length() = 2
	 * levelsOfDay[0] > 0 && levelsOfDay[0] <= levelNbr
	 * levelsOfDay[1] = 1*
	 * 
	 * *(only if levelsOfDay.length() = 2)
	 * 
	 * -------------------------------------------------------------------------
	 * HOW THE ALGORITHM WORKS
	 * 
	 * In this phase, to simplify I will refer to the following abbreviations:
	 * 
	 * d --> for day, can also be interpreted as revision session
	 * f --> frequency, how many sessions between each revision
	 * 
	 * Looking at the frequency for the first 4 Levels we have:
	 * 
	 * Level 1: every 1 day
	 * Level 2: every 2 days
	 * Level 3: every 4 days
	 * Level 4: every 8 days
	 * 
	 * The frequency for each Level increments in powers of 2. So we can deduct
	 * that for Level 5 the frequency is 16, Level 6 is 32 and so on...
	 * So the f value for a Level n can be calculated by the following formula:
	 * 
	 * 									2^(N-1)
	 * 
	 * From now on, I will associate the value of this formula to the f value
	 * for any given n Level.
	 * 
	 * This algorithm will mark a Level as "to be revised" if the formula will
	 * give 0 as a value (if formula result = 0 then the Level has to be revised).
	 * Based on this we can say that because the result has to depend on the Level
	 * number, the formula will be using the frequency of an n Level. Of course
	 * it will also use the day value, because this is the main value that will
	 * determine if a Level has to be revised or not.
	 * 
	 * Starting with Level 1. This has to be revised every day, so the formula
	 * for Level 1 will always give 0, whatever the day. Since the %1 (modulo one)
	 * operation always gives 0 we can use this as a baseline for our formula.
	 * For example, for any given d and f = 1:
	 * 
	 * 									d%f = 0
	 * 
	 * This works for Level 1, but not for Level 2 for example. In fact, because
	 * Level 2 has f = 2, this will means it will works only when d is a multiple
	 * of 2. As we know, Level 2 is going to be revised every 2 days but starting from
	 * the first one. This means we will revise Level 2 every odd day (d=1, d=3, 
	 * d=5, etc). So a possible adaptation of the formula would be:
	 * 
	 * 								d-(f-1)%f = 0
	 * 
	 * This works because it will always subtract 1 to any day, making even days odd
	 * and viceversa. Example with d = 3:
	 * 
	 * 								3-(2-1)%2 = 
	 * 								(3-1)%2   =
	 * 									2%2   = 0
	 * 
	 * This would also work with the following formula:
	 * 
	 * 								(d-(f/2))%f = 0
	 * 
	 * Given the possibility between the two, we will test with the next Levels with both.
	 * I will refer to F1 for the first formula and F2 with the second one.
	 * Given Level 3 with f=4, if we use F1 d = 2 (which should give 0 since Level 3
	 * is supposed to be revised on day 2) :
	 * 
	 * 								(2-(4-1)%4 =
	 * 								(2-3)%4	   =
	 * 									-1%4   = 3
	 * 
	 * F1 doesn't work with Level 3, so we'll try F2:
	 * 
	 * 								(2-(4/2)%4 =
	 * 								(2-2)%4    =
	 * 									0%4    = 0
	 * 
	 * Since 0 % n = 0. Level 3 has f=4 so the next revision would be on day 6. For Level 3
	 * and d = 6 this will also return 0. What F2 is implicitly doing is subtracting half
	 * of any Level's frequency to the current day. This connects well to the starting
	 * day of any n Level. In fact, every Level has its very first revision when d = f/2.
	 * So for Level 2 with f=2 the starting day will be d = 2/2 = 1. For Level 3 with 
	 * f = 4 the starting day will be d = 4/2 = 2, for Level 4 with f = 8 the starting
	 * day will be d = 8/2 = 4.
	 * 
	 * Trying F2 with Level 4 on day 4 then:
	 * 
	 * 								(4-(8/2)%8 =
	 * 								(4-4)%8    =
	 * 									0%8    = 0
	 * 
	 * This formula then returns 0 when d = starting day of the Level + f.
	 * 
	 * To conclude, this is the formula that getLevelsOfDay will use: 
	 * 
	 * 								(d-(f/2))%f = 0
	 * 
	 * 
	 * Since the maximum number of Levels to be revised on and d day is 2, an integer
	 * array of lenght 2 will be initialised at the start.
	 * The first value of this array is initialised to 0, while the second one is set
	 * to 1, which will always be present. In case no Levels have to be studied, apart 
	 * from Level 1 which is revised every day, on a given day, getLevelsOfDay will 
	 * return an array of lenght 1 containing the integer 1, since it represents the 
	 * Level 1.
	 * The method will loop through each Level backwards, since the User will revise
	 * highest Level first, then Level 1 as last.
	 * A frequency value is calculated for each Level and then used in the formula
	 * explained previously. In case the formula returns 0, the currently examined
	 * Level will be placed in the array. Since a maximum of 2 Levels will be studied
	 * on any given day, there's no need to continue looping through the Levels. A
	 * break instructions takes care of that (it is not necessary but save ressources).
	 * The final return checks if the first value of the array is 0. This would mean
	 * only Level 1 has to be revised, so a size 1 array containing 1 has to be returned.
	 * 
	 * -------------------------------------------------------------------------
	 * THEORETICAL TIME COMPLEXITY (TTC)
	 * 
	 * Since the algorithm will use a for loop for as many Levels there are in the Box
	 * we can start saying the complexity is linear in the form of O(N) where n 
	 * corresponds to the number of Levels examined. Of course the break instruction
	 * will try to limit the complexity by stopping the loop once a Level is found,
	 * but the worst case is when no Levels (out of Level 1) are found. In fact, in 
	 * that case the for loop will run for each level never entering the if condition.
	 * The only variable impacting the TTC of getLevelsOfDay is the integer class 
	 * attribute levelNbr. In conclusion, the TTC of getLevelsOfDay is O(N) is the worst
	 * case
	 * 
	 * @param day what day the Levels have to be calculated for
	 * @return an Integer array of lenght 1 or 2, containing the Level number(s) that have
	 * to be revised
	 */
	public int[] getLevelsOfDay(final int day) {
		final int[] levelsOfDay = {0, 1};
		
		for (int level = this.levelNbr; level > 1; level--) {
			final int frequency = (int) Math.pow(2, (level-1));
			if (((day-(frequency/2))%frequency) == 0) {
				levelsOfDay[0] = level;
				break;
			}
		}
		return levelsOfDay[0] == 0 ? new int[] {1} : levelsOfDay;
	}

	/**
	 * Fabric method used for testing
	 * @param nbrLevel the numberOfLevels
	 * @return a CalendarAlgorithm
	 */
	public static CalendarAlgorithm Of(final int nbrLevel) {
		return new CalendarAlgorithmComputationnal(nbrLevel);
	}
}
