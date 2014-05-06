package test;

import java.io.FileNotFoundException;
import java.io.IOException;

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
		try {
			data.Mgr.i().loadInis();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Setup.setup(data.Mgr.i().mapTable.getProperty("0"), dummyR, dummyC, 2);
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
