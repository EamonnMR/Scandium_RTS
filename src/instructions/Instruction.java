package instructions;

/**
 * This class has two distinct functions: one is to act as a base class
 * for Instructions, and the other is to 
 */

public abstract class Instruction {
	abstract String toCode();
	public static Instruction fromCode(String code) {
		switch(Integer.parseInt(code.substring(0,3))){
		case 0:{
			return new Relocate(code.substring(3));
		}
		case 1:{
			return new RequisitionUnit(code.substring(3));
		}
		case 2:
		default:
			System.out.println("Passed bad instr" + code.substring(0,3));
			return null;
		}
	}
}
