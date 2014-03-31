package game;

import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
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
	private static final int UPDATE_TIME = 100;
	Model m;
	static PlayState instance;
	private TiledMap map;
	int camX, camY;
	int maxCamX;
	int maxCamY;
	private int screenX;
	private int screenY;
	private PathGrid pg;
	private CmdSender sndr;
	private Reciever rcv;
	int timer;
	private Hud h;
	
	protected PlayerMouse playerAgent;
	
	
	public PlayState(){
		//FIXME: How does playState know what player it is?
		//Perhaps the server should send message telling it which one it is...
	}
	
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
		g.setLineWidth(2);
		map.render(camX, camY);
		playerAgent.drawHilights(g, camX, camY);
		m.draw(camX, camY);
		//Ok, so the button needs to *not* depend on the game I guess...
		h.render(null, g);
		playerAgent.draw(g);
		
	}


	private void fixedUpdate() throws IOException{
		//Recieve and interpret incoming commands
		//this is where communication will block if it blocks at all.
		m.tickUpdate(UPDATE_TIME, rcv.getLatestCommands());
		sndr.updateTick();
	}
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int dt)
			throws SlickException {
		timer += dt;
		if(timer > UPDATE_TIME){
			try {
				fixedUpdate();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			timer = 0;
		}
		playerAgent.freeUpdate(dt, camX, camY, m, pg, sndr, this);
	}
	

	/**
	 * Files being loaded before the game starts should be sent to PlayState through this function.
	 * 
	 * @param m Pre-setup game model
	 * @param t Current tile map
	 * @param pg Pre-setup path grid
	 * @param mouseSpr Sprite to use for the mouse
	 * @param sndr Command sender with open connection
	 * @param rcv  Command Reciever with open connection
	 */
	public void sendInfo(Model m, TiledMap t, PathGrid pg, Sprite mouseSpr, CmdSender sndr, Reciever rcv, Hud h){
		this.m = m;
		this.map = t;
		this.pg = pg;
		this.sndr = sndr;
		this.rcv = rcv;
		this.h = h;
		
		maxCamX = (t.getWidth() * -t.getTileWidth()) + screenX;
		maxCamY = (t.getHeight() * -t.getTileHeight()) + screenY;
		
		playerAgent = new PlayerMouse(0, //FIXME: During "send info" the player should be sent
				screenX, screenY, mouseSpr,
				(t.getWidth()  * -t.getTileWidth())  + screenX,
				(t.getHeight() * -t.getTileHeight()) + screenY);
	}
	
	public void setScreenSize(int x, int y){
		this.screenX = x;
		this.screenY = y;
	}

	@Override
	public int getID() {
		return 6;
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		Mouse.i().updatePos(newx, newy);
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		Mouse.i().updatePos(newx, newy);
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		Mouse.i().mouseDown(button);
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		Mouse.i().mouseUp(button);
	}

	public void setCam(int camX, int camY) {
		this.camX = camX;
		this.camY = camY;
	}

}
