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


import java.util.List;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;








import net.ClientTransceiver;
import net.DataStreamTrnscv;
import net.MsgTrnscv;

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
						e.printStackTrace();
						System.out.println("Slick exception thrown");
						System.exit(1);
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("Fatal I/O error");
						System.exit(1);
					}
					game.enterState(6);
				}
			};
		}
	}

	private void startClientCon(int port) throws SlickException, IOException{
		MsgTrnscv mtr = new DataStreamTrnscv(new Socket(host, port));
		List<Integer> theInitialCommand = mtr.rcvMsg();
		int player = theInitialCommand.get(0);
		ClientTransceiver ctr = new ClientTransceiver(mtr);
		game.Setup.setup("res/RageValley.tmx",ctr, ctr, player);
	}
		
	@Override
	public int getID() {
		return 2;
	}

}
