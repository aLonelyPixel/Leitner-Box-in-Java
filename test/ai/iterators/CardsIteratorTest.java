package ai.iterators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import ai.domains.Card;

class CardsIteratorTest {

	@Test
	void iteratorReturnsAccordingToFifo() {
		final Card firstCard = new Card("A", "A");
		final Card secondCard = new Card("B" , "B");
		final Queue<Card> cards = new LinkedList<Card>();
		cards.add(firstCard);
		cards.add(secondCard);
		final CardsIterator cardsIterator = new CardsIterator(cards);

		assertEquals(2, cards.size());
		assertTrue(cardsIterator.hasNext());
		assertEquals(firstCard, cardsIterator.next());
		assertTrue(cardsIterator.hasNext());
		assertEquals(secondCard, cardsIterator.next());
		assertFalse(cardsIterator.hasNext());
		assertEquals(null, cardsIterator.next());
		assertEquals(0, cards.size());
	}
	
	@Test
	void adding() {
		final Card firstCard = new Card("A", "A");
		final Card secondCard = new Card("B" , "B");
		final Card thirdCard = new Card("C" , "C");
		final Queue<Card> cards = new LinkedList<Card>();
		cards.add(firstCard);
		cards.add(secondCard);
		final CardsIterator cardsIterator = new CardsIterator(cards);
		
		cardsIterator.next();
		cardsIterator.next();
		cardsIterator.add(thirdCard);
		
		assertTrue(cardsIterator.hasNext());
		assertEquals(thirdCard, cardsIterator.next());
	}

}
