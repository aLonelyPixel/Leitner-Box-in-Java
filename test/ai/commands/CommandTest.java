package ai.commands;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author andre
 *
 */
class CommandTest {
	
	@Test
	/**
	 * Tests the getName and getDescription methods
	 */
	public void testKnowsNameAndDescription() {
		final FakeCommand cmd = new FakeCommand("testName", "veryGood");
		
		assertEquals("testName", cmd.getName());
		assertEquals("veryGood", cmd.getDescription());
	}

	@Test
	/**
	 * Tests the method hasName returns true ignoring case
	 */
	public void trueWhenSameName() {
		final FakeCommand cmd1 = new FakeCommand("testName", "veryGood");
		final FakeCommand cmd2 = new FakeCommand("TESTNAME", "amazing");
		
		assertTrue(cmd1.hasName(cmd2.getName()));
	}
	
	@Test
	/**
	 * Tests the method hasName returns true ignoring case
	 */
	public void falseWhenDifferentName() {
		final FakeCommand cmd1 = new FakeCommand("testName", "veryGood");
		final FakeCommand cmd2 = new FakeCommand("differentName", "veryBad");
		
		assertFalse(cmd1.hasName(cmd2.getName()));
	}
	
}
