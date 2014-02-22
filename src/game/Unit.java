package game;

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
		// TODO Auto-generated method stub
		return uid;
	}
	
}
