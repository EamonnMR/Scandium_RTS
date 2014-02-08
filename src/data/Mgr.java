package data;

//Singleton that 

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Mgr {
	public Properties cfg, ports;

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
}