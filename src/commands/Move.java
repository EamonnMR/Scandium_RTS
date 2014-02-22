package commands;

public class Move extends Instruction {

	int x, y;
	
	public Move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	String toCode() {
		// TODO Auto-generated method stub
		return "move" + x + y;
	}

}
