package interfaceSlk;

import game.LocalCmd;
import game.LocalRcv;
import game.PathGrid;
import game.Unit;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import commands.GroundCombatUnitC;
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
		
		
		game.PlayState.i().sendInfo(m, t, pg, new Sprite(
				new Image("res/graphics/danC/mouse.png"),
				40, 40, 14, 1, 0, 0, 0), 
				new LocalCmd(dummyR), dummyR
				);
		
		m.addUnit(new Unit(
					new Sprite(
					new Image("res/graphics/danC/tank.png"),
					48, 56, 8, 1, -24, -27, 0)
					, 0, 1000, 160,
					new GroundCombatUnitC())
				);
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
