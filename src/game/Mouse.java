package game;

/**
 * Singleton for handling pointer input.
 * @author Eamonn
 *
 */
public class Mouse {
	static int NUM_MOUSE_BUTTONS = 5; //FIXME: What's this actual number?!!?
	
	int x, y;
	boolean[] buttons;
	private static Mouse instance;
	
	private Mouse(){
		buttons = new boolean[NUM_MOUSE_BUTTONS];
		x = 0;
		y = 0;
	}
	
	public static Mouse i(){
		if(instance == null){
			instance = new Mouse();
		}
		return instance;
	}
	
	public void updatePos(int x, int y){
		this.x += x;
		this.y += y;
	}
	
	public void mouseUp(int button){
		buttons[button] = false;
	}
	public void mouseDown(int button){
		buttons[button] = true;
	}
}
