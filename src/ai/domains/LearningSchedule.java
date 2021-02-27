package ai.domains;

import ai.domains.algorithm.CalendarAlgorithm;
import ai.iterators.CardsIterator;

/**
 * This class creates a Learning Schedule (LS) which is going to use a Box and is 
 * going to have a startDay value to know which day the LS is going to start.
 * A CalendarAlgorithm will calculate what Levels need to be revised for the current day.
 * 
 * @author Andrea Dal Molin
 *
 */
public class LearningSchedule {

	private final Box box;
	private int currDay;
	private CalendarAlgorithm calendarAlgo;

	/**
	 * Constructor that takes a Box and a starDay as parameters to initialise
	 * the LS. Also uses a CalendarAlgorithm to calculate what Levels need to
	 * be revised for the current day.
	 * @param box the Box of the LS
	 * @param currDay the day the LS is starting at
	 * @param calendarAlgo the calendar that will determine what Levels have to be revised
	 * each day
	 */
	public LearningSchedule(final Box box, final int currDay, final CalendarAlgorithm calendarAlgo) {
		this.box = box;
		this.currDay = currDay;
		this.calendarAlgo = calendarAlgo;
	}

	/**
	 * Getter for the current day of the LS
	 * @return the currentDay
	 */
	public int getCurrentDay() {
		return currDay;
	}

	/**
	 * Setter for the calendar algorithm
	 */
	public void setCalendarAlgorithm(final CalendarAlgorithm calendarAlgorithm) {
		this.calendarAlgo = calendarAlgorithm;
	}

	/**
	 * Adds a Card to the Box
	 * 
	 * @param card, to be added
	 * @return true if the Card is successfully added
	 */
	public boolean add(final Card card) {
		return this.box.add(card);
	}

	/**
	 * Removes a Card from the Box
	 * 
	 * @param card, to be removes
	 * @return true if the Card is successfully removed
	 */
	public boolean remove(final Card card) {
		return this.box.remove(card);
	}

	/**
	 * Increases the current day value to skip to the next day.
	 * This will impact of the calendar algorithm and Levels to
	 * be revised on the current day
	 */
	public void nextDay() {
		this.currDay++;
	}

	/**
	 * Returns the box of the LS (using the Iterable box)
	 * 
	 * @return the box of the LS
	 */
	public Iterable<Card> getAllCards(){
		return this.box;
	}

	/**
	 * Returns a CardsIterator that contains all the Cards to be revised
	 * on the current day.
	 * @return, the CardsIterator containing the Cards to be revised on the current day
	 */
	public CardsIterator getCardsToRevise() {
		return this.box.getIteratorForLevels(calendarAlgo.getLevelsOfDay(currDay));
	}

	/**
	 * Uses the box internal method to move the Card to the next Level
	 * @return the Level number where the Card was moved in
	 */
	public int moveCardToNextLevel(final Card card, final boolean answerKnown) {
		return this.box.moveCardToNextLevel(card, answerKnown);
	}
	
	/**
	 * Returns true if the box isn't empty
	 * @return true if the Box can still be revised, otherwise false 
	 */
	public boolean isRevisable() {
		return !this.box.isEmpty();
	}
}
