package commands;

public class Teleport extends Instruction {

	int x, y;
	
	public Teleport(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Teleport(String code){
		String c = "";
		String num = "";
		while(!c.equals(",")){
			c = code.substring(0,1);
			code = code.substring(1);
			num += c;
		}
		x = Integer.parseInt(num);
		num = "";
		while(c.length() > 0){
			c = code.substring(0,1);
			code = code.substring(1);
			num += c;
		}
		y = Integer.parseInt(num);
	}

	@Override
	String toCode() {
		// TODO Auto-generated method stub
		return "tp" + x + "," + y;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
