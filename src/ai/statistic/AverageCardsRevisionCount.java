package ai.statistic;

import java.util.HashMap;
import java.util.Map;

import ai.domains.Card;
import ai.pubsub.RevisionStatistic;

/**
 * This class calculates the average cards amount of revisions. 
 * 
 * To store the values I've chosen to use a Map interface. Amongst
 * Lists, Sets, Queues and Maps, I found the Map to be the most 
 * useful and the most efficient. The technique I've gone for is to
 * associate a Card 'c' to a number of revisions, that will increment
 * each time the 'c' Card is revised in the ReviseCommand. Of course,
 * it doesn't matter if the User answered correctly or not. Every time
 * a new Card is revised it will be added to the Map and its revisions
 * count will be set to 1. If the Card is already in the Map then its
 * revisions counter will increase by one. The Map was the only 
 * interface I could do this with since it works with keys and values.
 * A Queue is not designed for this kind of work, while a List or
 * a Set would make difficult to associate a number of values to
 * each Card.
 * 
 * As for the implementation, between a HashMap and a TreeMap, I've
 * chosen to use a HashMap, mostly because we don't need the elements
 * to be sorted but also because the same operations would be more
 * complex and have a higher theoretical time complexity (TTC) than
 * a HashMap. In fact, the methods used here are containsKey, replace
 * get and put. In a HashMap, those all have a complexity of O(1) 
 * (unless there were multiple identical cards, but here it's not
 * the case). By comparison, all those operations are in in O(log2n)
 * in a TreeMap, which means they are less efficient than the HashMap.
 * Given the methods used and the purpose of this statistic, I then
 * chose the HashMap as it seemed to me the optimal choice.
 * 
 * IMPORTANT NOTICE:
 * 
 * According to my personal interpretation of the instructions, a Card
 * is considered "revised" every time the User answers to it. For example
 * if the Card 'a' is in Level 4 and the User answers incorrectly, it's
 * going to fall back to Level 1. So far, the User has revised that Card
 * once. However, at the end of the revision, that card is going to be
 * asked again, since it fell back to Level 1. The act of answering to
 * that question again, counts to me as a revision. So for as long as
 * the User fails that question, its revision number is going to keep
 * increasing, until the User answers correctly. This was my personal
 * interpretation of the given instructions.
 * 
 * @author Andrea Dal Molin
 *
 */
public class AverageCardsRevisionCount implements RevisionStatistic{

	private final Map<Card, Integer> cardsMap;
	
	/**
	 * Constructor for this specific statistic. Initialises the Map with
	 * a HashMap implementation
	 */
	public AverageCardsRevisionCount() {
		 cardsMap = new HashMap<Card, Integer>();
	}
	
	@Override
	/**
	 * This method adds new Cards to the map, initialising their counter
	 * to 1, and updates its counter if it's already in the Map.
	 * 
	 * All the HashMap methods used (containsKey, replace, get, put) have
	 * a O(1) complexity. The are no loops in this methods, only conditions.
	 * So the total complexity for this method is O(1)
	 * @param revisionEvent the event that is updating the Statistic
	 */
	public void update(final StatisticEvent revisionEvent) {
		if (cardsMap.containsKey(revisionEvent.getCard())) {
			cardsMap.replace(revisionEvent.getCard(), cardsMap.get(revisionEvent.getCard())+1);
		}else if (revisionEvent.getDestLevel() == 1) {
			cardsMap.put(revisionEvent.getCard(), 0);
		}else {
			cardsMap.put(revisionEvent.getCard(), 1);
		}
	}

	/**
	 * Loops through the cards to calculate the average number of revisions
	 * 
	 * @return the average cards number of revisions (a conditions prevents
	 * dividing by 0 when no revisions have been done yet
	 */
	public double average() {
		double somme = 0;
		for (final Card card : cardsMap.keySet()) {
			somme += cardsMap.get(card);
		}
		return cardsMap.size() == 0 ? somme/1 : somme/cardsMap.size();
	}
}
