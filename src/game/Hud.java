package game;

import interfaceSlk.AbstractButton;

import java.util.Collection;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

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
			button.render(game, g);
		}
		//FIXME: Somehow represent the set of selected units, draw a portrait, draw a minimap
	}
}
