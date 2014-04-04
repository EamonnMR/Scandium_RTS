package gui;

import game.CmdSender;
import game.Model;
import game.PathGrid;
import game.PlayState;
import org.newdawn.slick.Graphics;
import data.Sprite;

public class PlayerMouse {
	int player;
	private Sprite mouseSpr;
	int mouseState;
	
	int screenX, screenY;

	private final double SCROLL_SPEED = 1;
	private final double SCROLL_SPEED_CORNER = Math.sqrt(2) * SCROLL_SPEED;

	int maxCamX, maxCamY;
	
	Hud hd;
	
	private Mode mode;
	
	
	public PlayerMouse(int player, int screenX, int screenY, Sprite mouseSpr, int maxCamX, int maxCamY, Hud hd) {
		this.player = player;
		this.screenX = screenX;
		this.screenY = screenY;
		this.maxCamX = maxCamX;
		this.maxCamY = maxCamY;
		this.hd = hd;
		this.mouseSpr = mouseSpr;
		
		defaultMode();
	}
	

	public void defaultMode(){
		this.mode = new DefaultMM(mouseSpr);
	}

	public void setMode(Mode mode){
		this.mode = mode;
	}

	/**
	 * This is the closest thing we have to a main method in this class.
	 */
	public void freeUpdate(int dt, int camX, int camY, Model m, PathGrid pg, CmdSender sndr, PlayState pls){

		//Handle mouse box dragging
		
		mode.update(dt, camX, camY, m, pg, sndr, hd);

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
		if(mode.unlocked()){
			
			if(l || r || t || b){
				mode.hide();
				if(l || r){
					camX += (int) (r ? -1 : 1) * Math.round(dt * ((t || b) ? SCROLL_SPEED_CORNER : SCROLL_SPEED));
				}
				if(t || b){
					camY += (int) (b ? -1 : 1) * Math.round(dt * ((l || r) ? SCROLL_SPEED_CORNER : SCROLL_SPEED));
				}
			} else {
				mode.show();
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


	public void drawGameMouse(Graphics g, int camX, int camY, Model m){
		mode.draw(g, camX, camY,m);
	}
	
	public void draw(Graphics g){
		drawMouse(g);
	}

	public static abstract class Mode{
		boolean hidden;
		public abstract void draw(Graphics g, int camX, int camY, Model m);
		public void show() {
			hidden = false;
		}
		public void hide() {
			hidden = true;
		}
		public abstract boolean unlocked();
		public abstract void update(int dt, int camX, int camY, Model m, PathGrid pg, CmdSender sndr, Hud hd);
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
}
