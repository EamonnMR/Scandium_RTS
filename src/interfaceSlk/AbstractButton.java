package interfaceSlk;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
/**
 * A state-based button. Extend it to add graphics and such.
 * 
 * @author Eamonn
 *
 */
public abstract class AbstractButton extends Interactive{

	Rectangle pos;
	/*
	 * state:
	 * 0: No hover
	 * 1: Held-into (holding mouse down before moving into button)
	 * 2: 
	 */
	private int state;
	
	public AbstractButton(Rectangle pos){
		this.pos = pos;
		state = 0;
	}

	/*
	 * This implements basic GUI button logic as seen in most mouse driven systems.
	 */
	public void update(Point mousePos, boolean mouseState) {
		switch(state){
			case 0:{
				if(pos.contains(mousePos)){
					/*
					 * If the mouse is already pressed when it enters the button, 
					 * don't click, go to the special state that prevents that
					 * situation. Otherwise, go to the normal hover state.
					 */
					state = mouseState ? 1 : 2;
				}
				break;
			}
			case 1:{
				if(pos.contains(mousePos)){
					if(!mouseState){
						state = 2;
					}
				} else {
					state = 0;
				}
				break;
			}
			case 2:{
				if(pos.contains(mousePos)){
					if(mouseState){ //If the mouse is pressed
						state = 3;
					}
				} else {
					state = 0;
				}
				break;
			}
			case 3:{	
				if(pos.contains(mousePos)){
					if(!mouseState){ //ie If the mouse is released
						state = 0;
						doAction();
					}
				} else {
					state = 0;
				}
				break;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		switch(state){
			case 0:{ renderNormal(g); break;}
			case 1:{ renderMouseOver(g); break;}
			case 2:{ renderMouseOver(g); break;}
			case 3:{ renderMouseDown(g); break;}
		}
	}

	protected abstract void doAction();
	protected abstract void renderMouseDown(Graphics g);
	protected abstract void renderMouseOver(Graphics g);
	protected abstract void renderNormal(Graphics g);
}