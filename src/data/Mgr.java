package data;

//Singleton that 

import game.Hud;
import game.Unit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


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
		DummyDataLoaderWithLiterals d = new 
				DummyDataLoaderWithLiterals();
		sprites = d.sprites;
		units = d.units;
		buttons = d.buttons;
	}
	
	public Sprite getSpr(int index, int sub){
		return sprites[index][sub];
	}
	
	public Sprite getSpr(int index){
		return sprites[index][0];
	}
	
	public Sprite[] loadMultiSpr(String srcimg, int xSize, int ySize, int xFrames, int yFrames, int xOffset, int yOffset, int multisize) throws SlickException{
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