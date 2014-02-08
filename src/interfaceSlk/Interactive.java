package interfaceSlk;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

/**
 * Defines how a menu state interacts with GUI elements.
 * @author Eamonn
 *
 */
public abstract class Interactive {
	public abstract void update(Point mousePos, boolean mouseState);
	
	public abstract void render(GameContainer game, Graphics g);
}