package game;

import java.util.Collection;

public class Util {
	/**
	 * Turns a collection of Units into an array of those unit's UIDs.
	 */
	public static int[] unitListToUIDArray(Collection<Unit> selectedUnits) {
		int[] toSender = new int[selectedUnits.size()];
		int i = 0;
		for(Unit u : selectedUnits){
			toSender[i] = u.getUid();
			i++;
		}
		return toSender;
	}
}
