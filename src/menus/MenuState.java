package menus;

/* Abstract class for GUI pages. */

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.BasicGameState;

/** A quick way to make GUI pages. 
 * 
 * @author Eamonn
 *
 */
public abstract class MenuState extends BasicGameState {

	Interactive[] interactives;
	Point mousePos = new Point(0,0);
	boolean mouseState;

	@Override
	public void render(GameContainer game, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.clear();
		for(Interactive i : interactives){
			i.render(game, g);
		}
		if(mousePos == null){
			mousePos = new Point(0,0);
		}
		g.draw(new Rectangle(mousePos.getX(), mousePos.getY(), 5,5));
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		if(mousePos == null){
			mousePos = new Point(0,0);
		}
		for(Interactive i : interactives){
			i.update(mousePos, mouseState);
		}
	}

	/*
	 * Mouse interface methods. It might be possible to make due with less of them...
	 */
	
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		mousePos = new Point(newx + mousePos.getX(), newy + mousePos.getY());
		super.mouseMoved(oldx, oldy, newx, newy);
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		mousePos = new Point(newx + mousePos.getX(), newy + mousePos.getY());
		super.mouseMoved(oldx, oldy, newx, newy);
	}
	@Override
	public void mousePressed(int button, int x, int y) {
		mouseState = true;
		super.mousePressed(button, x, y);
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		mouseState = false;
		super.mouseReleased(button, x, y);
	}
	
	
}
