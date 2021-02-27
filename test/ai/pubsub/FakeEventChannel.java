package ai.pubsub;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import ai.pubsub.EventChannel;
import ai.pubsub.RevisionStatistic;
import ai.statistic.StatisticEvent;

public class FakeEventChannel implements EventChannel {

	private List<StatisticEvent> datas;
	
	public FakeEventChannel() {
		datas = new ArrayList<StatisticEvent>();
	}
	
	@Override
	public void publish(StatisticEvent lsd) {
		datas.add(lsd);
	}

	@Override
	public void subscribe(RevisionStatistic statistic) {

	}
	
	public void verifyPublished(StatisticEvent...leitnerStatisticDatas) {
		assertArrayEquals(leitnerStatisticDatas, datas.toArray());
	}

	public void verifyNumberPublished(int i) {
		assertEquals(i, datas.size());
	}

}
