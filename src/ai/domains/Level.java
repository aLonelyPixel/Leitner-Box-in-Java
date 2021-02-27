package ai.domains;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This class creates a Level object that will contain Cards.
 * To store cards, I've chose to use a Set interface. First of all, 
 * a Level can't contain two identical Cards (two Cards are considered 
 * equal if the have the same question). This means a Set is particularly
 * useful since it doesn't allow for identical items. The methods that
 * will be need in a level are:
 * 
 * -to add a Card
 * -to remove a Card
 * -to check if a Level contains a Card
 * 
 * All of the three methods are used recurrently which means a good choice
 * of Collection is important to optimize performance. A List allows for
 * duplicate elements and can have a higher complexity for removing items,
 * which means it's not suited here. A Queue is more suited to hold 
 * elements prior to processing, so it isn't a good option either. Finally
 * a Map could be a good choice, especially a Hashmap implementation, but
 * I've chosen to just store values and not keys. A Set, compared to a Map
 * also allows to use more easily an Iterator, since it's directly 
 * implemented. For the methods we use here, a Map and a Set are also very
 * similar, if not identical, in complexity, but I reckoned a Set would be
 * more suited. Anyhow a Map would've also worked well here.
 * 
 * As for the Set implementation, the choice was between a TreeSet and
 * a HashSet. Given the structure of the TreeSet, its complexity for the
 * methods we use here would be higher than the HashSet's. In fact, more
 * specifically for the add, remove and contains methods, a TreeSet would
 * have a O(logN) theoretical time complexity. By comparison, a HashSet
 * only has a constant complexity for those same methods. In this case then,
 * the choice pretty easily falls in favor of the HashSet, which also
 * explains why a HashMap would have worked, given their similar structure.
 * Both HashMaps and HashSets use hashcodes to store, and find elements.
 * In other words, when we ask if a HashSet contains(card), the card will be
 * transformed in hashcode. The contains method will then check if that
 * hashcode is already in the Set. If so, contains will return true. This
 * is also how add and remove work in a Set. Finally the order of the cards
 * inside a Level isn't important so using a TreeSet isn't interesting
 * anyway.
 * 
 * @author Andrea Dal Molin
 *
 */
public class Level implements Iterable<Card>{

	private final int numLevel;
	private final Set<Card> cards;

	/**
	 * This constructor initialises the Level with a Level number and initialises
	 * the interface that will be used to contain cards
	 * 
	 * @param numLevel, the level number of the Level
	 */
	public Level(final int numLevel) {
		this.numLevel = numLevel;
		this.cards = new HashSet<Card>();
	}

	/**
	 * Getter for the Level number
	 * @return the Level number
	 */
	public int getNumLevel() {
		return numLevel;
	}

	/**
	 * This method allow to know if a card with the same question is already inside
	 * the Level. Theoretical time complexity of this method is in O(1) since it uses
	 * the standard HashSet contains method, that works with hashcode to be efficient.
	 * 
	 * @param card, the card to be found in the Level
	 * @return true if the card is already in the Level, false otherwise
	 */
	public boolean contains(final Card card) {
		return (cards.contains(card));
	}

	/**
	 * Inserts a new Card inside the Level. Theoretical time complexity of this method 
	 * is O(1) sincei uses the standard HashSet add method.
	 * @param card, the new card to be added
	 */
	public void insert(final Card card) {
		if (!contains(card)) {
			cards.add(card);
		}
	}

	/**
	 * Removes a Card from the Level
	 * @param card, the card to be removed
	 */
	public void remove(final Card card) {
		if (contains(card)) {
			cards.remove(card);
		}
	}

	@Override
	/**
	 * Returns a new iterator for the card
	 */
	public Iterator<Card> iterator() {
		final Iterator<Card> it = cards.iterator();
		return it; // NOPMD by andre on 09/05/20, 19:58
	}
	
	/**
	 * Returns true if the Level is Empty, false if there's still Cards in the Level.
	 * Operates in constant O(1) time complexity.
	 * @return true if the Level is empty
	 */
	public boolean isEmpty() {
		return this.cards.isEmpty();
	}
}
