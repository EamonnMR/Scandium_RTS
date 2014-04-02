package game;

import interfaceSlk.AbstractButton;
import interfaceSlk.SpriteButton;

import java.util.Collection;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import data.Sprite;

public class Hud {
	
	private Image background;
	int xPos, yPos;
	AbstractButton[] buttons;
	Collection<Unit> selection;
	protected CmdSender sender;
	
	public Hud(Image background, int xPos, int yPos,CmdSender sender){
		selection = null;
		buttons = new AbstractButton[9];
		this.background = background;
		this.xPos = xPos;
		this.yPos = yPos;
		this.sender = sender;
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
	
	public void freeUpdate(Model m, int mouseX, int mouseY){
		for(AbstractButton b : buttons){
			if(b != null){
				b.update(new Point(Mouse.i().x,
						Mouse.i().y),
						Mouse.i().buttons[0]
						);
			}
		}
	}
	
	public void changeSelection(Collection<Unit> selection){
		this.selection = selection;
		//FIXME: This needs to be reworked until it actually works.
		if(selection != null && selection.size() > 0){
			boolean first = true;
			for(int i = 0; i < 9; i++){ //Only one button is implemented.
				Button currentButton = null;
				for(Unit u : selection){
					if(first){
						currentButton =  u.getButton(i);
						first = false;
					} else {
						if(currentButton != u.getButton(i)){
							currentButton = null;
							break;
						}
					}
				}
				buttons[i] =  makeHudButton(i, currentButton);
			}
		} else {
			for(int i = 0; i < 9; i++){
				buttons[i] = null;
			}
		}
	}
	
	public void render(GameContainer game, Graphics g){
		background.draw(xPos, yPos);
		for(AbstractButton button : buttons){
			if(button != null){
				button.render(game, g);
			}
		}
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
		public abstract void pressed(Hud h, CmdSender c);
		
		public Button(data.Sprite spr){
			
		}
		
		public void setSpr(Sprite spr){
			this.spr = spr;
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
			dat.pressed(Hud.this, sender);
		}
		
	}
	
	public RealizedButton makeHudButton(int index, Button btn){
		int x = 0, y = 0;
		if(btn == null){
			return null;
		} else {
			/* Decide where to put the button based on its index (this could be offloaded to JASON as 
			   different games will probably have totally different HUDs) */
			switch(index){
			case 0:{
				x = 658;
				y = 11;
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
