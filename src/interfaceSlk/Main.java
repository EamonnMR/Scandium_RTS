package interfaceSlk;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import game.Mouse;
import game.PlayState;
class Main extends StateBasedGame{

	public Main() {
		super("Scandium");
		// TODO Auto-generated constructor stub
	}
	
	
	public static void main(String args[]) throws FileNotFoundException, IOException, SlickException{

		AppGameContainer game = new AppGameContainer(new Main());
		
		//Apply prefs
		data.Mgr.i().loadInis();
		Properties cfg = data.Mgr.i().cfg;//loadProps();
		
        int screenX, screenY;
        screenX = quantity((String) cfg.get("width"));
        screenY = quantity((String) cfg.get("height"));
        PlayState.i().setScreenSize(screenX, screenY);
        Mouse.i().setScreenSize(screenX, screenY);
		
	    game.setMouseGrabbed(truthiness((String) cfg.get("mouseGrabbed")));
        game.setDisplayMode(screenX,
        					screenY,
        					truthiness((String) cfg.get( "fullscreen")));
        game.setShowFPS(truthiness((String) cfg.get(     "showFPS")));
        
        
        //Start game
        game.start();
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		for(BasicGameState state : new BasicGameState[]{
				new MainMenu(),
				new HostMenu(),
				new JoinMenu(),
				new HostLobby(),
				new JoinLobby(),
				new TestScreen(),
				game.PlayState.i()
		}){
			addState(state);
		}
		
	}
	
	//Defaults to false!
	private static boolean truthiness(String bool){
		return bool.equals("true")||bool.equals("True")||bool.equals("TRUE")||bool.equals("1");
	}
	
	private static int quantity(String number){
		return Integer.parseInt(number);
	}
	
}
