package ai.commands;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import ai.domains.Box;
import ai.domains.Card;
import ai.domains.CardsListFactory;
import ai.domains.LearningSchedule;
import ai.domains.algorithm.FakeCalendarAlgorithm;

class RemoveCommandTest {

	@Test
	void removeSeveralCardsFromTheBox() {
		final FakeCalendarAlgorithm calendar = new FakeCalendarAlgorithm(4);
		FakeConsole console = new FakeConsole();
		FakeStorage storage = new FakeStorage();
		CardsListFactory CardsCollectionFactory = new CardsListFactory();
		Box box = new Box(5);
		LearningSchedule learningSchedule = new LearningSchedule(box, 1, calendar);
		List<Card> cards = Arrays.asList(
			new Card("L'aéroport est à gauche", "The airport is to the left"),
			new Card("My taxi is here", "Mon taxi est ici"),
			new Card("Here is your bus", "Voilà ton bus"),
			new Card("Here is the taxi", "Voilà le taxi"),
			new Card("Est-il à l'aéroport ?", "Is he at the airport?"),
			new Card("Êtes-vous David ?", "Are you David?")
		);
		
		for (Card card : cards) {
			learningSchedule.add(card);
		}
		
		final String[] userInput = { "R", "", "", "R", "", "R" };

		RemoveCommand cmd = new RemoveCommand(console, learningSchedule, storage);
		console.addUserInput(userInput);
		cmd.execute();
		
//		There should be three cards left
		assertEquals(3, CardsCollectionFactory.asList(learningSchedule.getAllCards()).size());
	    final List<Card> cardsAsList = CardsCollectionFactory.asList(cards);
		for (Card card: learningSchedule.getAllCards()) {
	        assertTrue(cardsAsList.contains(card));
	    }

				
		storage.verifySaveCallCount(1);
		storage.verifyLoadCallCount(0);
	}


}
