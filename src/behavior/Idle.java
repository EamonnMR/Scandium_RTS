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
	public void update(Model m, Unit u) {
		//nothing
	}

	@Override
	public void updateInterm(Model m, Unit u, int dt) {
		//Nothing :D
	}

}
