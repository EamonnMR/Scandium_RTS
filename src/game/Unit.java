package game;

import commands.Instruction;

import data.Sprite;

public class Unit {
	Sprite s;
	int facing;
	int x, y;
	private int uid;
	boolean selected;
	
	public void insert(int uid){
		this.uid = uid;
	}
	
	public Unit(Sprite s, int facing, int x, int y) {
		this.s = s;
		this.facing = facing;
		this.x = x;
		this.y = y;
	}
	public void updateTick(Model parent){
		
	}
	public void updateIntermediate(){
		
	}
	public void draw(int camX, int camY){
		s.draw(facing, x + camX, y + camY);
	}

	public int getUid() {
		return uid;
	}

	public void giveInst(Instruction inst) {
		// TODO This is obviously wrong, only for testing.
		commands.Teleport move = (commands.Teleport) inst;
		this.x = move.getX();
		this.y = move.getY();
	}
	
}
