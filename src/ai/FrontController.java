package ai;

import ai.commands.Command;
import ai.commands.CommandMap;
import ai.consoles.Console;

/**
 * Creates a FrontController that is going to interact with the program, usually
 * its loop method. Initialises a commandMap and a console for communication
 * with the User.
 * 
 * @author Andrea Dal Molin
 *
 */
public class FrontController {

	private final Console console;
	private final CommandMap commandMap;

	/**
	 * Constructor that builds the FrontControll via a console and a CommandMap
	 * 
	 * @param console
	 * @param commandMap
	 */
	public FrontController(final Console console, final CommandMap commandMap) {
		this.console = console;
		this.commandMap = commandMap;
	}

	/**
	 * The main core of the FrontController is this loop method that loops
	 * until the User decides to quit by typing "exit". Allows to use the different
	 * Commands of the CommandMap.
	 */
	public void loop() {
		String userChoice = "";

		do {
			userChoice = console.readLine("\n\nEntrez une commande : ");
			switch (userChoice.toLowerCase()) {
			case "list":
				printLists();
				break;
			case "exit":
				this.commandMap.get("exit").execute();
				break;
			case "add":
				this.commandMap.get("add").execute();
				break;
			case "remove":
				this.commandMap.get("remove").execute();
				break;
			case "revise":
				this.commandMap.get("revise").execute();
				break;
			case "stats":
				this.commandMap.get("stats").execute();
				break;
			default: 
				console.print("Invalid message");
				break;
			}
		} while (!"exit".equalsIgnoreCase(userChoice));
	}

	/**
	 * Used in the loop method for printing via the console all of the Commands
	 * available in the CommandMap.
	 */
	private void printLists() {
		for(final Command command  : commandMap) {
			if (command!=null) {
				console.print(command.getName() + "\t\t" + command.getDescription() + "\n");
			}
		}
	}
}
