package commands;

import java.util.LinkedList;

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
	public Command(String code) {
		LinkedList<Integer> unitl = new LinkedList<Integer>();
		//Parse the units from strings...
		String c = "";
		String num = "";
		while(!c.equals(":")){
			c = code.substring(0,1);
			code = code.substring(1);
			if(c.equals(",")){
				unitl.push(Integer.parseInt(num));
			} else {
				num += c;
			}
		}
		
		//The remainder of the code is the instruction string, we let Instruction.fromCode handle it.
		inst = Instruction.fromCode(code);
		
		//Turn it into an int array
		int[] orderedUnits = new int[unitl.size()];
		for(int i = 0; i > unitl.size(); i ++){
			orderedUnits[i] = unitl.get(i);
		}
	}
	public String toCode(){
		//FIXME: This could be more efficient if it used a StringBuilder rather than
		//just a string.
		String toSender = "";
		for(int i : orderedUnits){
			toSender += Integer.toString(i)+",";
		}
		toSender += ":";
		return toSender +inst.toCode() + ";";
	}
}