package ai.pubsub;

import ai.statistic.StatisticEvent;

/**
 * Interface for the Statistics that defines an update method that allows to
 * update each statistic that implements this interface
 * 
 * @author Andrea Dal Molin
 *
 */
public interface RevisionStatistic {
	/**
	 * Updates each statistic with a new event
	 * @param revisionEvent the event that updates the statistic
	 */
	void update(StatisticEvent revisionEvent);
}
