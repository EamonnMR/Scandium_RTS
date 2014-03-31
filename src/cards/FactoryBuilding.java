package cards;

import game.Model;
import game.Unit;

import behavior.ProduceUnit;
import commands.Instruction;
import commands.RequisitionUnit;

public class FactoryBuilding extends CommandCard{

	public game.Hud.Button getButton(){
		return data.Mgr.i().getButton(0);
	}
	
	public boolean movesByPath() {
		return false;
	}
	
	public void actuate(Instruction cmd, Unit u, Model m) {
		// TODO Auto-generated method stub
		if(cmd.getClass() == RequisitionUnit.class){
			u.addState(new ProduceUnit(
					((RequisitionUnit) cmd).unitToProduce
					)); //
		}
	}
}
