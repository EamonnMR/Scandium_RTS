package data;

import cards.CommandCard;

public class UnitDat {
	int sprite;
	public int price;
	private cards.CommandCard cc;
	public float speed;
	public boolean canGather;
	
	public UnitDat(int sprite, float speed, CommandCard cc, boolean canGather, int price) {
		super();
		this.sprite = sprite;
		this.cc = cc;
		this.canGather = canGather;
		this.price = price;
	}
	public CommandCard getCC() {
		// TODO Auto-generated method stub
		return cc;
	}
}
