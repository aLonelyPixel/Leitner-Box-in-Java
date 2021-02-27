package ai.commands;

import ai.commands.Command;

/**
 * 
 * @author andre
 *
 */
public class FakeCommand extends Command{

	/**
	 * 
	 * @param name
	 * @param description
	 */
	public FakeCommand(final String name, final String description) {
		super(name, description);
	}

	@Override
	/**
	 * 
	 */
	public void execute() {}

}
