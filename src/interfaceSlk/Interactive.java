package interfaceSlk;

import org.newdawn.slick.geom.Point;

public abstract class Interactive {
	public abstract void update(Point mousePos, boolean mouseState);
	
	public abstract void render();
}