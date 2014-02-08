package interfaceSlk;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
class Main extends StateBasedGame{

	public Main() {
		super("Scandium");
		// TODO Auto-generated constructor stub
	}
	
	
	public static void main(String args[]) throws FileNotFoundException, IOException, SlickException{
		AppGameContainer game = new AppGameContainer(new Main());
		
		//Apply prefs
		Properties cfg = loadProps();
		
	    game.setMouseGrabbed(truthiness((String) cfg.get("mouseGrabbed")));
        game.setDisplayMode(quantity((String) cfg.get(   "width")),
        					quantity((String) cfg.get(   "height")),
        					truthiness((String) cfg.get( "fullscreen")));
        game.setShowFPS(truthiness((String) cfg.get(     "showFPS")));
        
        //Start game
        game.start();
	}

	
	//FIXME: This should use sensable defaults, not throw...
	private static Properties loadProps() throws FileNotFoundException, IOException {
		Properties toSender = new Properties();
		toSender.load(new FileInputStream("prefs.ini"));
		return toSender;
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState( new MainMenu());
		addState( new HostMenu());
		addState( new JoinMenu());
	}
	//Defaults to false!
	private static boolean truthiness(String bool){
		return bool.equals("true")||bool.equals("True")||bool.equals("TRUE")||bool.equals("1");
	}
	
	private static int quantity(String number){
		return Integer.parseInt(number);
	}
	
}
