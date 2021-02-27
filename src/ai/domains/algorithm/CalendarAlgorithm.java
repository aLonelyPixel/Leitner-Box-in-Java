package ai.domains.algorithm;

/**
 * Initilises an interface for the CalendarAlgorithm
 * 
 * Basically, the higher the level, the longer the time between its
 * revisions. For example, Level 1 is going to be revised every day,
 * Level 2 every 2 days, Level 3 every 4 days, Level 4 every 5 days.
 * We can see that the pattern is the number of days separating revisions
 * are powers of 2. We can then say that the number of days separating
 * revisions of Level 5 is 16 days, Level 6 is 32, etc.
 * 
 * More generally, Cards of N Levels are revised every 2^(N-1) days.
 * 
 * Also the duration of cycle of a Box of N Levels is 2^(N-1) days,
 * since the cycle restarts once the session number (called currDay
 * in the Learning Schedule) reaches the number of interval days of 
 * the maximum Level. For example, for a box of 4 Levels, the cycle
 * will restart every 8 days since 2^(4-1) = 8. For a Box of 7 Levels 
 * the cycle will restart every 64 days since 2^(7-1) = 64.
 * 
 * Finally, a formula allows to find which Levels are to be studied on
 * a day. More generally, let s be a session, n a Level and f it's revisions
 * frequency (how many days between each revision): n is to be revised
 * during s if (s-(f/2))%f = 0. This formula will be further explained
 * in the Javadoc attached to the getLevelsOfDay method, in the
 * CalendarAlgorithmComputationnal class.
 * 
 * @author Andrea Dal Molin
 *
 */
public interface CalendarAlgorithm {

	/**
	 * Template for the method that will get the number of Levels
	 */
	public int getNumberOfLevels();
	
	/**
	 * Template for the method that will contain the algorithm
	 * to find what Levels have to be revised based on what day
	 * it is
	 * @return an Array that will contain the Levels to be revised
	 */
	public int[] getLevelsOfDay(final int day);
}
