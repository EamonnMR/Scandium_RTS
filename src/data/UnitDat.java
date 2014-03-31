package data;

import cards.CommandCard;

public class UnitDat {
	int sprite;
	public UnitDat(int sprite, CommandCard cc) {
		super();
		this.sprite = sprite;
		this.cc = cc;
	}
	
	private cards.CommandCard cc;
	
	public CommandCard getCC() {
		// TODO Auto-generated method stub
		return cc;
	}
}
