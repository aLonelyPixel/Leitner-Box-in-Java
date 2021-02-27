package ai.statistic;

import static org.junit.Assert.assertArrayEquals;
import java.util.ArrayList;
import java.util.List;
import ai.pubsub.RevisionStatistic;

public class FakeStatistic implements RevisionStatistic {

	private List<StatisticEvent> datas;
	
	public FakeStatistic() {
		datas = new ArrayList<StatisticEvent>();
	}
	
	@Override
	public void update(StatisticEvent lsd) {
		datas.add(lsd);
	}
	
	public void verifyReceiveUpdateDataFor(StatisticEvent... data) {
		assertArrayEquals(data, datas.toArray());
	}

}
