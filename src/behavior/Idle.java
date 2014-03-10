package behavior;

import game.Model;
import game.Unit;

/**
 * This state idles a unit. It does nothing.
 * @author Eamonn
 *
 */

public class Idle extends UnitState {

	@Override
	public UnitState update(Model m, Unit u) {
		return this;
	}

	@Override
	public void updateInterm(Model m, Unit u, int dt) {
		//Nothing :D
	}

}
