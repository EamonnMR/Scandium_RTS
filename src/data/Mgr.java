package data;

//Singleton that 

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Mgr {
	public Properties cfg, ports;
	private UnitDat[] unitTypes;
	private Sprite[][] sprites;

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
	
	public void makeUnit(int x, int y, int type, int side){
		//Ok, I guess we need to add this hmm
	}
	
	public void faux_load_data() throws SlickException{
		sprites = new Sprite[2][];
		sprites[0] = loadMultiSpr(
			"res/graphics/danC/tank.png",
			48, 56, 8, 1, -24, -27, 3);
	}
	
	public Sprite getSpr(int index, int sub){
		return sprites[index][sub];
	}
	
	private Sprite[] loadMultiSpr(String srcimg, int xSize, int ySize, int xFrames, int yFrames, int xOffset, int yOffset, int multisize) throws SlickException{
		Sprite[] toSender = new Sprite[multisize];
		Image img = new Image(srcimg);
		for(int i = 0; i < multisize; i++){
			toSender[i] = new Sprite(img, xSize, ySize, xFrames, yFrames, xOffset, yOffset, i );
		}
		return toSender;
	}
}