package ai.commands;

import java.util.Iterator;
import ai.consoles.Console;
import ai.domains.Card;
import ai.statistic.AverageCardsRevisionCount;
import ai.statistic.CardsInEachLevelCount;

/**
 * Shows to the User statistics about his Cards, Levels and revisions
 * 
 * @author Andrea Dal Molin
 *
 */
public class ShowStatsBoardCommand extends Command{

	private final Console console;
	private final AverageCardsRevisionCount avgCardsRevisions;
	private final CardsInEachLevelCount cardsLevelVisits;
	
	/**
	 * Constructor for the stats Command that takes as parameter Statistics
	 * and a Console to interact with the User. Also initialises two statistics
	 * that are going to be used in the execute method
	 * @param console used to interact with the Kind User
	 * @param avgCardsRevisions the statistics that calculate the average number of revisions for the cards
	 * @param cardsLevelVisits the statistics that keep track of how many times each Card goes through each Level
	 */
	public ShowStatsBoardCommand(final Console console, final AverageCardsRevisionCount avgCardsRevisions, final CardsInEachLevelCount cardsLevelVisits) {
		super("stats", "Affiche les statistiques");
		this.console = console;
		this.avgCardsRevisions = avgCardsRevisions;
		this.cardsLevelVisits = cardsLevelVisits;
	}

	@Override
	/**
	 * Overridden method that shows to the User the differents statistics about 
	 * the Cards, Levels and revisions
	 */
	public void execute() {
//		FIRST STATISTIC
		console.printLine("\nLes statistiques affichées sont :");
		console.printLine("- le nombre moyen de révisions par carte : %2.2f", avgCardsRevisions.average());
		
//		SECOND STATISTIC
		console.printLine("- le nombre de fois que les cartes sont passées à chaque level");
		final Iterator<Card> it = cardsLevelVisits.getAllCards();
		
		while (it.hasNext()) {
			final Card thisCard = it.next();
			String userFeedback = "";
			userFeedback = console.readLine("\nPressez [C] pour consulter les statistiques pour la Carte (question= \"" + thisCard.getQuestion() + "\", réponse= \"" + thisCard.getAnswer() + "\") : ");
			if ("c".equalsIgnoreCase(userFeedback)) {
				final int[] values = cardsLevelVisits.returnCardValues(thisCard);
				console.printLine("\nLes statistiques pour cette carte sont ");
				for (int i = 0; i < values.length; i++) {
					console.print("lv" + (i+1) + ": " + values[i] + " ");
				}
			}
		}
	}
}
