package ai.pubsub;

import ai.statistic.StatisticEvent;

/**
 * Interface for the EventChannel that defines the main methods that are going to be used
 * in its implementations
 * 
 * @author Andrea Dal Molin
 *
 */
public interface EventChannel {
	/**
	 * Publishes a new event in all the statistics
	 * @param revisionEvent the event to be published in every statistic
	 */
	public void publish(StatisticEvent revisionEvent);
	/**
	 * Subscribes a statistic to the publisher
	 * @param statistic the statistic subscribing to the publisher
	 */
	public void subscribe(RevisionStatistic statistic);
}
