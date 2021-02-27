package ai.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ai.pubsub.FakeEventChannel;
import ai.statistic.AverageCardsRevisionCount;
import ai.statistic.CardsInEachLevelCount;
import ai.statistic.FakeStatistic;

class ShowStatsBoardCommandTest {

	@Test
	void knowsNameAndDescription() {
		final FakeConsole console = new FakeConsole();
		final FakeEventChannel eventChannel = new FakeEventChannel();
		final AverageCardsRevisionCount avgCardsRevisions = new AverageCardsRevisionCount();
		final CardsInEachLevelCount cardLevelCount = new CardsInEachLevelCount();
		eventChannel.subscribe(avgCardsRevisions);
		final ShowStatsBoardCommand stats = new ShowStatsBoardCommand(console, avgCardsRevisions, cardLevelCount);
		
		assertEquals("stats", stats.getName());
		assertEquals("Affiche les statistiques", stats.getDescription());
	}

}
