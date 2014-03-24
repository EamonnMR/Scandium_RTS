package commands;

/**
 * This class has two distinct functions: one is to act as a base class
 * for Instructions, and the other is to 
 */

public abstract class Instruction {
	abstract String toCode();
	public static Instruction fromCode(String code) {
		return new Teleport(code);
	}
}
