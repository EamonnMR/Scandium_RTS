package commands;

public class RequisitionUnit extends Instruction{
	public int unitToProduce;

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
		// TODO Auto-generated method stub
		return 1 + ":" + unitToProduce;
	}
}
