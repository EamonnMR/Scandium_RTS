package menus;

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
	
	//FIXME: game should not need to be an argument here;
	//we might be able to get away with removing TextBox altogether.
	public abstract void render(GameContainer game, Graphics g);
}