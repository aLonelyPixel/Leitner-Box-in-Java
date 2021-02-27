package ai.domains;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class LevelTest {

	@Test
	void knowsItsLevelNumber() {
		final Level level = new Level(3);
		
		assertEquals(3, level.getNumLevel());
	}

	@Test
	void containsKnowsWhichCardsAreInTheLevel() {
		final Card firstCard = new Card("A", "Yeah mate");
		final Card secondCard = new Card("B" , "Nah");
		final Card notNewCard = new Card("A", "different answer");
		final Level level = new Level(3);
		level.insert(firstCard);
		level.insert(secondCard);
		
		assertTrue(level.contains(firstCard));
		assertTrue(level.contains(secondCard));
		assertTrue(level.contains(notNewCard));
	}
	
	@Test
	void insertingNewCard() {
		final Card thirdCard = new Card("test" , "again");
		final Level level = new Level(3);
		level.insert(thirdCard);
		
		assertTrue(level.contains(thirdCard));
	}
	
	@Test
	void insertingExistingCardDoesNothing() {
		final Card firstCard = new Card("Cool card", "Yeah mate");
		final Card secondCard = new Card("Cool card" , "Nah");
		final Level level = new Level(3);
		level.insert(firstCard);
		level.insert(secondCard);
		level.insert(secondCard);
		
		assertTrue(level.contains(secondCard));
	}
	
	@Test
	void iteratorReturnsCorrectly() {
		final Card firstCard = new Card("Cool card", "Yeah mate");
		final Card secondCard = new Card("Cool card" , "Nah");
		final Card thirdCard = new Card("test" , "again");
		final Level level = new Level(3);
		level.insert(firstCard);
		level.insert(secondCard);
		level.insert(thirdCard);
		
		assertTrue(level.iterator().hasNext());
	}
	
	@Test
	void removeCardRemovesCorrectly() {
		final Card firstCard = new Card("Cool card", "Yeah mate");
		final Card secondCard = new Card("Cool card" , "Nah");
		final Card thirdCard = new Card("test" , "again");
		final Level level = new Level(3);
		level.insert(firstCard);
		level.insert(secondCard);
		level.insert(thirdCard);
		
		assertTrue(level.contains(thirdCard));
		
		level.remove(thirdCard);
		
		assertFalse(level.contains(thirdCard));
		assertTrue(level.contains(firstCard));
		assertTrue(level.contains(secondCard));
	}
	
	@Test
	void levelIsEmpty() {
		final Level level = new Level(3);
		
		assertTrue(level.isEmpty());
		
		final Level differentLevel = new Level(3);
		final Card firstCard = new Card("Cool card", "Yeah mate");
		differentLevel.insert(firstCard);
		
		assertFalse(differentLevel.isEmpty());
	}
}
