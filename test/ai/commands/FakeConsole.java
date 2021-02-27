package ai.commands;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

import ai.consoles.Console;

/**
 * Simule des interactions avec l'utilisateur ce qui est utile pour valider les commandes.
 * 
 * Cette fausse console affiche tout de même les sorties à l'écran et 
 * propose des méthodes pour simuler des entrées utilisateurs,
 * pour vérifier qu'une sortie attendue a été effectivement produite,
 * pour vérifier que la méthode de sortie a été effectivement appelée, etc.
 * 
 * @see ai.ai.commands.Command
 * @see ai.consoles.Console
 * */
public class FakeConsole implements Console {
	private final PrintStream output;
	private final ByteArrayOutputStream buffer;
	private final PrintStream clonedOutput;
	private final LinkedBlockingQueue<String> input;
	private int exitCalledCount;
	
	/**
	 * 
	 */
	public FakeConsole() {
		output = System.out;
		buffer = new ByteArrayOutputStream();
		clonedOutput = new PrintStream(buffer);
		input = new LinkedBlockingQueue<String>();
	}
	
	/**
	 * Ajoute des entrées utilisateurs fictives.
	 * 
	 * Ces entrées seront utilisées comme valeurs de retours pour les méthodes readLine et readInteger.
	 * 
	 * @param inputs une séquence d'entrées utilisateur.
	 * @see ai.consoles.Console#readLine(String, Object...)
	 * @see ai.consoles.Console#readInteger(String, Object...)
	 * */
	public void addUserInput(final String... inputs) {
		for (final String str : inputs) {
			try {
				input.add(str);
			} catch (NullPointerException e) {
				throw new IllegalArgumentException("La référence null n'est pas admise !");
			}
		}
	}

	/**
	 * Vide les entrées utilisateurs mémorisées.
	 * 
	 * Cette méthode peut être utiles entre deux exécutions de tests afin de s'assurer
	 * que des entrées parasites n'apparaissent pas.
	 * */
	public void clearUserInput() {
		input.clear();
	}
	
	/**
	 * Lance une erreur d'assertion si aucun appel à la méthode exit n'a été enregistré.
	 * 
	 * @see #exit()
	 * */
	public void verifyExitCalled() {
		assertNotEquals("La méthode exit de cette console devrait avoir été appelée.", 0,exitCalledCount);
	}
	
	/**
	 * Lance une erreur d'assertion si le texte attendu n'a pas été écrit à l'aide des méthodes print...
	 * 
	 * @param expected le texte attendu en sortie
	 * @see #print(String, Object...)
	 * @see #printLine(String, Object...)
	 * @see #printLine()
	 * */
	public void verifyOutputContains(final String expected) {
		final String asString = new String(buffer.toByteArray());
		assertTrue("Cette console devrait afficher le texte ["+expected+"].", asString.contains(expected));
	}
	
	@Override
	public String readLine(final String fmt, final Object... args) {
		String str = null;
		print(fmt, args);
		if (!input.isEmpty()) {
			str = input.remove();
			printLine(str);
		}
		return str;
	}

	@Override
	public void printLine(final String fmt, final Object... args) {
		print(fmt, args);
		printLine();
	}

	@Override
	public void print(final String fmt, final Object... args) {
		output.printf(fmt, args);
		clonedOutput.printf(fmt, args);
	}

	@Override
	public void printLine() {
		output.println();
		clonedOutput.println();
	}

	@Override
	public int readInteger(final String fmt, final Object... args) {
		final String lineRead = readLine(fmt, args);
		return Integer.parseInt(lineRead);
	}

	
	@Override
	public void exit() {
		++exitCalledCount;
	}

}
