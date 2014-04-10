package data;

import cards.CommandCard;

public class UnitDat {
	int sprite;
	public UnitDat(int sprite, float speed, CommandCard cc) {
		super();
		this.sprite = sprite;
		this.cc = cc;
	}
	
	private cards.CommandCard cc;
	public float speed;
	
	public CommandCard getCC() {
		// TODO Auto-generated method stub
		return cc;
	}
}
