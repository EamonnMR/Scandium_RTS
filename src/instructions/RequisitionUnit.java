package instructions;

import java.util.Collection;
import java.util.LinkedList;

public class RequisitionUnit extends Instruction{
	public int unitToProduce;

	public RequisitionUnit(int unitToProduce){
		this.unitToProduce = unitToProduce;
	}
	
	public RequisitionUnit(String code) {
		String c, num;
		c = "";
		num = "";
		while(!c.equals(";")){
			num += c;
			c = code.substring(0,1);
			code = code.substring(1);
		}
		unitToProduce = Integer.parseInt(num);
	}

	@Override
	String toCode() {
		return 1 + ":" + unitToProduce;
	}
	
	public Collection<? extends Integer> toInts() {
		LinkedList<Integer> toSender = new LinkedList<Integer>();
		toSender.add(2);
		toSender.add(unitToProduce);
		return toSender;
	}
}
