package game;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import net.CmdSender;

import org.newdawn.slick.util.pathfinding.Path;

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
	
	//I really need to find a home for this...
	public static void issueMoveCmd(int x, int y, Model m, PathGrid pg, int[] sel, CmdSender sndr){
		if(sel.length > 0){
			
			List<Unit> directPathUnits = new LinkedList<Unit>();
			int destX = x / 40;
			int destY = y / 40;
			Path path;
			for(int i : sel){
				Unit u = m.getUnit(i);
				path = pg.getPath(u.x / 40, u.y / 40, 
						destX, 
						destY);
				if(path != null){
					directPathUnits.add(u);
				}
			}
			
			sndr.rcv(new commands.Command(Util.unitListToUIDArray(directPathUnits),
					new commands.Relocate(x , y)
			));
		}
	}
}
