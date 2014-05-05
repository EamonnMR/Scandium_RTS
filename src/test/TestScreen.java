package test;

import game.Setup;
import menus.MenuState;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class TestScreen extends MenuState {

	@Override
	public void render(GameContainer game, StateBasedGame arg1, Graphics g)
			throws SlickException {
		//This never takes time, so it should not last long.
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException{
		LocalRcv dummyR = new LocalRcv();
		LocalCmd dummyC = new LocalCmd(dummyR);
		Setup.setup("res/RageValley.tmx", dummyR, dummyC, 1);
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
