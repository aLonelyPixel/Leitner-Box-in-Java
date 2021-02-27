package ai.domains;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import ai.iterators.CardsIterator;

class BoxTest {

	@Test
	void knowsHowManyLevelsItHas() {
		final Box box = new Box(5);
		
		assertEquals(5, box.getAmountOfLevels());
	}

	@Test
	void setsMinimumTwoLevels() {
		final Box box = new Box(0);
		final Box box1 = new Box(1);
		
		assertEquals(2, box.getAmountOfLevels());
		assertEquals(2, box1.getAmountOfLevels());
	}
	
	@Test
	void addNewCardToLevel() {
		final Box box = new Box(3);
		final Card newCard = new Card("test?", "yes");
		
		assertTrue(box.add(newCard));
	}
	
	@Test
	void addExistingCardToLevel() {
		final Box box = new Box(3);
		final Card newCard = new Card("test?", "yes");
		box.add(newCard);
		
		assertFalse(box.add(newCard));
	}
	
	@Test
	void iteratorOnNullBox() {
		final Box box = new Box(2);
		Iterator<Card> it = box.getIteratorForLevel(1);
		
		assertFalse(it.hasNext());
	}

	@Test
	void returnsFalseOnAddingExistingCard() {
		final Box box = new Box(0);
		final Card firstCard = new Card("Cool card", "Yeah mate");
		final Card secondCard = new Card("another card" , "Nah");
		final Card thirdCard = new Card("testCard", "testing");
		box.add(firstCard);
		box.add(secondCard);
		
		assertFalse(box.add(secondCard));
		assertTrue(box.add(thirdCard));
	}
	
	@Test
	void removesCardCorrectly() {
		final Box box = new Box(5);
		final Card firstCard = new Card("Cool card", "Yeah mate");
		final Card secondCard = new Card("another card" , "Nah");
		final Card thirdCard = new Card("testCard", "testing");
		box.add(firstCard);
		box.add(secondCard);
		box.add(thirdCard);
		
		assertFalse(box.add(firstCard));
		box.remove(firstCard);
		assertTrue(box.add(firstCard));
	}
	
	@Test
	void iteratorReturnsCorrectLevel() {
		final Box box = new Box(5);
		final Card firstCard = new Card("Cool card", "Yeah mate");
		final Card secondCard = new Card("another card" , "Nah");
		final Card thirdCard = new Card("testCard", "testing");
		box.add(firstCard);
		box.add(secondCard);
		box.add(thirdCard);
		Iterator<Card> it = box.getIteratorForLevel(1);
		
		assertTrue(it.hasNext());
		it.next();
		assertTrue(it.hasNext());
		it.next();
		assertTrue(it.hasNext());
		it.next();
		assertFalse(it.hasNext());
	}
	
	@Test
	void iteratorForAllCards() {
		final Box box = new Box(5);
		final Card firstCard = new Card("Cool card", "Yeah mate");
		final Card secondCard = new Card("another card" , "Nah");
		final Card thirdCard = new Card("testCard", "testing");
		box.add(firstCard);
		box.add(secondCard);
		box.add(thirdCard);
		Iterator<Card> it = box.iterator();
		
		assertTrue(it.hasNext());
		it.next();
		assertTrue(it.hasNext());
		it.next();
		assertTrue(it.hasNext());
		it.next();
		assertFalse(it.hasNext());
	}
	
	@Test
	void iteratorForLevelsAskingFourLevels() {
		final Box box = new Box(5);
		final Card firstCard = new Card("A", "A");
		final Card secondCard = new Card("B" , "B");
		final Card thirdCard = new Card("C", "C");
		box.add(firstCard);
		box.add(secondCard);
		box.add(thirdCard);
		
		final CardsIterator ci = box.getIteratorForLevels(1,2,3,4);
		
		assertEquals(firstCard, ci.next());
		assertTrue(ci.hasNext());
		assertEquals(secondCard, ci.next());
		assertTrue(ci.hasNext());
		assertEquals(thirdCard, ci.next());
		assertFalse(ci.hasNext());
	}
	
	@Test
	void iteratorForLevelsAskingOneLevel() {
		final Box box = new Box(5);
		final Card firstCard = new Card("A", "A");
		box.add(firstCard);
		
		final CardsIterator ci = box.getIteratorForLevels(1);
		
		assertEquals(firstCard, ci.next());
		assertFalse(ci.hasNext());
	}
	
	@Test
	void movingCardToLevelTwoThenBack() {
		final Box box = new Box(5);
		final Card firstCard = new Card("A", "A");
		box.add(firstCard);
		box.moveCardToNextLevel(firstCard, true);
		
		assertEquals(2, box.getLevelOfCard(firstCard));
		
		box.moveCardToNextLevel(firstCard, false);
		
		assertEquals(1, box.getLevelOfCard(firstCard));
	}
	
	@Test
	void movingCardReturnsDestinationLevel() {
		final Box box = new Box(5);
		final Card firstCard = new Card("A", "A");
		box.add(firstCard);
		box.moveCardToNextLevel(firstCard, true);
		
		assertEquals(3, box.moveCardToNextLevel(firstCard, true));
		assertEquals(4, box.moveCardToNextLevel(firstCard, true));
		assertEquals(5, box.moveCardToNextLevel(firstCard, true));
		assertEquals(-1, box.moveCardToNextLevel(firstCard, true));
		
		box.add(firstCard);
		box.moveCardToNextLevel(firstCard, true);
		
		assertEquals(1, box.moveCardToNextLevel(firstCard, false));
		assertEquals(2, box.moveCardToNextLevel(firstCard, true));
		assertEquals(1, box.moveCardToNextLevel(firstCard, false));
	}
	
	@Test
	void emptyBoxReturnsTrue() {
		final Box box = new Box(5);
		
		assertTrue(box.isEmpty());
	}
	
	@Test
	void notEmptyBoxReturnsFalse() {
		final Box box = new Box(5);
		final Card firstCard = new Card("A", "A");
		box.add(firstCard);
		
		assertFalse(box.isEmpty());
	}
}
