package interfaceSlk;

import game.Hud;
import game.LocalCmd;
import game.LocalRcv;
import game.PathGrid;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import data.Sprite;

public class TestScreen extends MenuState {

	@Override
	public void render(GameContainer game, StateBasedGame arg1, Graphics g)
			throws SlickException {
		//
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException{
		// TODO Auto-generated method stub
		TiledMap t = new TiledMap("res/RageValley.tmx");
		PathGrid pg = new PathGrid(t);
		game.Model m = new game.Model(t, pg, 2, 0);
		LocalRcv dummyR = new LocalRcv();
		LocalCmd dummyC = new LocalCmd(dummyR);
		
		data.Mgr.i().faux_load_data();
		
		game.PlayState.i().sendInfo(m, t, pg, new Sprite(
				new Image("res/graphics/danC/mouse.png"),
				40, 40, 14, 1, 0, 0, 0), 
				dummyC, dummyR,
				new Hud(new Image("res/graphics/danC/GUI.png"), 0, 540, dummyC )
				);
		
		m.addUnit(data.Mgr.i().getUnit(1000, 160, 0, 1, 0));
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
