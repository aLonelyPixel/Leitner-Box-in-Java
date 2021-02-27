package ai;

import ai.commands.AddCommand;
import ai.commands.CommandMap;
import ai.commands.ExitCommand;
import ai.commands.RemoveCommand;
import ai.commands.ReviseCommand;
import ai.commands.ShowStatsBoardCommand;
import ai.consoles.Console;
import ai.consoles.UserConsole;
import ai.data.LearningScheduleStorage;
import ai.data.Storage;
import ai.domains.LearningSchedule;
import ai.domains.algorithm.CalendarAlgorithm;
import ai.domains.algorithm.CalendarAlgorithmComputationnal;
import ai.pubsub.EventChannel;
import ai.pubsub.SequentialEventChannel;
import ai.statistic.AverageCardsRevisionCount;
import ai.statistic.CardsInEachLevelCount;
import ai.domains.Box;

/**
 * Main program
 * 
 * @author Andrea Dal Molin
 */
public class Program {

	public static final String LS_STORAGE_PATH = "res/learningSchedule.json";
	public static final int NUMBER_OF_LEVELS = 7; 

	/**
	 * Main program that initialises the core functions of the Leitner Box system
	 * (only one box is going to be used in this program)
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		final Console console = new UserConsole();
		final Storage storage = new LearningScheduleStorage(LS_STORAGE_PATH);
		final CalendarAlgorithm calendar = new CalendarAlgorithmComputationnal(NUMBER_OF_LEVELS);
		final EventChannel eventChannel = new SequentialEventChannel();
		final AverageCardsRevisionCount avgCardsRevisions = new AverageCardsRevisionCount();
		final CardsInEachLevelCount cardsLevelVisits = new CardsInEachLevelCount();
		eventChannel.subscribe(avgCardsRevisions);
		eventChannel.subscribe(cardsLevelVisits);
		LearningSchedule learningSchedule = storage.load(calendar) ;
		if (learningSchedule == null) { //Impossible de charger le fichier
			learningSchedule = new LearningSchedule(new Box(NUMBER_OF_LEVELS), 1, calendar);
		}
		final CommandMap map = 	new CommandMap(
								new ExitCommand(console), 
								new AddCommand(console, learningSchedule, storage, eventChannel), 
								new RemoveCommand(console, learningSchedule, storage),
								new ReviseCommand(console, storage, learningSchedule, eventChannel),
								new ShowStatsBoardCommand(console, avgCardsRevisions, cardsLevelVisits));

		final FrontController controller = new FrontController(console, map);		
		console.print("Bienvenue!");
		controller.loop();
	}
}
