package cards;

import instructions.Instruction;
import instructions.RequisitionUnit;
import game.Model;
import game.Unit;
import gui.Hud;
import behavior.ProduceUnit;

public class FactoryBuilding extends CommandCard{
	
	public FactoryBuilding(Hud.Button[] earlyAccessToButtons){
		super(earlyAccessToButtons);
		btns[0] = earlyAccessToButtons[0];
		btns[1] = earlyAccessToButtons[1];
		btns[2] = earlyAccessToButtons[2];
		btns[3] = earlyAccessToButtons[5];
	}
	
	public boolean movesByPath() {
		return false;
	}
	
	public void actuate(Instruction cmd, Unit u, Model m) {
		if(cmd.getClass() == RequisitionUnit.class){
			u.enterState(new ProduceUnit(
					((RequisitionUnit) cmd).unitToProduce
					)); 
		}
	}
}
