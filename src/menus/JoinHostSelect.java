package menus;

import java.util.Properties;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;


public class JoinHostSelect extends MenuState {

	@Override
	public void init(GameContainer arg0, final StateBasedGame game)
			throws SlickException {
		

		Properties hosts = data.Mgr.i().hosts;
		
		interactives = new Interactive[hosts.size() + 1];
		
		int i = 0;
		
		for(; i < hosts.size(); i++){
			final String iHost = hosts.getProperty(Integer.toString(i));

			interactives[i] = new TextButton(new Rectangle(200, 100 * (i+2), 50, 20), iHost ){
				protected void doAction() {
					JoinPortSelect.host = iHost;
					game.enterState(2);
				}
			};
		}
		
		interactives[hosts.size()] = new TextButton(new Rectangle(200, 100 * (i+3), 50, 20), "Cancel" ){
			protected void doAction() {
				game.enterState(0);
			}
		};
		
	}

	@Override
	public int getID() {
		return 4;
	}

}
