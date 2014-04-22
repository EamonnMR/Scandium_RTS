package instructions;

import java.util.Collection;
import java.util.List;

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
	public static Instruction fromInts(List<Integer> inp) {
		int opcode = inp.remove(0);
		switch(opcode){
		case 0:{
			return new Relocate(inp.remove(0), inp.remove(0));
		}
		case 1:{
			return new RequisitionUnit(inp.remove(0));
		}
		case 2:{
			return new Engage(inp.remove(0));
		}
		default:
			System.out.println("Passed bad opcode" + opcode);
			return null;
		}
	}
	
	public abstract Collection<? extends Integer> toInts();
}
