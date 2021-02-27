package ai.domains;

import java.util.*;

import ai.domains.Card;

/**
 * Cette classe transforme un tableau de cartes ou une collection de cartes
 * en Liste afin de les comparer dans des assertions assertEquals.
 * 
 * ATTENTION : pour fonctionner, Cards doit redéfinir la méthode equals.
 * */
public class CardsListFactory {
	/**
	 * Transforme un tableau de cartes ou un nombre quelconque de cartes en une liste.
	 * 
	 * @param cards un tableau de cartes
	 * @return une liste immuable de cartes
	 * */
	public static List<Card> asList(final Card... cards) {
		return Arrays.asList(cards);
	}

	/**
	 * Transforme un itérable de cartes en une liste.
	 * 
	 * @param cards un itérable de classe, ce qui correspond à la plupart des collections Java.
	 * @return une liste immuable de cartes
	 * */
	public static List<Card> asList(final Iterable<Card> cards) {
		List<Card> result = new LinkedList<>();
		for (Card c : cards) {
			result.add(c);
		}
		return result;
	}
}
