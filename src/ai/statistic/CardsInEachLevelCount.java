package ai.statistic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ai.Program;
import ai.domains.Card;
import ai.pubsub.RevisionStatistic;

/**
 * This statistics class keeps track of how many time a Card has gone through the
 * different Levels in the Box.
 * 
 * Amongst the different collection types, I chose to use the Map inteface. I found
 * the Map to be the most useful and most efficient for the work that has to be done
 * with this statistic. A Card goes through multiple Levels, which means I have to
 * associate a Card not only to the Levels it went to, but also to the number of
 * times it went through each one. In simple words, each Card has to be linked to
 * multiple Levels. This is exactly the kind of key-value relationship the Map is
 * made for. A Queue is more suited to hold elements prior to processing, so it 
 * isn't a good option here. A List and a Set don't offer the advantages a Map allows
 * here so I've ruled them out and chose a Map inteface. To go deeper into the
 * structure of the Map, I've decided it shall use a Card as key and another Map as
 * values. This is because each Card is going to be linked to multiple Levels AND
 * the amount of times they have been visited. If we only needed the Levels a List
 * or a Set would have been sufficient, but here we need to store the number of visits
 * of that Card to each specific Level and increment it accordingly. This is why I
 * decided to implement an internal Map in my Map. Each key (an int) of the internal 
 * Map is going to represent a Level number and the value associated to each key, is 
 * going to be an Integer representing how many times that Level has been visited by
 * that Card. 
 * 
 * As for the implementation, because we are using Cards and Integers as keys for the 
 * two different Maps, insertion order isn't important. So we don't need a TreeMap,
 * nor a LinkedHashMap. Furthermore, the methods used here are going to be: containsKey,
 * get, put, replace and keySet. The TTC for all of these is O(1) which is linear
 * complexity, better and more efficient than the TreeMap's equivalent methods that
 * in O(log2N). For these reasons, the HashMap is the better choice of implementation.
 * 
 * @author Andrea Dal Molin
 *
 */
public class CardsInEachLevelCount implements RevisionStatistic{
	
	private final Map<Card, Map<Integer, Integer>> cards;
	
	/**
	 * Constructor that initialises the HashMap
	 */
	public CardsInEachLevelCount() {
		this.cards = new HashMap<Card, Map<Integer, Integer>>();
	}
	
	@Override
	/**
	 * Increments the levels "visits" count for each Card. If the Card is already
	 * in the Map it checks if it already visited that Level. If it did, it increments
	 * the counter for that Level, if not in adds that Level and sets the visits to 1.
	 * If the Card is new, it is added in the Map and its first Level is set to 1.
	 * 
	 * Theoretical time complexity calculation (TTC)
	 * 
	 * The methods used are: containsKey, get, put and replace. All of these have a O(1)
	 * TTC. There are no loops in this method, only conditions. So the total TTC of this
	 * method is also O(1).
	 * @param revisionEvent the event that is updating the Statistic
	 */
	public void update(final StatisticEvent revisionEvent) {
		if (cards.containsKey(revisionEvent.getCard())) {
			final Map<Integer, Integer> levels = cards.get(revisionEvent.getCard());
			if (levels.containsKey(revisionEvent.getDestLevel())) {
				incrementLevelCounter(revisionEvent.getDestLevel(), levels);
			}else {
				addLevelToCard(revisionEvent.getDestLevel(), levels);
			}
		}else {
			final HashMap<Integer, Integer> values = new HashMap<Integer, Integer>();
			values.put(revisionEvent.getDestLevel(), 1);
			cards.put(revisionEvent.getCard(), values);
		}
	}
	
	/**
	 * Increments the visits count of a Level for a specific card
	 * 
	 * @param destLevel the level to be incremented
	 * @param levels the Map of Levels where the requested Level is stored
	 */
	private void incrementLevelCounter(final int destLevel, final Map<Integer, Integer> levels) {
		levels.replace(destLevel, levels.get(destLevel)+1);
	}
	
	/**
	 * Adds a new Level to a Card key, and sets it visits count to 1
	 * 
	 * @param destLevel the new Level number to be added
	 * @param levels the Map where the new Level has to be added
	 */
	private void addLevelToCard(final int destLevel, final Map<Integer, Integer> levels) {
		levels.put(destLevel, 1);
	}
	
	/**
	 * Retrieves all the Cards in the Map and iterates them
	 * @return Iterator an iterator that goes through all the Cards
	 */
	public Iterator<Card> getAllCards() {
		return cards.keySet().iterator();
	}
	
	/**
	 * Returns for a requested Card how many times each Level has been visited. If
	 * a Level has never been visited, its count is set to zero
	 * 
	 * @param card the card for which the number of Level visits are requested
	 * @return levelNbr an Integer array where the index represents the Level, and the
	 * value the number of times its been visited from the @param card
	 */
	public int[] returnCardValues(final Card card) {
		int[] levelNbr = new int[Program.NUMBER_OF_LEVELS];
		
		for (int i = 0; i < Program.NUMBER_OF_LEVELS; i++) {
			if (cards.get(card).containsKey(i+1)) {
				levelNbr[i] = cards.get(card).get(i+1);
			}else{
				levelNbr[i] = 0;
			};
		}
		return levelNbr;
	}
}
