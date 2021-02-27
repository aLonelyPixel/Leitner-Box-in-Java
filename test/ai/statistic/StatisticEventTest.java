package ai.statistic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ai.domains.Card;

class StatisticEventTest {

	@Test
	void knowsCardLevelAndDay() {
		final StatisticEvent statistics = new StatisticEvent(new Card("a", "a"), 2, 1);
		
		assertEquals("a", statistics.getCard().getQuestion());
		assertEquals(2, statistics.getDestLevel());
		assertEquals(1, statistics.getDay());
	}

}
