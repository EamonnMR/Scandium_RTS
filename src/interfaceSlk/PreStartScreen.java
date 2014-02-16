package interfaceSlk;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import game.PathGrid;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import data.Sprite;

public class PreStartScreen extends MenuState {

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
		try {
			game.PlayState.i().sendInfo(new game.Model(), t, new PathGrid(t), new Sprite(
					new Image( new FileInputStream("res/graphics/danC/mouse.png"), "0", false),
					40, 40, 14, 1, 0, 0));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
