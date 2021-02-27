package ai.statistic;

import ai.domains.Card;

/**
 * Creates a StatisticEvent object that is defined by a Card, a Level where it's been
 * moved, and the dya it happened.
 * 
 * @author Andrea Dal Molin
 *
 */
public class StatisticEvent {
	
	private final Card card;
	private final int destLevel;
	private final int day;

	/**
	 * Constructor that creates a StatisticEven object with a Card, a destination 
	 * Level for the Card and the day the Card has been moved
	 * @param card the card that moved
	 * @param destLevel the Level the Card moved into
	 * @param day the day the Card has been moved
	 */
	public StatisticEvent(final Card card, final int destLevel, final int day) {
		this.card = card;
		this.destLevel = destLevel;
		this.day = day;
	}

	/**
	 * Getter for the Card of the StatisticEvent
	 * @return the Card of the StatisticEvent
	 */
	public Card getCard() {
		return card;
	}

	/**
	 * Getter for the destination Level number of the StatisticEvent
	 * @return the destination Level number of the StatisticEvent
	 */
	public int getDestLevel() {
		return destLevel;
	}

	/**
	 * Getter for the day the Card moved
	 * @return the day the Card has been moved
	 */
	public int getDay() {
		return day;
	}
}
