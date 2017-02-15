package instructions;

import java.util.Collection;
import java.util.List;

/**
 * Base class for Instructions
 */

public abstract class Instruction {

	public static Instruction fromInts(List<Integer> inp) {
		int opcode = inp.remove(0);
		System.out.println("Opcode = " + opcode);
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
