package game;

import java.util.Queue;

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
	Queue<UnitState> stateQue;
	
	public void insert(int uid){
		this.uid = uid;
	}
	
	public void nextState(){
		state = stateQue.poll();
		if(state == null){
			state = new behavior.Idle();
		}
	}
	
	public void addState(UnitState s){
		stateQue.offer(s);
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
		state.update(parent, this);
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
		this.state = new behavior.FollowPath(pg.getPath(Math.round((x + 20) / 40), Math.round((y + 20 )/40), (int)(move.getX() / 40), (int)(move.getY()/40)), 2f, 0f);
	}

	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
