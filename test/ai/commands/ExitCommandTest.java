package ai.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ai.commands.ExitCommand;

/**
 * 
 * @author andre
 *
 */
class ExitCommandTest {

	@Test
	/**
	 * 
	 */
	public void knowsNameAndDescription() {
		final FakeConsole console = new FakeConsole();
		final ExitCommand cmd = new ExitCommand(console);
		
		assertEquals("exit", cmd.getName());
		assertEquals("Termine l'exécution du programme", cmd.getDescription());
	} 
	
	@Test
	/**
	 * 
	 */
	public void exitsOnExecute() {
		final FakeConsole console = new FakeConsole();
		final ExitCommand cmd = new ExitCommand(console);
		
		cmd.execute();
		
		console.verifyExitCalled();
		console.verifyOutputContains("Au revoir !");
	} 

}
