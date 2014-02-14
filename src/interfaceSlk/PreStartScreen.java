package interfaceSlk;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class PreStartScreen extends MenuState {

	@Override
	public void render(GameContainer game, StateBasedGame arg1, Graphics g)
			throws SlickException {
		//
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		game.PlayState.i().sendInfo(new game.Model(), new TiledMap("res/RageValley.tmx"));
		System.out.println("Map loaded successfully");
		arg1.enterState(6);
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 5;
	}
}
