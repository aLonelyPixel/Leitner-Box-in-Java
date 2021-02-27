package ai.statistic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ai.domains.Card;

class AverageCardsRevisionCountTest {

	@Test
	void averageZeroWhenNoRevisions() {
		AverageCardsRevisionCount av = new AverageCardsRevisionCount();
		
		assertEquals(0,av.average());
	}

	@Test
	void AverageWhenAllCardsAreRevisedOnce() {
		AverageCardsRevisionCount av = new AverageCardsRevisionCount();
		
		StatisticEvent st = new StatisticEvent(new Card("a",""), 2, 1);
		StatisticEvent st2 = new StatisticEvent(new Card("b",""), 2, 1);
		StatisticEvent st3 = new StatisticEvent(new Card("c",""), 2, 1);
		av.update(st);
		av.update(st2);
		av.update(st3);
		
		assertEquals(1 ,av.average());
	}
	
	@Test
	void AverageWhenDifferentRevisionsForEachCard() {
		AverageCardsRevisionCount av = new AverageCardsRevisionCount();
		
		StatisticEvent st = new StatisticEvent(new Card("a",""), 2, 1);
		StatisticEvent st2 = new StatisticEvent(new Card("b",""), 2, 1);
		StatisticEvent st3 = new StatisticEvent(new Card("c",""), 2, 1);
		av.update(st);
		av.update(st);
		av.update(st2);
		av.update(st3);
		av.update(st3);
		av.update(st3);
		av.update(st3);
		
		assertEquals(2.33 ,av.average(),0.01);
	}
	
	@Test
	void addingNewCardToTheBox() {
		AverageCardsRevisionCount av = new AverageCardsRevisionCount();
		
		StatisticEvent st = new StatisticEvent(new Card("a",""), 1, 1);
		av.update(st);
		av.update(st);
		
		assertEquals(1.00 ,av.average(),0.01);
	}
}
