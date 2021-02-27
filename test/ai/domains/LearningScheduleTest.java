package ai.domains;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import ai.domains.algorithm.CalendarAlgorithmComputationnal;
import ai.domains.algorithm.FakeCalendarAlgorithm;
import ai.iterators.CardsIterator;

class LearningScheduleTest {

	@Test
	void returnsFalseOnExistingCard() {
		final FakeCalendarAlgorithm calendar = new FakeCalendarAlgorithm(4);
		final Box box = new Box(3);
		final LearningSchedule ls = new LearningSchedule(box, 1, calendar);
		final Card firstCard = new Card("Cool card", "Yeah mate");
		final Card secondCard = new Card("another card" , "Nah");
		final Card thirdCard = new Card("testCard", "testing");
		ls.add(firstCard);
		ls.add(secondCard);
		
		assertFalse(ls.add(secondCard));
		assertTrue(ls.add(thirdCard));
	}

	@Test
	void checkCurrentDay() {
		final FakeCalendarAlgorithm calendar = new FakeCalendarAlgorithm(4);
		final Box box = new Box(3);
		final LearningSchedule ls = new LearningSchedule(box, 1, calendar);
		
		assertEquals(1, ls.getCurrentDay());
		ls.nextDay();
		assertEquals(2, ls.getCurrentDay());
	}
	
	@Test
	void checkCalendarAlgorithm() {
		final FakeCalendarAlgorithm calendar = new FakeCalendarAlgorithm(4);
		final FakeCalendarAlgorithm differentCalendar = new FakeCalendarAlgorithm(4);
		
		final Box box = new Box(3);
		final LearningSchedule ls = new LearningSchedule(box, 1, calendar);
		
		ls.setCalendarAlgorithm(differentCalendar);
	}
	
	@Test
	void checkCardsToBeRevisedOnDayOne() {
		final FakeCalendarAlgorithm calendar = new FakeCalendarAlgorithm(4);
		final Card firstCard = new Card("Cool card", "Yeah mate");
		final Card secondCard = new Card("another card" , "Nah");
		final Card thirdCard = new Card("testCard", "testing");
		final Box box = new Box(3);
		box.add(firstCard);
		box.add(secondCard);
		box.add(thirdCard);
		final LearningSchedule ls = new LearningSchedule(box, 1, calendar);
		final LinkedList<Card> cardsQueue = new LinkedList<Card>();
		cardsQueue.add(firstCard);
		cardsQueue.add(secondCard);
		cardsQueue.add(thirdCard);
		final CardsIterator differentCI = ls.getCardsToRevise();
		differentCI.next();
		
		assertTrue(differentCI.hasNext());
	}
	
	@Test
	void revisableBox() {
		final FakeCalendarAlgorithm calendar = new FakeCalendarAlgorithm(4);
		final Box box = new Box(3);
		final Card firstCard = new Card("Cool card", "Yeah mate");
		box.add(firstCard);
		final LearningSchedule ls = new LearningSchedule(box, 1, calendar);
		
		assertTrue(ls.isRevisable());
	}
	
	@Test
	void unrevisableBox() {
		final FakeCalendarAlgorithm calendar = new FakeCalendarAlgorithm(4);
		final Box box = new Box(3);
		final LearningSchedule ls = new LearningSchedule(box, 1, calendar);
		
		assertFalse(ls.isRevisable());
	}
	
	@Test
	void returnsDestinationLevelOnMoveCard() {
		final FakeCalendarAlgorithm calendar = new FakeCalendarAlgorithm(4);
		final Box box = new Box(4);
		final LearningSchedule ls = new LearningSchedule(box, 1, calendar);
		final Card firstCard = new Card("Cool card", "Yeah mate");
		ls.add(firstCard);
		
		assertEquals(2, ls.moveCardToNextLevel(firstCard, true));
		assertEquals(3, ls.moveCardToNextLevel(firstCard, true));
		assertEquals(1, ls.moveCardToNextLevel(firstCard, false));
		assertEquals(2, ls.moveCardToNextLevel(firstCard, true));
		assertEquals(3, ls.moveCardToNextLevel(firstCard, true));
		assertEquals(4, ls.moveCardToNextLevel(firstCard, true));
		assertEquals(-1, ls.moveCardToNextLevel(firstCard, true));
	}
}
