package ai.statistic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ai.domains.Card;

class CardsInEachLevelCountTest {

	@Test
	void valuesForEachLevelMatchWithOneCard() {
		CardsInEachLevelCount cd = new CardsInEachLevelCount();
		
		final Card firstCard = new Card("a", "a");
		
		final StatisticEvent st = new StatisticEvent(firstCard, 1, 1);
		final StatisticEvent st1 = new StatisticEvent(firstCard, 2, 1);
		final StatisticEvent st2 = new StatisticEvent(firstCard, 3, 1);
		final StatisticEvent st3 = new StatisticEvent(firstCard, 3, 1);
		
		cd.update(st);
		cd.update(st1);
		cd.update(st2);
		cd.update(st3);

		assertArrayEquals(new int[]{1,1,2,0,0,0,0}, cd.returnCardValues(firstCard));
	}
}
