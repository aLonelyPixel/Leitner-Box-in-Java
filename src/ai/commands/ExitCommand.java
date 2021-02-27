package ai.commands;

import ai.consoles.Console;

/**
 * This class inherits of the abstract class Command and specifies some
 * specific behaviour and properties
 * 
 * @author Andrea Dal Molin
 *
 */
public class ExitCommand extends Command{
	private final Console console;

	/**
	 * This constructor sets a specific name and description for the
	 * ExitCommand and initialises a Console attribute that is needed 
	 * for the execute method
	 * @param console
	 */
	public ExitCommand(final Console console) {
		super("exit", "Termine l'exécution du programme");
		this.console = console;
	}

	@Override
	/**
	 * Calls an internal method of the Console class (that uses a System.exit())
	 * and prints a goodbye line to be kind and polite to the User
	 */
	public void execute() {
		console.printLine("Au revoir !");
		console.exit();
	}
}
