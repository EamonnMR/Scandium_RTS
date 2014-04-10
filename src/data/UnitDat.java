package data;

import cards.CommandCard;

public class UnitDat {
	int sprite;
	public UnitDat(int sprite, float speed, CommandCard cc, boolean canGather) {
		super();
		this.sprite = sprite;
		this.cc = cc;
		this.canGather = canGather;
	}
	
	private cards.CommandCard cc;
	public float speed;
	public boolean canGather;
	
	public CommandCard getCC() {
		// TODO Auto-generated method stub
		return cc;
	}
}
