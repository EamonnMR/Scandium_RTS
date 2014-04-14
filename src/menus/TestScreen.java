package menus;

import game.PathGrid;
import gui.Hud;
import net.LocalCmd;
import net.LocalRcv;

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
		game.Model m = new game.Model(pg, 3);
		LocalRcv dummyR = new LocalRcv();
		LocalCmd dummyC = new LocalCmd(dummyR);
		
		data.Mgr.i().faux_load_data();
		
		game.PlayState.i().sendInfo(m, t, pg, new Sprite(
				new Image("res/graphics/danC/mouse.png"),
				40, 40, 14, 1, 0, 0, 0), 
				dummyC, dummyR,
				new Hud(new Image("res/graphics/danC/GUI.png"), 0, 540, dummyC, m.getPlayer(1))
				);
	
		
		//m.addUnit(data.Mgr.i().getUnit(1000, 160, 0, 5, 0, new behavior.Idle()));
	
		int playerNum = 0;
		
		for(int i = 0; i < t.getObjectCount(0); i++){
			//Preplace starting objects
			
			//Place the starting stuff at the start locations
			String type = t.getObjectType(0, i);
			if(type.equals("startloc")){
				playerNum ++;
				m.addUnit( data.Mgr.i().getUnit(
						t.getObjectX(0, i),
						t.getObjectY(0, i),
						0, 5, playerNum, 
						new behavior.Idle()));
			} else if(type.equals("wellhead")){
				int tx =(int) (t.getObjectX(0, i) / 40);
				int ty =(int) (t.getObjectY(0, i) / 40);
				pg.p[tx][ty] = false;
				pg.p[tx][ty+1] = false;
				pg.p[tx+1][ty] = false;
				pg.p[tx+1][ty+1] = false;
				
				m.addUnit( data.Mgr.i().getUnit(
						tx * 40,
						ty * 40,
						0, 6, 0, 
						new behavior.Extractor()));
			}
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
