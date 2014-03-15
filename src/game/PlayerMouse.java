package game;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.pathfinding.Path;

import data.Sprite;

public class PlayerMouse {
	int player;
	private Sprite mouseSpr;
	int mouseState;
	int selectionX, selectionY;
	boolean isDragging;
	Rectangle selectBox;

	private Collection<Unit> selectedUnits;
	int screenX, screenY;
	
	boolean mouseRight;

	private final double SCROLL_SPEED = 1;
	private final double SCROLL_SPEED_CORNER = Math.sqrt(2) * SCROLL_SPEED;

	int maxCamX, maxCamY;
	
	public PlayerMouse(int player, int screenX, int screenY, Sprite mouseSpr, int maxCamX, int maxCamY) {
		this.player = player;
		this.screenX = screenX;
		this.screenY = screenY;
		this.maxCamX = maxCamX;
		this.maxCamY = maxCamY;

		selectedUnits = new LinkedList<Unit>();

		this.mouseSpr = mouseSpr;
	}
	

	/**
	 * This is the closest thing we have to a main method in this class.
	 */
	public void freeUpdate(int dt, int camX, int camY, Model m, PathGrid pg, CmdSender sndr, PlayState pls){

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
			mouseRight = Mouse.i().buttons[1];
		} else if(Mouse.i().buttons[1]){
			mouseRight = true;
			if(!selectedUnits.isEmpty()){
				//FIXME: Hack (the for loop at least)
				
				List<Unit> directPathUnits = new LinkedList<Unit>();
				int destX = (Mouse.i().x - camX) / 40;
				int destY = (Mouse.i().y - camY) / 40;
				Path path;
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
		
		pls.setCam(camX, camY);
	}

	
	public void draw(Graphics g){
		if(isDragging && selectBox != null){
			g.draw(selectBox);
		}
		drawMouse(g);
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

	/**
	 * Draw the hilights around selected things...
	 * @param g
	 * @param camX
	 * @param camY
	 */
	
	public void drawHilights(Graphics g, int camX, int camY) {
		for(Unit i : selectedUnits){
			g.draw(new Circle(i.x + camX, i.y + camY, 20));
		}
	}

	/**
	 * Determine 
	 * @param l Is it on the left edge?
	 * @param r Is it on the right edge
	 * @param t Is it on the top edge?
	 * @param b Is it on the bottom edge?
	 * @return The sprite index of the state (which also determines where it'll be drawn)
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
	 * Turns a collection of Units into an array of those unit's UIDs.
	 */
	private int[] unitListToUIDArray(Collection<Unit> selectedUnits) {
		int[] toSender = new int[selectedUnits.size()];
		int i = 0;
		for(Unit u : selectedUnits){
			toSender[i] = u.getUid();
			i++;
		}
		return toSender;
	}
}
