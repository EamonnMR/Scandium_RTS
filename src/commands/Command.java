package commands;

/** Commands are the key.
 * 
 * This class does two things: it forces every command to have a "toCode" method, and it has a 
 * factory that will hide the messy downcasting required to decode commands.
 * @author Eamonn
 *
 */
public abstract class Command {
	public abstract String toCode();
	public static Command fromCode(String code) {
		return null;
	}
}
