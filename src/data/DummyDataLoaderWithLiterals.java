package data;

import org.newdawn.slick.SlickException;

import commands.Command;
import game.CmdSender;
import game.Hud;
import game.Unit;

public class DummyDataLoaderWithLiterals {
	public Sprite[][] sprites;
	public UnitDat[] units;
	public Hud.Button[] buttons;
	
	public DummyDataLoaderWithLiterals() throws SlickException {
		sprites = new Sprite[3][];
		
		sprites[0] = Mgr.i().loadMultiSpr(
				"res/graphics/danC/tank.png",
				48, 56, 8, 1, -24, -27, 3);
		
		sprites[1] = Mgr.i().loadMultiSpr(
				"res/graphics/danC/factory.png",
				80, 82, 15, 1, -2, -4, 3);
		
		sprites[2] = Mgr.i().loadMultiSpr(
				"res/graphics/danC/button.png",
				42, 42, 3, 1, 0, 0, 1);
		
		units = new UnitDat[2];
		
		units[0] = new UnitDat(
				0, new cards.GroundCombatUnit());
		units[1] = new UnitDat(
				1, new cards.FactoryBuilding());
		
		buttons = new Hud.Button[1];
		
		//This is a pain-I really need to figure out how to pass the sprite into that anonclass.
		
		buttons[0] = new Hud.Button(){
			/*
			 * Wow so hack such poor coding
			 */
			@Override
			public void pressed(Hud h, CmdSender c) {
				c.rcv(new Command(h.getSelectedUnits(), new commands.RequisitionUnit(0)));
			}
		};
		//Ok maybe it's not so bad as long as it's standardized...
		buttons[0].setSpr(sprites[2][0]);
	
	}
}
