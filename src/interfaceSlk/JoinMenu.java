package interfaceSlk;

import java.util.Properties;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class JoinMenu extends MenuState {

	@Override
	public void init(GameContainer arg0, final StateBasedGame game)
			throws SlickException {
		final String host = "localhost";
		Properties ports = data.Mgr.i().ports;
		interactives = new Interactive[ports.size() + 1];
		interactives[ports.size()] = new TextButton(new Rectangle(200, 100, 50, 20), "Cancel" ){
			protected void doAction() {
				game.enterState(0);
			}
		};
		
		//This giant blob is mostly getting around Java's shameful lack of closures...
		//Essentially I'm creating a button to join at each port.
		for(int i = 0; i < ports.size(); i++){
			String sPort = ports.getProperty(Integer.toString(i));
			final int iPort = Integer.parseInt(sPort);

			interactives[i] = new TextButton(new Rectangle(200, 100 * (i+2), 50, 20), sPort ){
				final int port = iPort;
				final String lhost = host;
				protected void doAction() {
					startClientCon(port, lhost);
				}
			};
		}
		
	}

	private void startClientCon(int port, String host){
		//lol this does not exist yet.
	}
		
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

}
