package interfaceSlk;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.gui.TextField;

public class TextBox extends Interactive {

	public TextField t;
	
	TextBox(int x, int y){
		t = new TextField(null, null, y, y, y, y);
	}
	
	@Override
	public void update(Point mousePos, boolean mouseState) {
		//No op
	}

	@Override
	public void render(GameContainer game, Graphics g) {
		t.render(game, g);
	}

	public String getText(){
		return t.getText();
	}
}
