package game;

import gui.Hud;
import net.CmdSender;
import net.Reciever;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import data.Sprite;

/**
 * This controls the setup logic-how it reads a map and sets up the players and such.
 * 
 * @author Eamonn
 *
 */

public class Setup {
	public static void setup(String map, Reciever rcv, CmdSender cmdsnd, int player) throws SlickException{
		TiledMap t = new TiledMap(map);
		PathGrid pg = new PathGrid(t);
		game.Model m = new game.Model(pg, 3);
		
		data.Mgr.i().faux_load_data();
		
		game.PlayState.i().sendInfo(m, t, pg, new Sprite(
				new Image("res/graphics/danC/mouse.png"),
				40, 40, 14, 1, 0, 0, 0), 
				cmdsnd, rcv,
				new Hud(new Image("res/graphics/danC/GUI.png"), 0, 540, cmdsnd, m.getPlayer(player))
				);

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

	}
}
