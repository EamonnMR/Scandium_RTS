package menus;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * A button with text only graphics. Intended for use with anon classes
 * that override doAction to make the button do stuff.
 */

public abstract class TextButton extends AbstractButton{

	String label;
	
	public TextButton(Rectangle pos, String label) {
		//Not simply taking the position and determining the size
		//from the text is a hack.
		super(pos);
		this.label = label;
	}
	
	@Override
	protected void renderMouseDown(Graphics g) {
		g.drawString("#" + label + "#", pos.getX(), pos.getY());
	}

	@Override
	protected void renderMouseOver(Graphics g) {
		g.drawString("*" + label + "*", pos.getX(), pos.getY());
	}

	@Override
	protected void renderNormal(Graphics g) {
		g.drawString(" " + label + " ", pos.getX(), pos.getY());
	}
}
