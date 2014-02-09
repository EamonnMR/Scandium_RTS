package interfaceSlk;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class JoinLobby extends MenuState {

	@Override
	public void init(GameContainer arg0, final StateBasedGame game)
			throws SlickException {
		interactives = new Interactive[] {
				new TextButton(new Rectangle(200, 200, 50, 20), "Cancel" ){
					protected void doAction() {
						game.enterState(0);
					}
				}
		};
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 4;
	}

}
