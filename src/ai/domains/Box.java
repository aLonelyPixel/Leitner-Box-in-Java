package ai.domains;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ai.iterators.CardsIterator;

/**
 * This class creates a box that will be used to contain a minimum of two 
 * Levels.
 * 
 * To store the Levels, I've chosen to use a List inteface. The methods
 * used in the Collection are just two: add and get. A Set can't easily
 * retrieve elements, so it's not a suitable choice since the recurrent
 * use of the get method (which the Set interface doesn't even offer). 
 * A Queue is more suited to hold elements prior to processing, so it 
 * isn't a good option either. A Map isn't interesting either since we do
 * not need a key-value association. Furthermore most of the time, we use
 * the get method to add a Card to the first Level, whose we know the 
 * index. Finally, a List directly implements the iterator method, while
 * the Map interface doesn't. Complexity-wise the Map and the List wouldn't
 * differ too much to one another, but for the reasons stated previously,
 * I think a List is a better fit in this case.
 * 
 * As for the List implementation, I've chosen to use an ArrayList. The most
 * used method is without a doubt the get method, which for the ArrayList
 * is in O(1), while for the LinkedList it's O(N/2). The add method is
 * indeed faster in a LinkedList (O(1) vs O(N) in an ArrayList), but this
 * method is only used upon initialisation, which means it's more relevant
 * to choose the ArrayList, since we prefer a quick accessibility with the
 * get method.
 * 
 * @author Andrea Dal Molin
 *
 */
public class Box implements Iterable<Card>{

	private int nbOfLevels = 2;
	private final List<Level> levels;

	/**
	 * This constructor initialises the Box with the number of Levels its
	 * going to contain and initialising the ArrayList to that length with 
	 * empty Levels
	 * 
	 * @param nbOfLevels how many Levels are going to be in the Box
	 */
	public Box(final int nbOfLevels) {
		if (nbOfLevels > 2) {
			this.nbOfLevels = nbOfLevels;
		}

		levels = new ArrayList<Level>();

		for (int i = 1; i <= this.nbOfLevels; i++) {
			this.levels.add(new Level(i));
		}
	}


	/**
	 * Getter for the number of Levels of the Box
	 * 
	 * @return the number of Levels of the Box
	 */
	public int getAmountOfLevels() {
		return nbOfLevels;
	}

	/**
	 * Returns the iterator for the l Level
	 * 
	 * @param l the Level to be found
	 * @return the iterator of the searched Level
	 */
	public Iterator<Card> getIteratorForLevel(final int l){
		for (final Level level : levels) {
			if (level.getNumLevel() == l) {
				return level.iterator();
			}
		}
		return null;
	}

	/**
	 * Adds a new Card to the first Level if the requested Card isn't already
	 * in the box. 
	 * To calculate the total theoretical time complexity we have to consider
	 * the time complxity of the contains and insert methods. Both of these are 
	 * in O(1).
	 * The theoretical time complexity of this method is O(N) because
	 * it is first going to go through each Card to check if the requested Card
	 * is already in the Box O(N*1), and if not, it's going to insert it in the
	 * Level 1. Get method is also O(1). So O(N*1) + O(1) + O(1) = O(N). So it's
	 * a linear complexity.
	 * 
	 * @param card the Card to be added in the Box
	 * @return true if the Card has been successfully added, otherwise false
	 */
	public boolean add(final Card card) {
		for (final Level level : levels) {
			if (level.contains(card)) {
				return false;
			}
		}
		levels.get(0).insert(card);
		return true;
	}

	/**
	 * Removes a card from the Box, using the remove method of Level
	 * 
	 * @param card the Card to be removed in the Box
	 * @return true if the Card has been successfully removed, otherwise false
	 */
	public boolean remove(final Card card) {
		for (final Level level : levels) {
			if (level.contains(card)) {
				level.remove(card);
				return true;
			}
		}
		return false;
	}

	@Override
	/**
	 * Initialises a new ArrayList of all the cards in the Box so that they can 
	 * be used to iterate throughout each of them
	 * @return an iterator for the Cards
	 */
	public Iterator<Card> iterator(){
		final Iterator<Card> it;
		final List<Card> allCards = new ArrayList<Card>();

		for (final Level level : levels) {
			for (final Card card : level) {
				allCards.add(card);
			}
		}
		it = allCards.iterator();

		return it;
	}

	/**
	 * This method returns an CardsIterator build from the cards
	 * contained in the parameter levels. A Queue is used to store
	 * the different Cards since it's the ideal Collection to hold 
	 * elements prior to processing. 
	 * 
	 * Amongst the different Queue possible implementations, I've
	 * chosen to use a LinkedList. This was a relatively simple choice
	 * considering the fact that the methods that will be used for the
	 * Queue inside CardsIterator are add, poll and isEmpty. These
	 * are all constant in theoretical time complexity (in O(1) more
	 * specifically) if we use a LinkedList. For reference, in a PriorityList
	 * those same methods are respectively in O(log2N), O(log2N) and
	 * O(1). This makes, by far, the LinkedList the better choice in
	 * this instance, given its inferior complexity in almost all
	 * of the methods that will be used for this Queue. 
	 * 
	 * @param levels the Levels for which we want to pick the Cards 
	 * @return a CardsIterator built from the cards in the @param levels
	 */
	public CardsIterator getIteratorForLevels(final int... levels) {
		final Queue<Card> cardsQueue = new LinkedList<Card>();

		for (final int i : levels) {
			final Iterator<Card> it = getIteratorForLevel(i);
			while (it.hasNext()) {
				cardsQueue.add(it.next());
			}
		}
		return new CardsIterator(cardsQueue);
	}

	/**
	 * Moves Card to the next Level is the answer is known. If the Card was
	 * already in the last Level, the Card is removed from the Box. If the 
	 * answer is not known, the Card is moved to the first Level
	 * @param card the Card to be moved to the next Level
	 * @param answerKnown determines if the answer was answered correctly,
	 * and will move on in the Box, or incorrectly and will go back to Level 1
	 * @return the Level number where the Card was moved in
	 */
	public int moveCardToNextLevel(final Card card, final boolean answerKnown) {
		for (final Level level : levels) {
			if (level.contains(card)) {
				if (remove(card)) {
					if (answerKnown) {
						if (level.getNumLevel() == this.nbOfLevels) {
							remove(card);
							return -1;
						}else {
							levels.get(level.getNumLevel()).insert(card);
							return level.getNumLevel()+1;
						}
					}else {
						levels.get(0).insert(card);
						return 1;
					}
				}
			}
		}
		return -1;
	}

	/**
	 *	Utility method used in testing to get the Level number
	 *	that contains a card
	 *	@param card the Card for which the Level number is asked
	 *	@return the Level number where the Card is
	 */
	protected int getLevelOfCard(final Card card) {
		int levelNbr = 0;

		for (final Level level : levels) {
			if (level.contains(card)) {
				levelNbr = level.getNumLevel();
			}
		}
		return levelNbr;
	}
	
	/**
	 * Runs through all the Box's Levels if they're all empty. If no Cards are found
	 * the Box is Empty and this method returns true.
	 * This method cycles through all the Levels so in the worst case every Level is
	 * going to be empty and the method is going to loop for N time where N is the
	 * number of Levels in the Box. The underlying isEmpty method on Levels is in O(1)
	 * constant theoretical time complexity. So in total, this method's TTC is O(N)
	 * where N is the number of Levels. This variable is the only one take affects
	 * TTC.
	 * 
	 * @return true if the Box is empty, false if there's still Cards in it
	 */
	public boolean isEmpty() {
		for (final Level level : levels) {
			if (!level.isEmpty()) {
				return false;
			}
		}
		return true;
	}
}
