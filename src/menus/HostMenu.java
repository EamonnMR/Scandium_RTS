package menus;

import net.Server;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class HostMenu extends MenuState {

	@Override
	public void init(GameContainer arg0, final StateBasedGame game)
			throws SlickException {
		final int port = Integer.parseInt(data.Mgr.i().ports.getProperty("0"));
		interactives = new Interactive[] {
			new TextButton(new Rectangle(200, 200, 50, 20), "Host 1 player" ){
				protected void doAction() {
					new Thread(new Server(1, port)).start();
					game.enterState(0);
				}
			},
			new TextButton(new Rectangle(200, 300, 50, 20), "Host 2 player" ){
				protected void doAction() {
					new Thread(new Server(2, port)).start();
					game.enterState(0);
				}
			},
			new TextButton(new Rectangle(200, 400, 50, 20), "Cancel" ){
				protected void doAction() {
					game.enterState(0);
				}
			},
		};
	}


	@Override
	public int getID() {
		return 1;
	}

}
