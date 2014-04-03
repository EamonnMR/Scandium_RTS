package game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import commands.Command;

public class Model {

	public PathGrid p;
	private Map<Integer, Unit> units;
	int unitCap = 0;
	Player[] players;
	int currentPlayer;
	
	public Model(PathGrid p, int numplayers, int currentPlayer) {
		this.p = p;
		units = new ConcurrentHashMap<Integer, Unit>();
		players = new Player[numplayers];
		this.currentPlayer = currentPlayer;
	}

	public int addUnit(Unit unit) {
		int uid = unitCap;
		units.put(uid, unit);
		unit.insert(uid);
		unitCap++;
		return uid;
	}
	public void tickUpdate(int dt, List<Command> cmds){
		test_move_cmds(cmds);
		//Thanks for explaining this one StackOverflow...
		for(Iterator<Unit> i = units.values().iterator(); i.hasNext();){
			i.next().updateTick(this);
		}
	}

	public void draw(int camX, int camY){
		for(Iterator<Unit> i = units.values().iterator(); i.hasNext();){
			i.next().draw(camX, camY);
		}
	}
	
	/**
	 * Returns a collection of all units inside the selected area.
	 */
	public Collection<Unit> areaQuerey(org.newdawn.slick.geom.Shape area){
		List<Unit> toSender = new ArrayList<Unit>();
		for(Unit i : units.values()){
			if(area.contains(i.x, i.y)){
				toSender.add(i);
			}
		}
		return toSender;
	}
	

	private void test_move_cmds(List<Command> cmds) {
		if(cmds != null){
			for(Command cmd : cmds){
				int[] unitz = cmd.getUnits();
				if(unitz!= null){
					for(int i :unitz){
						units.get(i).giveInst(cmd.getInstruction(),this);
					}
				}
			}
		}
	}
}