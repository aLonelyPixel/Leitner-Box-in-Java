package ai.pubsub;

import java.util.HashSet;
import java.util.Set;

import ai.statistic.StatisticEvent;

/**
 * Implementation of the EventChannel. Subscribes the statistics to the Publisher
 * 
 * This class will store its subscribers in a Collection. Given the simplicity
 * of the operations added, I opted for a Set. The only operation we will need
 * to do with this collection is adding elements. This gives a lot of flexibility
 * with choosing a Collection as many types offer low complexity and high efficiency
 * with this kind of operation. A List would also have been a good choice, but
 * I went for the Set since it doesn't allow duplicates, and indeed  duplicate
 * statistics. A Queue would have no use here, nor a Map, since we only want to
 * store elements, without the need of keys.
 * 
 * The order of the elements is not important in this case, so a HashSet implementation
 * is enough. Besides, a TreeSet complexity is higher compared to the HashSet.
 * For the add method, a HashSet has a complexity of O(1), while a TreeSet has
 * O(log n). For this reason, I chose a HashSet.
 * 
 * (An ArrayList would also have been just fine since it also has O(1) complexity
 * for adding)
 * 
 * @author Andrea Dal Molin
 *
 */
public class SequentialEventChannel implements EventChannel{
	
	final private Set<RevisionStatistic> statistics;

	/**
	 * Creates a SequentialEventChannel object and initialises the Map as a HashMap
	 */
	public SequentialEventChannel() {
		this.statistics = new HashSet<RevisionStatistic>();
	}
	
	@Override
	/**
	 * Loops through all the statistics and publishes a revision event in each
	 * of them
	 * @param revisionEvent the revision event to be published inside the stats
	 */
	public void publish(final StatisticEvent revisionEvent) {
		for (final RevisionStatistic revisionStatistic : statistics) {
			revisionStatistic.update(revisionEvent);
		}
	}

	@Override
	/**
	 * Subscribes the statistic to the publisher
	 * @param statistic the statistic subscribing to the publisher
	 */
	public void subscribe(final RevisionStatistic statistic) {
		statistics.add(statistic);
	}
}
