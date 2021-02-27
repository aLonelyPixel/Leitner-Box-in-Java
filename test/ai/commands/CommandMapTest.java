package ai.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ai.commands.Command;
import ai.commands.CommandMap;

class CommandMapTest {

	@Test
	/**
	 * Tests the get method in the Map to find the contained Commands
	 */
	void knowsHisCommands() {
		final FakeCommand cmd1 = new FakeCommand("testName", "veryGood");
		final FakeCommand cmd2 = new FakeCommand("testName1", "amazing");
		final FakeCommand cmd3 = new FakeCommand("testName2", "good");
		final CommandMap commands = new CommandMap(cmd1, cmd2, cmd3);
		
		assertEquals(cmd1, commands.get("testName"));
		assertEquals(cmd2, commands.get("testName1"));
		assertEquals(cmd3, commands.get("testName2"));
	}

	@Test
	/**
	 * If the command isn't in the Map, the get method shall return null
	 */
	void nonExistentCommandReturnsNull() {
		final FakeCommand cmd1 = new FakeCommand("testName", "veryGood");
		final FakeCommand cmd2 = new FakeCommand("testName1", "amazing");
		final FakeCommand cmd3 = new FakeCommand("testName2", "good");
		final CommandMap commands = new CommandMap(cmd1, cmd2, cmd3);
		
		assertEquals(null, commands.get("nonExistent"));
	}
	
	@Test
	/**
	 * Initialising the CommandMap with a Collection of Commands
	 */
	void initialisesWithCollection() {
		final FakeCommand cmd1 = new FakeCommand("testName", "veryGood");
		final FakeCommand cmd2 = new FakeCommand("testName1", "amazing");
		final FakeCommand cmd3 = new FakeCommand("testName2", "good");
		final List<Command> commandsList = new ArrayList<Command>();
		commandsList.add(cmd1);
		commandsList.add(cmd2);
		commandsList.add(cmd3);
		final CommandMap commands = new CommandMap(commandsList);

		assertEquals(cmd1, commands.get("testName"));
		assertEquals(cmd2, commands.get("testName1"));
		assertEquals(cmd3, commands.get("testName2"));
		assertEquals(null, commands.get("nonExistent"));
	}
	
	@Test
	/**
	 * Initialising the CommandMap with an ellipse of Commands
	 */
	void iteratorReturnsCorrectNext() {
		final FakeCommand cmd1 = new FakeCommand("testName", "veryGood");
		final FakeCommand cmd2 = new FakeCommand("testName1", "amazing");
		final FakeCommand cmd3 = new FakeCommand("testName2", "good");
		final CommandMap commands = new CommandMap(cmd1, cmd2, cmd3);
		
		assertEquals(cmd2, commands.iterator().next());
		assertTrue(commands.iterator().hasNext());
	}
}
