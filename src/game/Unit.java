package game;

import interfaceSlk.AbstractButton;

import java.util.LinkedList;
import java.util.Queue;

import commands.CommandCard;
import commands.Instruction;
import behavior.UnitState;
import data.Sprite;

public class Unit {
	Sprite s;
	int facing;
	//Yes, x and y outght to be private...
	public int x;
	public int y;
	private int uid;
	boolean selected;
	UnitState state;
	Queue<UnitState> stateQue;
	private CommandCard cc;
	
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
	
	public void enterState(UnitState state){
		this.state = state;
	}
	
	public void setFacing(int facing){
		this.facing = facing;
	}
	
	public Unit(Sprite s, int facing, int x, int y, CommandCard cc) {
		this.s = s;
		this.facing = facing;
		this.x = x;
		this.y = y;
		this.state = new behavior.Idle();
		this.cc = cc;
		stateQue = new LinkedList<UnitState>();
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

	public void giveInst(Instruction inst, Model m) {
		cc.actuate(inst, this, m);
	}

	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public AbstractButton getButton(int btn){
		return cc.getButton(btn);
	}
	
}
