package menus;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import data.Sprite;

public abstract class SpriteButton extends AbstractButton{

	Sprite sprite;
	
	public SpriteButton(int x, int y, Sprite sprite){
		super(new Rectangle(x, y, sprite.getWidth(), sprite.getHeight()));
		this.sprite = sprite;
	}

	@Override
	protected void renderNormal(Graphics g) {
		draw(0, g);
	}

	@Override
	protected void renderMouseOver(Graphics g) {
		draw(1, g);
	}
	
	@Override
	protected void renderMouseDown(Graphics g) {
		draw(2, g);
	}
	
	private void draw(int mode, Graphics g){
		sprite.draw(mode, (int)pos.getX(), (int)pos.getY());
		//g.draw(pos); //Debug
	}

}
