package ai.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author andre
 *
 */
class CardTest {

	@Test
	/**
	 * Checks if the getters work correctly
	 */
	void knowsQuestionAndAnswer() {
		final Card newCard = new Card("Vais-je passer l'AI?", "Hahaha");
		
		assertEquals("Vais-je passer l'AI?", newCard.getQuestion());
		assertEquals("Hahaha", newCard.getAnswer());
	}

	@Test
	/**
	 * Tests if returns null on null answer
	 */
	void knowsQuestionAndNullAnswer() {
		final Card newCard = new Card("Vais-je passer l'AI?", null);
		
		assertEquals("Vais-je passer l'AI?", newCard.getQuestion());
		assertEquals(null, newCard.getAnswer());
	}
	
	@Test
	void knowsNullQuestionAndAnswer() {
		final Card newCard = new Card(null, "Hahaha");
		
		assertEquals(null, newCard.getQuestion());
		assertEquals("Hahaha", newCard.getAnswer());
	}
	
	@Test
	void knowsNullQuestionAndNullAnswer() {
		final Card newCard = new Card(null, null);
		
		assertEquals(null, newCard.getQuestion());
		assertEquals(null, newCard.getAnswer());
	}
	
	@Test
	void notEqualsOnSameQuestionDifferentAnswers() {
		final Card firstCard = new Card("Cool card", "Yeah mate");
		final Card secondCard = new Card("Cool card" , "Nah");
		
		assertTrue(firstCard.equals(secondCard));
	}
	
	@Test
	void notEqualsOnDifferentQuestionSameAnswers() {
		final Card firstCard = new Card("Cool card", "Nah");
		final Card secondCard = new Card("Cooler card" , "Nah");
		
		assertFalse(firstCard.equals(secondCard));
	}
	
	@Test
	void cardEqualsItself() {
		final Card firstCard = new Card("Cool card", "Nah");
		
		assertTrue(firstCard.equals(firstCard));
	}
	
	@Test
	void noTEqualsOnNullCard() {
		final Card firstCard = new Card("Cool card", "Nah");
		final Card secondCard = null;
		
		assertFalse(firstCard.equals(secondCard));
	}
}
