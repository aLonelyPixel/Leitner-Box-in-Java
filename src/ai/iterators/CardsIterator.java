package ai.iterators;

import java.util.Iterator;
import java.util.Queue;

import ai.domains.Card;

/**
 * Creates a CardsIterator that will allow to cycle through
 * the different Cards for each revision. Also specifies a
 * special add method that will be used when the User fails
 * to answer correctly to a Card's question. 
 * 
 * @author Andrea Dal Molin
 */
public class CardsIterator implements Iterator<Card>{

	private final Queue<Card> cards;

	/**
	 * A constructor that uses a Queue of Cards
	 */
	public CardsIterator(final Queue<Card> cards) {
		this.cards = cards;
	}

	@Override
	/**
	 * @return true if there's more cards to be processed
	 * in the Queue
	 */
	public boolean hasNext() {
		return !cards.isEmpty();
	}

	@Override
	/**
	 * @return the next Card using the Queue's specific poll
	 * method
	 */
	public Card next() {
		return cards.poll();
	}

	/**
	 * If the User fails to give the correct answer to the question
	 * the Card will be added on top of the Queue
	 * @param c the Card to be added
	 */
	public void add(final Card c) {
		cards.add(c);
	}
}
