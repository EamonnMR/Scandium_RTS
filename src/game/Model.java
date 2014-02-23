package game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.tiled.TiledMap;

import commands.Command;

public class Model {

	private TiledMap m;
	private PathGrid p;
	private Map<Integer, Unit> units;
	int unitCap = 0;
	
	public Model(TiledMap m, PathGrid p) {
		this.m = m;
		this.p = p;
		units = new HashMap<Integer, Unit>();
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
		for(Integer i : units.keySet()){
			units.get(i).updateTick(this);
		}
	}

	public void draw(int camX, int camY){
		for(Integer i : units.keySet()){
			units.get(i).draw(camX, camY);
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
						units.get(i).giveInst(cmd.getInstruction());
					}
				}
			}
		}
	}
}