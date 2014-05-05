package menus;

/* FIXME: This class (and menu opton) is obsolete.
 * 
 * I'm not going to maintain two versions of the same class at once,
 * so I'm going to get TestScreen working first, then once there's little
 * to no code in it, move that code over to here.
 * 
 * The network code is broken anyway.
 * 
 */


import java.util.Properties;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
public class JoinPortSelect extends MenuState {
	
	public static String host; //Yes, this isn't the best way to pass a var, but I'm a bit pressed for time.
	

	@Override
	public void init(GameContainer arg0, final StateBasedGame game)
			throws SlickException {
		Properties ports = data.Mgr.i().ports;
		interactives = new Interactive[ports.size() + 1];
		interactives[ports.size()] = new TextButton(new Rectangle(200, 100, 50, 20), "Cancel" ){
			protected void doAction() {
				game.enterState(0);
			}
		};
		
		//Essentially I'm creating a button to join at each port.
		for(int i = 0; i < ports.size(); i++){
			String sPort = ports.getProperty(Integer.toString(i));
			final int iPort = Integer.parseInt(sPort);

			interactives[i] = new TextButton(new Rectangle(200, 100 * (i+2), 50, 20), sPort ){
				final int port = iPort;
				protected void doAction() {
					try {
						startClientCon(port);
					} catch (SlickException e) {
						System.out.println("Can't establish connection");
						System.exit(1);
						e.printStackTrace();
					}
					game.enterState(6);
				}
			};
		}
	}

	private void startClientCon(int port) throws SlickException{
		
	}
		
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

}
