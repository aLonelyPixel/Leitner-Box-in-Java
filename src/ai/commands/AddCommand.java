package ai.commands;

import ai.consoles.Console;
import ai.data.Storage;
import ai.domains.Card;
import ai.domains.LearningSchedule;
import ai.pubsub.EventChannel;
import ai.statistic.StatisticEvent;

/**
 * This class creates the AddCommand that is going to allow to add Cards to the Box
 * 
 * @author Andrea Dal Molin
 *
 */
public class AddCommand extends Command{

	private final Console console;
	private final LearningSchedule ls;
	private final Storage storage;
	private final EventChannel eventChannel;

	/**
	 * This constructor initialises the AddCommand with the console, used in the
	 * execute method to interact with the User, a Learning Schedule and a storage
	 * to save and load the previously entered Cards. Also initialises an
	 * eventChannel to update the statistics
	 * @param eventChannel 
	 */
	public AddCommand(final Console console, final LearningSchedule ls, final Storage storage, final EventChannel eventChannel) {
		super("add", "Ajoute une carte");
		this.console = console;
		this.ls = ls;
		this.storage = storage;
		this.eventChannel = eventChannel;
	}

	@Override
	/**
	 * This overridden method asks the characteristics of the new Card to be entered
	 * and adds it if it isn't already in the Box. It then saves the Learning Schedule
	 */
	public void execute() {
		final String question = console.readLine("La question ? ");
		final String answer = console.readLine("La réponse ? ");
		final Card newCard = new Card(question, answer);
		if (ls.add(newCard)) {
			eventChannel.publish(new StatisticEvent(newCard, 1, ls.getCurrentDay()));
			console.printLine("Carte ajoutée au niveau 1 : \"" + newCard.getQuestion() + "\"");
		}else {
			console.printLine("Ajout refusé !");
		}
		storage.save(ls);
	}
}
