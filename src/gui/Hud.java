package gui;

import game.Model;
import game.Player;
import game.Unit;
import game.Util;

import java.util.Collection;
import java.util.LinkedList;

import menus.AbstractButton;
import menus.SpriteButton;
import net.CmdSender;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Point;

import data.Sprite;

public class Hud {
	
	private Image background;
	int xPos, yPos;
	AbstractButton[] buttons;
	Collection<Unit> selection;
	protected CmdSender sender;
	private PlayerMouse ms;
	public Player plr;
	private boolean ownership;
	
	public Hud(Image background, int xPos, int yPos, CmdSender sender, Player plr){
		selection = new LinkedList<Unit>();
		buttons = new AbstractButton[9];
		this.background = background;
		this.xPos = xPos;
		this.yPos = yPos;
		this.sender = sender;
		this.plr = plr;
		ownership = false;
	}
	
	public void setMouse(PlayerMouse ms){
		this.ms = ms;
	}
	
	/**
	 * Where's the bottom of the HUD?
	 */
	public int getMaxY(){
		return yPos;
	}
	
	public int getHeight(){
		return background.getHeight();
	}
	
	public void freeUpdate(Model m){
		for(AbstractButton b : buttons){
			if(b != null){
				b.update(new Point(Mouse.i().x,
						Mouse.i().y),
						Mouse.i().buttons[0]
						);
			}
		}
	}
	
	public void changeSelection(Collection<Unit> trySelection){
		
		if(trySelection.size() == 1){ //Player selected exactly one unit
			for(Unit i : trySelection){
				this.selection = trySelection;
				if(i.owner == plr.getIndex()){
					setupButtons();
					ownership = true;
				} else {
					clearButtons();
					ownership = false;
				}
			}
		} else { //Player has selected multiple units; ignore enemey units in selection.
			this.selection = new LinkedList<Unit>();
			for(Unit i : trySelection){
				if(i.owner == plr.getIndex()){
					this.selection.add(i);
				}
			}
			if(selection.size() > 0){
				setupButtons();
				ownership = true;
			} else {
				clearButtons();
				ownership = false;
			}
		}
	}
	
	public void setupButtons(){
		for(int i = 0; i < 9; i++){ 
			Button currentButton = null;
			boolean first = true;
			for(Unit u : selection){
				if(first){
					currentButton =  u.getButton(i);
					first = false;
				} else {
					if(currentButton != u.getButton(i)){
						currentButton = null;
					}
				}
			}
			buttons[i] =  makeHudButton(i, currentButton);
		}
	}
	
	public void clearButtons(){
		for(int i = 0; i < 9; i++){
			buttons[i] = null;
		}
	}
	
	public void render(GameContainer game, Graphics g, int camX, int camY){
		if(selection != null){
			for(Unit i : selection){
				g.draw(new Circle(i.x + camX, i.y + camY, i.getRadius()));
				//Combat isn't implemented yet
				//g.drawString(Integer.toString(i.hitPoints), i.x + camX, i.y + camY + i.getRadius());
			}
		}
		background.draw(xPos, yPos);
		for(AbstractButton button : buttons){
			if(button != null){
				button.render(game, g);
			}
		}
		g.drawString("Oil : " + Integer.toString(plr.oil), xPos + 2, yPos + 2);
		//FIXME: Somehow represent the set of selected units, draw a portrait, draw a minimap
	}
	
	/**
	 * This is the class that lets Command Cards indicate what each of the nine
	 * potential command buttons should do and look like-the very reason that it's
	 * called a command "card." I generally don't import the name down to just Button;
	 * it's less ambiguous to call it a Hud.Button.
	 * @author Eamonn
	 *
	 */
	public abstract static class Button{
		public Sprite spr; //The three-frame sprite to use for the button
		
		/* Philosophy of what's exposed to the button class:
		 * Pressing a button alone is NOT allowed to affect the game model. It can only
		 * change the GUI, change the mouse, or send a command.
		 */
		public abstract void pressed(Hud h, CmdSender c, PlayerMouse m);
		
		public Button(data.Sprite spr){
			
		}
		
		/*
		 * This is a hack to avoid passing parameters to the constructor of an anon class.
		 * In my defense, a similar technique is used in the C++ FAQ for chaining several
		 * method calls together.
		 */
		public Button setSpr(Sprite spr){
			this.spr = spr;
			return this;
		}
		
		public Button(){
			//I'd rather put the sprite in through a constructor, but that might not be possible.
		}
	}
	
	private class RealizedButton extends SpriteButton{
		public Button dat;
		RealizedButton(int x, int y, Button dat){
			super(x, y, dat.spr);
			this.dat = dat;
		}
		@Override
		protected void doAction() {
			dat.pressed(Hud.this, sender, ms);
		}
		
	}

	public boolean isSelectionOwned(){
		return ownership;
	}
	
	public RealizedButton makeHudButton(int index, Button btn){
		int x = 0, y = 0;
		if(btn == null){
			return null;
		} else {
			/* Decide where to put the button based on its index (this could be offloaded to JASON as 
			   different games will probably have totally different HUDs) */
			//Battleship rows and columns
			final int ColA = 658;
			final int ColB = 704;
			final int ColC = 750;
			
			final int Row1 = 10;
			final int Row2 = 56;
			final int Row3 = 102;
			//Yet another switch table!
			switch(index){
			case 0:{
				x = ColA;
				y = Row1;
				break;
			}
			case 1:{
				x = ColB;
				y = Row1;
				break;
			}
			case 2:{
				x = ColC;
				y = Row1;
				break;
			}
			case 3:{
				x = ColA;
				y = Row2;
				break;
			}
			case 4:{
				x = ColB;
				y = Row2;
				break;
			}
			case 5:{
				x = ColC;
				y = Row2;
				break;
			}
			case 6:{
				x = ColA;
				y = Row3;
				break;
			}
			case 7:{
				x = ColB;
				y = Row3;
				break;
			}
			case 8:{
				x = ColC;
				y = Row3;
				break;
			}
			}
			return new RealizedButton(x + xPos, y + yPos, btn);
		}
	}
	
	public int[] getSelectedUnits() {
		return Util.unitListToUIDArray(selection);
	}
}
