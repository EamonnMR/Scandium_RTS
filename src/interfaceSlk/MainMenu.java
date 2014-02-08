package interfaceSlk;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu extends MenuState{

	@Override
	public void init(GameContainer arg0, final StateBasedGame game)
			throws SlickException {
		interactives = new Interactive[] {
			new TextButton(new Rectangle(200, 200, 50, 20), "Host" ){
				protected void doAction() {
					game.enterState(1);
				}
			},
			new TextButton(new Rectangle(200, 300, 50, 20), "Join" ){
				protected void doAction() {
					game.enterState(2);
				}
			},
			new TextButton(new Rectangle(200, 400, 50, 20), "Quit" ){
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
