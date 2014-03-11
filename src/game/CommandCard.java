package game;


/**
 * Class representing the set of buttons a unit offers on an interface.
 * 
 * This version is the template with no responses to anything.
 * It's not abstract because not everthing needs to be filled out;
 * for example, ground units won't respond to clicking on unpassable terrain.
 * @author Eamonn
 *
 */
public class CommandCard {
	/**
	 * Right-click command sent to coordinates (path found)
	 */
	public void rightClickEmpty(int posX, int posY){};
	/**
	 * Right click command sent to coordinates (no path found)
	 */
	public void rightClickUnpass(int posX, int posY){};
	
	/** 
	 * Right click command sent specifying an allied unit
	 */
	public void rightClickAlly(int AllyID){};
	
	/**
	 * Right click command sent specifying an enemey unit
	 */
	public void rightClickEnemey(int enemeyID){};
}
