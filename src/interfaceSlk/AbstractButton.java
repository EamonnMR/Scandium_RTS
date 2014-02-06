package interfaceSlk;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

public abstract class AbstractButton extends Interactive{

	Rectangle pos;
	/*
	 * state:
	 * 0: No hover
	 * 1: Held into
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
			}
			case 1:{
				if(pos.contains(mousePos)){
					if(!mouseState){
						state = 2;
					}
				} else {
					state = 0;
				}
			}
			case 2:{
				if(pos.contains(mousePos)){
					if(mouseState){
						state = 3;
					}
				} else {
					state = 0;
				}
			}
			case 3:{	
				if(pos.contains(mousePos)){
					if(!mouseState){
						state = 0;
					}
				} else {
					state = 0;
				}
			}
		}
	}

	@Override
	public void render() {
		switch(state){
		case 0: renderNormal();
		case 2: renderNormal();
		case 3: renderMouseOver();
		case 4: renderMouseDown();
		}
	}

	protected abstract void renderMouseDown();
	protected abstract void renderMouseOver();
	protected abstract void renderNormal();
}
