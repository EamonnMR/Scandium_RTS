package game;

import commands.Instruction;
import behavior.UnitState;
import data.Sprite;

public class Unit {
	Sprite s;
	int facing;
	int x, y;
	private int uid;
	boolean selected;
	UnitState state;
	
	public void insert(int uid){
		this.uid = uid;
	}
	
	public void setFacing(int facing){
		this.facing = facing;
	}
	
	public Unit(Sprite s, int facing, int x, int y) {
		this.s = s;
		this.facing = facing;
		this.x = x;
		this.y = y;
		this.state = new behavior.Idle();
	}
	public void updateTick(Model parent){
		state = state.update(parent, this);
	}
	public void updateIntermediate(Model parent, int dt){
		state.updateInterm(parent, this, dt);
	}
	public void draw(int camX, int camY){
		s.draw(facing, x + camX, y + camY);
	}

	public int getUid() {
		return uid;
	}

	public void giveInst(Instruction inst, Model m, PathGrid pg) {
		// TODO This is obviously wrong, only for testing.
		commands.Teleport move = (commands.Teleport) inst;
		this.state = new behavior.FollowPath(pg.getPath((int)(x / 40), (int)(y/40), (int)(move.getX() / 40), (int)(move.getY()/40)), 1);
	}

	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
