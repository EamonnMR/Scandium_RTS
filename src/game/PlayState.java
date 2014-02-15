package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

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
	private int screenX;
	private int screenY;
	private final double SCROLL_SPEED = 1;
	private PathGrid pg;
	
	
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
		drawMapMeta(g);
		g.draw(new Rectangle(Mouse.i().x, Mouse.i().y, 5,5));
	}

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
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 6;
	}

	public void sendInfo(Model m, TiledMap t, PathGrid pg){
		this.m = m;
		this.map = t;
		this.pg = pg;
	}
	public void setScreenSize(int x, int y){
		this.screenX = x;
		this.screenY = y;
	}
}
