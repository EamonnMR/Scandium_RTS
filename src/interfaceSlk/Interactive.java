package interfaceSlk;

import org.newdawn.slick.geom.Rectangle;

public abstract class Interactive {
	public abstract Rectangle getBox();
	
	public abstract void MouseDown(int mx, int my);
	
	public abstract void MouseUp(int mx, int my);
	
	public abstract void KeyPress(int key);
}