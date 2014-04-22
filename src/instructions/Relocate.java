package instructions;

import java.util.Collection;
import java.util.LinkedList;

public class Relocate extends Instruction {

	int x, y;
	
	public Relocate(int x, int y) {
		this.x = x;
		this.y = y;
	}


	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	@Override
	public Collection<? extends Integer> toInts() {
		LinkedList<Integer> toSender = new LinkedList<Integer>();
		toSender.add(0);
		toSender.add(x);
		toSender.add(y);
		return toSender;
	}
}
