package interfaceSlk;

/* Abstract class for GUI pages. */

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.BasicGameState;


public abstract class MenuState extends BasicGameState {

	Interactive[] interactives;
	Point mousePos;
	boolean mouseState;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		for(Interactive i : interactives){
			i.render();
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		for(Interactive i : interactives){
			i.update(mousePos, mouseState);
		}
	}

}
