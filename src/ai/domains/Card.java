package ai.domains;

/**
 * Creates a Card based on its question and answer. Two cards cannot have the same
 * question or else they'll be considered the same Card
 * 
 * @author Andrea Dal Molin
 *
 */
public class Card {
	
	private final String question;
	private final String answer;

	/**
	 * Constructor that initialises the Car with a question and an answer, both Strings
	 * 
	 * @param question the question of the Card
	 * @param answer the answer of the Card
	 */
	public Card(final String question, final String answer) {
		this.question = question;
		this.answer = answer;
	}

	/**
	 * Getter for the Card's question
	 * 
	 * @return the Card's question
	 */
	public String getQuestion() {
		return question;
	}
	
	/**
	 * Getter for the Card's answer
	 * 
	 * @return the Card's answer
	 */
	public String getAnswer() {
		return answer;
	}

	@Override
	/**
	 * Redefinition of the hashCode method
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		return result;
	}
	
	@Override
	/**
	 * Redefinition of the equals method so that two Cards are equal
	 * only if they have the same question
	 */
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Card)) {
			return false;
		}
		final Card other = (Card) obj;
		if (question == null) {
			if (other.question != null) {
				return false;
			}
		} else if (!question.equals(other.question)) {
			return false;
		}
		return true;
	}
}
