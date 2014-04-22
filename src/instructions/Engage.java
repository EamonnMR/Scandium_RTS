package instructions;

import java.util.Collection;
import java.util.LinkedList;

public class Engage extends Instruction{
	
	public int target;
	
	Engage(int target){
		this.target = target;
	}

	@Override
	public Collection<? extends Integer> toInts() {
		LinkedList<Integer> toSender = new LinkedList<Integer>();
		toSender.add(2);
		toSender.add(target);
		return toSender;
	}
}
