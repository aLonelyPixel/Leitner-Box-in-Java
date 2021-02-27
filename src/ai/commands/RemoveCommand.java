package ai.commands;

import ai.consoles.Console;
import ai.data.Storage;
import ai.domains.Card;
import ai.domains.LearningSchedule;

/**
 * This class creates a RemoveCommand that is going to allow to remove Cards from the Box
 * 
 * @author Andrea Dal Molin
 *
 */
public class RemoveCommand extends Command{

	private final Console console;
	private final LearningSchedule ls;
	private final Storage storage;

	/**
	 * Constructor that initialises the RemoveCommand with a console to interact
	 * with the User, a Learning Schedule and a Storage to save and load data.
	 * Also sets a specific name and description to the Command.
	 */
	public RemoveCommand(final Console console, final LearningSchedule ls, final Storage storage) {
		super("remove", "Supprime une carte de la boîte");
		this.console = console;
		this.ls = ls;
		this.storage = storage;
	}

	@Override
	/**
	 * This overridden method gets all the Cards from the Box, cycles through
	 * all of them and asks for each one if the User would like it to be removed
	 * from the Box. When there are no more Cards to go through, it saves the
	 * Learning Schedule.
	 */
	public void execute() {
		final Iterable<Card> allCards = ls.getAllCards();

		for (final Card card : allCards) {
			console.printLine("Carte (question=" + card.getQuestion() + ", réponse=" + card.getAnswer() + ")");
			final String userChoice = (console.readLine("Pressez [R] pour retirer la carte :")).toLowerCase();
			if ("r".equals(userChoice)) {
				ls.remove(card);
				console.printLine("Carte retirée : \"" + card.getQuestion() + "\"");
			}
		}
		storage.save(ls);
	}
}
