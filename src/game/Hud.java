package game;

import interfaceSlk.AbstractButton;

import java.util.Collection;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import data.Sprite;

public class Hud {
	
	Image background;
	int xPos, yPos;
	AbstractButton[] buttons;
	Collection<Unit> selection;
	
	
	public Hud(Image background, int xPos, int yPos){
		selection = null;
		buttons = new AbstractButton[9];
		this.background = background;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void freeUpdate(Model m, int mouseX, int mouseY){
		for(AbstractButton b : buttons){
			b.update(new Point(Mouse.i().y,
					Mouse.i().x),
					Mouse.i().buttons[0]
					);
		}
	}
	
	public void changeSelection(Collection<Unit> selection){
		this.selection = selection;
		if(selection.size() > 0){
			boolean first = true; //Jumping through hoops to use Collection;
			//FIXME: This is kinda dumb
			for(int i = 0; i < 9; i++){
				for(Unit u : selection){
					if(first){
						buttons[i] = u.getButton(i);
						first = false;
					} else {
						if(buttons[i] != u.getButton(i)){
							buttons[i] = null;
							break;
						}
					}
				}
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
	
	public abstract static class Button{
		public Sprite spr; //The three-frame sprite to use for the button
		
		/* Philosophy of what's exposed to the button class:
		 * Pressing a button alone is NOT allowed to affect the game model. It can only
		 * change the GUI, change the mouse, or send a command.
		 */
		public abstract void pressed(Hud h, PlayerMouse p, CmdSender c);
		
		public Button(Sprite spr){
			this.spr = spr;
		}
	}
}
