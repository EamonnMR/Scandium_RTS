package instructions;

import java.util.LinkedList;
import java.util.List;

/** Commands are the key.
 * 'orderedunits' has an array of UIDs of units being sent the command.
 * instruction i contains the instruction itself-
 * @author Eamonn
 *
 */
public class Command {
	int[] orderedUnits;
	Instruction inst;
	public Command(int[] orderedUnits, Instruction inst) {
		this.orderedUnits = orderedUnits;
		this.inst = inst;
	}
	
	public Command(List<Integer> inp){
		//Get the unit list first
		int numUnits = inp.remove(0);
		orderedUnits = new int[numUnits];
		for(int i = 0; i < numUnits; i++){
			orderedUnits[i] = inp.remove(0);
		}
		//Then get the instruction
		inst = Instruction.fromInts(inp);
	}
	
	public List<Integer> toInts(){
		List<Integer> toSender = new LinkedList<Integer>();
		toSender.add(orderedUnits.length);
		for(int i : orderedUnits){
			toSender.add(i);
		}
		
		toSender.addAll(inst.toInts());
		
		return toSender;
	}
	
	public int[] getUnits(){
		return orderedUnits;
	}
	public Instruction getInstruction() {
		return inst;
	}
}