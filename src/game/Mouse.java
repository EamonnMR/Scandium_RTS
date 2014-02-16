package game;

/**
 * Singleton for handling mouse input.
 * @author Eamonn
 *
 */
public class Mouse {
	static int NUM_MOUSE_BUTTONS = 5; //FIXME: What's this actual number?!!?
	
	int x, y, maxX, maxY;
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
	
	public void updatePos(int nx, int ny){
		x += nx;
		if(x < 0){
			x =0;
		} else if(x > maxX){
			x = maxX;
		}
		y += ny;
		if(y < 0){
			y = 0;
		} else if(y > maxY){
			y = maxY;
		}
	}
	
	public void mouseUp(int button){
		buttons[button] = false;
	}
	public void mouseDown(int button){
		buttons[button] = true;
	}
	
	public void setScreenSize(int maxX, int maxY){
		this.maxX = maxX;
		this.maxY = maxY;
	}
}
