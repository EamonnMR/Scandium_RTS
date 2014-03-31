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
		System.out.println("ProduceUnit update'd");
		m.addUnit(data.Mgr.i().getUnit(u.x, u.y, 90, unitType, u.owner));
		u.nextState();
	}
	@Override
	public void updateInterm(Model m, Unit u, int dt) {
		// TODO Auto-generated method stub
		
	}
}
