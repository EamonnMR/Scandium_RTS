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
	int mouseState;
	
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
		m.draw(camX, camY);
		//drawMouse(g);
	}

	private void drawMouse(Graphics g) {
		int x = Mouse.i().x;
		int y = Mouse.i().y;
		
		switch(mouseState){
		 case(0):{
			x = screenX - 40;
			break;
		}case(1):{
			x = screenX - 40;
			y = screenY - 40;
			break;
		}case(2):{
			y = screenY - 40;
			break;
		}case(3):{
			x = 0;
			y = screenY - 40;
			break;
		}case(4):{
			x = 0;
			break;
		}case(5):{
			x = 0;
			y = 0;
			break;
		}case(6):{
			y = 0;
			break;
		}case(7):{
			x = screenX - 40;
			y = 0;
			break;
		}
		}
		mouseSpr.draw(mouseState, x, y);
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
		//What edges the mouse is (or is not) touching.
		boolean l, r, t, b;
		l = r = t = b = false;
		
		if(Mouse.i().x <= 0){
			l = true;
		} else if(Mouse.i().x >= screenX){
			r = true;
		}
		
		if(Mouse.i().y <= 0){
			t = true;
		} else if(Mouse.i().y >= screenY){
			b = true;
		}
		
		//Logic to implement the scrolling.
		if(l || r){
			camX += (int) (r ? -1 : 1) * Math.round(dt * ((t || b) ? SCROLL_SPEED_CORNER : SCROLL_SPEED));
		}
		if(t || b){
			camY += (int) (b ? -1 : 1) * Math.round(dt * ((l || r) ? SCROLL_SPEED_CORNER : SCROLL_SPEED));
		}
		
		/* Independant (similar) logic to determine what mouse sprite to use */
		
		mouseState = calcMouseState(l,r,t,b);
		
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
	
	/*
	 * Based on left/right/top/bottom, figure out what mouse frame to use.
	 */
	private int calcMouseState(boolean l, boolean r, boolean t, boolean b) {
		if(l){
			if(b){
				return 3;
			} else if(t){
				return 5;
			} else {
				return 4;
			}
		} else if(r) {
			if(b){
				return 1;
			} else if(t){
				return 7;
			} else {
				return 0;
			}
		} else if(b){
			return 2;
		} else if(t){
			return 6;
		} else {
			return 12;
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
