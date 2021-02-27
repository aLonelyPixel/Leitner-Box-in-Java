package ai.commands;

import ai.consoles.Console;
import ai.data.Storage;
import ai.domains.Card;
import ai.domains.LearningSchedule;
import ai.iterators.CardsIterator;
import ai.pubsub.EventChannel;
import ai.statistic.StatisticEvent;

/**
 * Defines a Command that allows the User to revise its Cards, based on the
 * current day and the levels to revise on such day.
 * 
 * @author Andrea Dal Molin
 *
 */
public class ReviseCommand extends Command{

	private final Console console;
	private final Storage storage;
	private final LearningSchedule ls;
	private final EventChannel eventChannel;

	/**
	 * Constructor that initialises the Command with a specific name and description.
	 * Also uses a console to interact with the User, a Learing Schedule and a Storage
	 * to save and load data. Also initialises an eventChannel to update the statistics
	 */
	public ReviseCommand(final Console console, final Storage storage, final LearningSchedule ls, final EventChannel eventChannel) {
		super("revise", "Permet de réviser les cartes");
		this.console = console;
		this.storage = storage;
		this.ls = ls;
		this.eventChannel = eventChannel;
	}

	@Override
	/**
	 * This overridden method cycles through all the cards contained in
	 * the Levels that have to be revised on the current day. It then
	 * asks the User the answer for each Card, prints the correct answer
	 * and lets the User decide if his/her answer was correct or not. If 
	 * it was, the card is moves to the next Level, or remove from the box
	 * if it was in the last level. If it wasn't, it pushes the Card back
	 * to the first Level :( (sad face) and adds it to the queue of cards
	 * to revise that day. Basically, it runs a failed card over and over
	 * again, until the User guesses it right. This means each day 
	 * is going to end with an empty first Level.
	 */
	public void execute() {
		if (ls.isRevisable()) {
			final CardsIterator cardsIterator = ls.getCardsToRevise();
			console.printLine("Révision " + ls.getCurrentDay());
			
			if (!cardsIterator.hasNext()) {
				console.printLine("Aucune carte à réviser ! ");
			}
			
			while (cardsIterator.hasNext()) {
				final Card thisCard = cardsIterator.next();
				askQuestions(cardsIterator, thisCard);
			}
			ls.nextDay();
			storage.save(ls);
		}else {
			console.printLine("Votre boîte est vide. Félicitation ! Vous avez appris toutes les cartes de la boîte !");
		}
	}

	/**
	 * Asks questions to the User and gets her/his answers
	 * @param cardsIterator the cardsIterator that allows to add a Card if the User fails to answer
	 * @param thisCard the Card currently being used and to which the User has to answer
	 */
	private void askQuestions(final CardsIterator cardsIterator, final Card thisCard) {
		console.readLine(thisCard.getQuestion()+"\t");
		console.printLine("La réponse correcte est : " + thisCard.getAnswer());

		String userFeedBack;
		do {
			userFeedBack = console.readLine("Est-elle correcte ? ([O]ui, [N]on)");
		} while (!"o".equalsIgnoreCase(userFeedBack) && !"n".equalsIgnoreCase(userFeedBack));

		if ("o".equals(userFeedBack)) {
			final int destLevel = ls.moveCardToNextLevel(thisCard, true);
			eventChannel.publish(new StatisticEvent(thisCard, destLevel, ls.getCurrentDay()));
		}else {
			final int destLevel = ls.moveCardToNextLevel(thisCard, false);
			eventChannel.publish(new StatisticEvent(thisCard, destLevel, ls.getCurrentDay()));
			cardsIterator.add(thisCard);
		}
	}
}
