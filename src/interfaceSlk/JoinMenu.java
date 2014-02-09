package interfaceSlk;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class JoinMenu extends MenuState {

	@Override
	public void init(GameContainer arg0, final StateBasedGame game)
			throws SlickException {
		interactives = new Interactive[] {
			new TextButton(new Rectangle(200, 200, 50, 20), "Cancel" ){
				protected void doAction() {
					game.enterState(0);
				}
			},
			new TextButton(new Rectangle(200, 300, 100, 20), "Port " + data.Mgr.i().ports.get("1") ){
				protected void doAction() {
					startClientCon(Integer.parseInt((String) data.Mgr.i().ports.get("1")), "localhost");
					game.enterState(4);
				}
			},
			new TextButton(new Rectangle(200, 400, 100, 20), "Port " + data.Mgr.i().ports.get("2") ){
				protected void doAction() {
					startClientCon(Integer.parseInt((String) data.Mgr.i().ports.get("2")), "localhost");
					game.enterState(4);
				}
			},
			new TextButton(new Rectangle(200, 500, 100, 20), "Port " + data.Mgr.i().ports.get("3") ){
				protected void doAction() {
					startClientCon(Integer.parseInt((String) data.Mgr.i().ports.get("3")), "localhost");
					game.enterState(4);
				}
			},
		};
	}

	private void startClientCon(int port, String host){
		
	}
		
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

}
