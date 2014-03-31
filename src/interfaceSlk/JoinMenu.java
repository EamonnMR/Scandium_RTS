package interfaceSlk;

/* FIXME: This class (and menu opton) is obsolete.
 * 
 * I'm not going to maintain two versions of the same class at once,
 * so I'm going to get TestScreen working first, then once there's little
 * to no code in it, move that code over to here.
 * 
 * The network code is broken anyway.
 * 
 */
import game.PathGrid;
import game.Unit;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import net.ClientTransciever;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import data.Sprite;

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
					try {
						startClientCon(port, lhost);
					} catch (SlickException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					game.enterState(6);
				}
			};
		}
		
	}

	private void startClientCon(int port, String host) throws SlickException{
		//I'll go back and clean up the exceptions at a later date...
		//probably when I refactor the entire thing to use the dataman to load things.
		
		
		Socket sock = null;
		try {
			sock = new Socket(host, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		ClientTransciever ct = null;
		try {
			ct = new ClientTransciever(sock);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TiledMap t = null;
		try {
			t = new TiledMap("res/RageValley.tmx");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PathGrid pg = new PathGrid(t);
		game.Model m = new game.Model(t, pg, 2, 0);
		
		
		try {
			game.PlayState.i().sendInfo(m, t, pg, new Sprite(
					new Image("res/graphics/danC/mouse.png"),
					40, 40, 14, 1, 0, 0, 0), 
					ct, ct,
					null
					);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		m.addUnit(new Unit(
					new Sprite(
					new Image("res/graphics/danC/tank.png"),
					48, 56, 8, 1, -24, -27, 0)
					, 0, 1000, 160,
					new cards.CommandCard(){
						
					})
				);
		System.out.println("Map loaded successfully");
	}
		
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

}
