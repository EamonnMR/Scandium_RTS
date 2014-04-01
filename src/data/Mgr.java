package data;

//Singleton that 

import game.CmdSender;
import game.Hud;
import game.PlayerMouse;
import game.Unit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import commands.Command;

public class Mgr {
	public Properties cfg, ports;
	private Sprite[][] sprites;
	private UnitDat[] units;
	private Hud.Button[] buttons;

	public void loadInis() throws FileNotFoundException, IOException{
		cfg = loadProps("prefs.ini");
		ports = loadProps("ports.ini");
	}
	
	private static Properties loadProps(String loc) throws FileNotFoundException, IOException {
		Properties toSender = new Properties();
		toSender.load(new FileInputStream(loc));
		return toSender;
	}
	/*Mechanics/hoop jumping to make it
	 * a proper singleton, visible to the
	 * entire program but saved from
	 * tampering;
	 */
	private Mgr(){
	}
	
	private static Mgr instance;
	
	/** 
	 * Get the global data.Mgr instance.
	 */
	public static Mgr i(){
		if(instance == null){
			instance = new Mgr();
		}
		return instance;
	}
	
	public Unit getUnit(int x, int y, int facing, int type, int owner){
		UnitDat d = units[type];
		return new Unit(sprites[d.sprite][owner], facing, x, y, owner, d.getCC());
	}
	
	public void faux_load_data() throws SlickException{
		sprites = new Sprite[3][];
		
		sprites[0] = loadMultiSpr(
				"res/graphics/danC/tank.png",
				48, 56, 8, 1, -24, -27, 3);
		
		sprites[1] = loadMultiSpr(
				"res/graphics/danC/factory.png",
				80, 82, 15, 1, -2, -4, 3);
		
		sprites[2] = loadMultiSpr(
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
		buttons[0].setSpr(getSpr(2));
	}
	
	public Sprite getSpr(int index, int sub){
		return sprites[index][sub];
	}
	
	public Sprite getSpr(int index){
		return sprites[index][0];
	}
	
	private Sprite[] loadMultiSpr(String srcimg, int xSize, int ySize, int xFrames, int yFrames, int xOffset, int yOffset, int multisize) throws SlickException{
		Sprite[] toSender = new Sprite[multisize];
		Image img = new Image(srcimg);
		for(int i = 0; i < multisize; i++){
			toSender[i] = new Sprite(img, xSize, ySize, xFrames, yFrames, xOffset, yOffset, i );
		}
		return toSender;
	}

	public Hud.Button getButton(int index) {
		return buttons[index];
	}
}