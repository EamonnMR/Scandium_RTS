package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import data.Sprite;

/**
 * This mainly deals with the human interface to the game-viewing the map, etc.
 * @author Eamonn
 *
 */
public class PlayState extends BasicGameState{
	
	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		Mouse.i().updatePos(newx, newy);
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
		Mouse.i().updatePos(newx, newy);
	}

	Model m;
	static PlayState instance;
	TiledMap t;
	private TiledMap map;
	int camX, camY;
	int maxCamX;
	int maxCamY;
	private int screenX;
	private int screenY;
	private final double SCROLL_SPEED = 1;
	private final double SCROLL_SPEED_CORNER = Math.sqrt(2) * SCROLL_SPEED;
	private PathGrid pg;
	private Sprite mouseSpr;
	
	public static PlayState i(){
		if(instance == null){
			instance = new PlayState();
		}
		return instance;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		map.render(camX, camY);
		g.draw(new Rectangle(Mouse.i().x, Mouse.i().y, 5,5));
	}

	/**  Show the passability grid
	 */
	@SuppressWarnings("unused")
	private void drawMapMeta(Graphics g) {
		for(int i = 0; i < pg.p.length; i ++){
			for(int j = 0; j < pg.p[i].length; j++){
				if(pg.p[i][j]){
					g.draw(new Rectangle(camX + (40 * i) + 10,
										camY + (40 * j) + 10,
										20, 20));
				}
			}
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int dt)
			throws SlickException {
		//The following implements mouse scrolling
		if(Mouse.i().x <= 0){
			camX += (int) Math.round(dt * SCROLL_SPEED);
		} else if(Mouse.i().x >= screenX){
			camX -= (int) Math.round(dt * SCROLL_SPEED);
		}
		
		if(Mouse.i().y <= 0){
			camY += (int) Math.round(dt * SCROLL_SPEED);
		} else if(Mouse.i().y >= screenY){
			camY -= (int) Math.round(dt * SCROLL_SPEED);
		}
		//FIXME: Add corner scrolling - currently it scrolls twice as
		//fast as side scrolling
		
		if(camX < maxCamX){
			camX = maxCamX;
		} else if(camX > 0){
			camX = 0;
		}
		if(camY < maxCamY){
			camY = maxCamY;
		} else if(camY > 0){
			camY = 0;
		}
	}
	public void sendInfo(Model m, TiledMap t, PathGrid pg, Sprite mouseSpr){
		this.m = m;
		this.map = t;
		this.pg = pg;
		
		maxCamX = (t.getWidth() * -t.getTileWidth()) + screenX;
		maxCamY = (t.getHeight() * -t.getTileHeight()) + screenY;
		
		this.mouseSpr = mouseSpr;
	}
	
	public void setScreenSize(int x, int y){
		this.screenX = x;
		this.screenY = y;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 6;
	}

}
