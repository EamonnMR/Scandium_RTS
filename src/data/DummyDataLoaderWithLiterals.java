package data;

import org.newdawn.slick.SlickException;

import commands.Command;
import game.CmdSender;
import game.Model;
import game.PathGrid;
import game.Util;
import gui.Hud;

/**
 * This class stores most of the game data (I haven't parameterized CommandCards yet,
 * becayse I'm still feeling out how they're going to work). This will be replaced
 * with JASON (or possibly some other data language).
 */

public class DummyDataLoaderWithLiterals {
	public Sprite[][] sprites;
	public UnitDat[] units;
	public Hud.Button[] buttons;
	
	public DummyDataLoaderWithLiterals() throws SlickException {
		
		//Array literals can only be used in initializers.
		Sprite[][] spritesd = {
				//0
				Mgr.i().loadMultiSpr(
				"res/graphics/danC/tank.png",
				48, 56, 8, 1, -24, -27, 3),
		
				Mgr.i().loadMultiSpr(
				"res/graphics/danC/factory.png",
				80, 82, 15, 1, -2, -4, 3),
		
				loadButtonSpr(
				"res/graphics/danC/buttons/tank.png"
				),
		
				loadButtonSpr(
				"res/graphics/danC/buttons/tank2.png"
				),
		
				loadButtonSpr(
				"res/graphics/danC/buttons/artil.png"
				),
				
				//5
				Mgr.i().loadMultiSpr(
				"res/graphics/danC/tank2.png",
				40, 40, 8, 1, -20, -20, 3),
				
				Mgr.i().loadMultiSpr(
				"res/graphics/danC/artil.png",
				40, 80, 8, 1, -20, -60, 3),
				
				loadButtonSpr(
				"res/graphics/danC/buttons/attack.png"
				),
				
				loadButtonSpr(
				"res/graphics/danC/buttons/moveBtn.png"
				),
				loadButtonSpr(
				"res/graphics/danC/buttons/worker.png"
				),
				//10
				Mgr.i().loadMultiSpr(
				"res/graphics/danC/worker.png",
				38, 38, 8, 1, -19, -19, 3),
				
				Mgr.i().loadMultiSpr(
				"res/graphics/danC/moveCursor.png",
				30,30,3,1,-14,-14,1)
		};
		sprites = spritesd;
		
		
		buttons = new Hud.Button[1];
		
		//This is a pain-I really need to figure out how to pass the sprite into that anonclass.
		
		Hud.Button[] buttonsd = {
				(new Hud.Button(){
					@Override
					public void pressed(Hud h, CmdSender c, gui.PlayerMouse ms) {
						c.rcv(new Command(h.getSelectedUnits(), new commands.RequisitionUnit(0)));
					}
					}).setSpr(sprites[2][0]),
				(new Hud.Button(){
					@Override
					public void pressed(Hud h, CmdSender c, gui.PlayerMouse ms) {
						c.rcv(new Command(h.getSelectedUnits(), new commands.RequisitionUnit(2)));
					}
					}).setSpr(sprites[3][0]),
				(new Hud.Button(){
					@Override
					public void pressed(Hud h, CmdSender c,gui.PlayerMouse ms) {
						c.rcv(new Command(h.getSelectedUnits(), new commands.RequisitionUnit(3)));
					}
					}).setSpr(sprites[4][0]),
				(new Hud.Button(){
					@Override
					public void pressed(Hud h, CmdSender c, gui.PlayerMouse ms) {
						//Set mouse mode to select a thing to attack
					}
					}).setSpr(sprites[7][0]),
				(new Hud.Button(){
					@Override
					public void pressed(Hud h, CmdSender c, gui.PlayerMouse ms) {
						ms.setMode(
								new gui.BasicLocater(){

									@Override
									public void execute(int x, int y,
											CmdSender sndr, int[] sel, Model m) {
										Util.issueMoveCmd(x, y, m, m.getPg(), sel, sndr);
									}

									@Override
									public int evaluate(int x, int y, Model m,
											PathGrid pg) {
										//FIXME: This should look at the pathgrid and establish
										//if it's pathable.
										return 0;
									}
							
						}.setSpr(sprites[11][0]));
					}
					}).setSpr(sprites[8][0]),
				//5
				(new Hud.Button(){
					@Override
					public void pressed(Hud h, CmdSender c, gui.PlayerMouse ms) {
						c.rcv(new Command(h.getSelectedUnits(), new commands.RequisitionUnit(4)));
					}
					}).setSpr(sprites[9][0]),
		};
		buttons = buttonsd;
		
		
		UnitDat[] unitsd = {
				new UnitDat(
				0, new cards.GroundCombatUnit(buttons)),
				new UnitDat(
				1, new cards.FactoryBuilding(buttons)),
				new UnitDat(
				5, new cards.GroundCombatUnit(buttons)),
				new UnitDat(
				6, new cards.GroundCombatUnit(buttons)),
				new UnitDat(
				10, new cards.Worker(buttons)),
		};
		units = unitsd;
		
	}
	
	private Sprite[] loadButtonSpr(String buttonFile) throws SlickException{
		return Mgr.i().loadMultiSpr(
				buttonFile,
				42, 42, 3, 1, 0, 0, 1);
	}
}
