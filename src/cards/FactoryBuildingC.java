package cards;

import game.Model;
import game.Unit;

import org.newdawn.slick.util.pathfinding.Path;

import commands.Instruction;
import commands.RequisitionUnit;
import commands.Teleport;

public class FactoryBuildingC {

	public boolean movesByPath() {
		return true;
	}
	
	public void actuate(Instruction cmd, Unit u, Model m) {
		// TODO Auto-generated method stub
		if(cmd.getClass() == RequisitionUnit.class){
			
		}
	}
}
