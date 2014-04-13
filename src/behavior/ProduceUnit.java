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
		m.incrResource(u.owner, -1 * data.Mgr.i().units[unitType].price);
		m.addUnit(data.Mgr.i().getUnit(u.x, u.y, 0, unitType, u.owner, new behavior.Idle()));
		u.nextState();
	}
	@Override
	public void updateInterm(Model m, Unit u, int dt) {
		//This'll come into play when we have a loading bar working.
	}
}
