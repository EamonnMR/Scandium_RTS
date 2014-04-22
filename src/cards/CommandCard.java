package cards;

import game.Model;
import game.Unit;
import gui.Hud;
import gui.Hud.Button;


/**
 * Class representing the set of buttons a unit offers on an interface,
 * how the unit reacts to incoming instructions, and what actions (such
 * as clicking, hotkeys (not yet implemented) cause the unit to be sent
 * commands.
 * 
 * This version is the template with no responses to anything.
 * It's not abstract because not everthing needs to be filled out;
 * for example, ground units won't respond to clicking on unpassable terrain.
 * @author Eamonn
 *
 */
public class CommandCard {
	
	Hud.Button[] btns;
	
	//This is so subclasses can use buttons;
	//this merely initializes the (optional) buttons array.
	public CommandCard(Button[] earlyAccessToButtons){
		btns = new Hud.Button[9];
	}
	
	public boolean movesByPath(){
		return false;
	}
	public boolean movesAnywhere(){
		return false;
	}
	public boolean deploys(){
		return false;
	}
	
	public boolean defaultOnAlly(){
		return false;
	}
	
	public boolean defaultOnEnemey(){
		return false;
	}
	
	public int deploySprite(){
		return 0;
	}
	
	public void actuate(instructions.Instruction cmd, Unit u, Model m){
		
	}
	
	public gui.Hud.Button getButton(int i){
		return btns[i];
	}
}
