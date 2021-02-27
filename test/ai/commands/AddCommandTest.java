package ai.commands;

import org.junit.jupiter.api.Test;

import ai.domains.Box;
import ai.domains.LearningSchedule;
import ai.domains.algorithm.FakeCalendarAlgorithm;
import ai.pubsub.FakeEventChannel;

class AddCommandTest {
	

	@Test
	void addsAndSaveCard () {
		final String[][] userInput = { 
	         { "Who let the dogs out ?", "Who who whoo !" } 
	         //Sera utilisé pour simuler une entrée de l’utilisateur
	      };
		final FakeCalendarAlgorithm calendar = new FakeCalendarAlgorithm(4);
		final FakeConsole console = new FakeConsole();
		final FakeStorage storage = new FakeStorage();
		final FakeEventChannel eventChannel = new FakeEventChannel();
		final Box box = new Box(3);
		final LearningSchedule learningSchedule = new LearningSchedule(box, 1, calendar);
		AddCommand cmd = new AddCommand(console, learningSchedule, storage, eventChannel);
		console.addUserInput(userInput[0]);

		cmd.execute();
		
		storage.verifySaveCallCount(1);
		storage.verifyLoadCallCount(0);
	}


}
