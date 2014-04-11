package game;

import gui.Hud;

import java.util.LinkedList;
import java.util.Queue;

import cards.CommandCard;
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
	public int owner;
	private float speed;
	public int hitPoints;
	public boolean canGather;
	
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
	
	public Unit(Sprite s, int facing, int x, int y, int owner, float speed, CommandCard cc, UnitState initialState, boolean canGather) {
		this.s = s;
		this.facing = facing;
		this.x = x;
		this.y = y;
		this.state = initialState;
		this.cc = cc;
		stateQue = new LinkedList<UnitState>();
		this.owner = owner;
		this.canGather = canGather;
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
	
	public Hud.Button getButton(int btn){
		return cc.getButton(btn);
	}

	public float getRadius() {
		return 1 + (s.getWidth() / 2);
	}

	public float getSpeed() {
		return speed;
	}
	
}
