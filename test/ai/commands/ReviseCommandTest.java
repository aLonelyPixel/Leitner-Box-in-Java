package ai.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ai.domains.Box;
import ai.domains.LearningSchedule;
import ai.domains.algorithm.FakeCalendarAlgorithm;
import ai.pubsub.FakeEventChannel;
import ai.pubsub.SequentialEventChannel;
import ai.statistic.FakeStatistic;

class ReviseCommandTest {

	@Test
	void knowsNameAndDescription() {
		final FakeStorage storage = new FakeStorage();
		final FakeConsole console = new FakeConsole();
		final FakeEventChannel eventChannel = new FakeEventChannel();
		final FakeStatistic statistic = new FakeStatistic();
		eventChannel.subscribe(statistic);
		final Box box = new Box(4);
		final FakeCalendarAlgorithm calendar = new FakeCalendarAlgorithm(4);
		final LearningSchedule ls = new LearningSchedule(box, 1, calendar);
		final ReviseCommand reviseCommand = new ReviseCommand(console, storage, ls, eventChannel);
		
		assertEquals("revise", reviseCommand.getName());
		assertEquals("Permet de réviser les cartes", reviseCommand.getDescription());
	}

}
