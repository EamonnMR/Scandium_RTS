package behavior;

import game.Model;
import game.Unit;

public class AbsoluteMove extends UnitState {
	int startX, startY, endX, endY;

	@Override
	public void update(Model m, Unit u) {
		//Interpolate between current pos & goal
	}

	@Override
	public void updateInterm(Model m, Unit u, int dt) {
		//Finer grained interpolation, ALLOWED TO USE FLOAT
	}
	
}
