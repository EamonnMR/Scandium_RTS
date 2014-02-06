package interfaceSlk;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu extends MenuState{

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		interactives = new Interactive[] {
			new TextButton(new Rectangle(100, 100, 50, 20), "Quit" ){
				protected void doAction() {
					System.exit(0);
				}
			}
		};
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
