package game;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.Path;

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
	private final double SCROLL_SPEED = 1;
	private final double SCROLL_SPEED_CORNER = Math.sqrt(2) * SCROLL_SPEED;
	private PathGrid pg;
	private Sprite mouseSpr;
	int mouseState;
	int selectionX, selectionY;
	boolean isDragging;
	Rectangle selectBox;
	private CmdSender sndr;
	private Reciever rcv;
	int timer;
	private Collection<Unit> selectedUnits;
	private boolean mouseRight;
	
	private Path path;
	
	public PlayState(){
		selectedUnits = new LinkedList<Unit>();
		path = null;
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
		drawHilights(g);
		m.draw(camX, camY);
		if(path != null){
			Devgog.drawPath(g, path, camX, camY);
		}
		drawSelectionBox(g);
		drawMouse(g);
		
	}

	private void drawHilights(Graphics g) {
		for(Unit i : selectedUnits){
			g.draw(new Circle(i.x + camX, i.y + camY, 20));
		}
	}

	private void drawSelectionBox(Graphics g) {
		if(isDragging && selectBox != null){
			g.draw(selectBox);
		}
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
		freeUpdate(dt);
	}
	
	private void freeUpdate(int dt){

		//Handle mouse box dragging
		if(isDragging){
			Line selln = new org.newdawn.slick.geom.Line(Mouse.i().x, Mouse.i().y, selectionX, selectionY);
			selectBox = new Rectangle(selln.getMinX(), selln.getMinY(), Math.abs(selln.getDX()), Math.abs(selln.getDY()));
			if(!Mouse.i().buttons[0]){
			//FIXME: Mouse released from selection: querey the model for units inside the selection box
				isDragging = false;
				
				
				selectedUnits = m.areaQuerey(new Rectangle(selectBox.getX() - camX, selectBox.getY() - camY, selectBox.getWidth(), selectBox.getHeight()));
				selectBox = null;
			}
		} else if(Mouse.i().buttons[0] && !isDragging){
			isDragging = true;
			selectionX = Mouse.i().x;
			selectionY = Mouse.i().y;
		}
		
		//Handle right click
		if(mouseRight){
		//FIXME: Is the right mouse button actually button 1?
			mouseRight = Mouse.i().buttons[1];
		} else if(Mouse.i().buttons[1]){
			mouseRight = true;
			if(!selectedUnits.isEmpty()){
				//FIXME: Hack (the for loop at least)
				
				List<Unit> directPathUnits = new LinkedList<Unit>();
				int destX = (Mouse.i().x - camX) / 40;
				int destY = (Mouse.i().y - camY) / 40;
				for(Unit i : selectedUnits){
					path = pg.getPath(i.x / 40, i.y / 40, 
							destX, 
							destY);
					if(path != null){
						directPathUnits.add(i);
					}
				}
				
				sndr.rcv(new commands.Command(unitListToUIDArray(directPathUnits),
						new commands.Teleport(Mouse.i().x - camX , Mouse.i().y - camY)
				));
			}
		}
		

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
		if(!isDragging){
			if(l || r){
				camX += (int) (r ? -1 : 1) * Math.round(dt * ((t || b) ? SCROLL_SPEED_CORNER : SCROLL_SPEED));
			}
			if(t || b){
				camY += (int) (b ? -1 : 1) * Math.round(dt * ((l || r) ? SCROLL_SPEED_CORNER : SCROLL_SPEED));
			}
		}
		
		/* Independant (similar) logic to determine what mouse sprite to use */
		mouseState = calcMouseState(l,r,t,b);
		
		//Limit the camera to the edges of the map
		
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
	
	private int[] unitListToUIDArray(Collection<Unit> selectedUnits) {
		int[] toSender = new int[selectedUnits.size()];
		int i = 0;
		for(Unit u : selectedUnits){
			toSender[i] = u.getUid();
			i++;
		}
		return toSender;
	}

	/**
	 * Comverts selectedUnits into an int[] of UIDs.
	 */
	private int[] getSelectedUnits() {
		return unitListToUIDArray(selectedUnits);
	}

	/**
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
	public void sendInfo(Model m, TiledMap t, PathGrid pg, Sprite mouseSpr, CmdSender sndr, Reciever rcv){
		this.m = m;
		this.map = t;
		this.pg = pg;
		this.sndr = sndr;
		this.rcv = rcv;
		
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

}
