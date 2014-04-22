package instructions;

public class Relocate extends Instruction {

	int x, y;
	
	public Relocate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Relocate(String code){
		String c = "";
		String num = "";
		while(!c.equals(",")){
			num += c;
			c = code.substring(0,1);
			code = code.substring(1);
		}
		c = "";
		x = Integer.parseInt(num);
		num = "";
		while(!c.equals(";")){
			num += c;
			c = code.substring(0,1);
			code = code.substring(1);
		}
		y = Integer.parseInt(num);
	}

	@Override
	String toCode() {
		// TODO Auto-generated method stub
		return 0 + ":" + x + "," +  y; //Should I add a ';' at the end of this?
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
