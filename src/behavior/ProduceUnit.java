package behavior;

import game.Model;
import game.Unit;

public class ProduceUnit extends UnitState{
	int unitType;
	public ProduceUnit(int unitType){
		this.unitType = unitType;
	}
	@Override
	public void update(Model m, Unit u) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateInterm(Model m, Unit u, int dt) {
		// TODO Auto-generated method stub
		
	}
	
	//FIXME: Once the dataman's unit creation abilities are a thing, make this actually spawn a unit.
}
