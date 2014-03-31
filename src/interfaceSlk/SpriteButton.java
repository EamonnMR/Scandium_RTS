package interfaceSlk;

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
		draw(0);
	}

	@Override
	protected void renderMouseOver(Graphics g) {
		draw(1);
	}
	
	@Override
	protected void renderMouseDown(Graphics g) {
		draw(2);
	}
	
	private void draw(int mode){
		sprite.draw((int)pos.getX(), (int)pos.getY(), mode);
	}

}
