package instructions;

import java.util.Collection;
import java.util.LinkedList;

public class RequisitionUnit extends Instruction{
	public int unitToProduce;

	public RequisitionUnit(int unitToProduce){
		this.unitToProduce = unitToProduce;
	}
	
	public Collection<? extends Integer> toInts() {
		LinkedList<Integer> toSender = new LinkedList<Integer>();
		toSender.add(2);
		toSender.add(unitToProduce);
		return toSender;
	}
}
