package cards;

import instructions.Instruction;
import instructions.Relocate;

import org.newdawn.slick.util.pathfinding.Path;

import game.Model;
import game.Unit;
import gui.Hud.Button;

public class GroundCombatUnit extends CommandCard{

	public GroundCombatUnit(Button[] earlyAccessToButtons) {
		super(earlyAccessToButtons);
		btns[0] = earlyAccessToButtons[4];
		btns[1] = earlyAccessToButtons[3];
	}

	@Override
	public boolean movesByPath() {
		return true;
	}

	@Override
	public void actuate(Instruction cmd, Unit u, Model m) {
		// Wow: 
		//Letting the command card have total control over how instructions are
		//Executed is actually a really good idea...
		if(cmd.getClass() == Relocate.class){
			Relocate move = (Relocate) cmd;
			Path path = m.p.getPath(Math.round((u.x) / 40), Math.round((u.y)/40), Math.round(move.getX() / 40), Math.round(move.getY()/40));
			//Path path = m.p.getPath((int)(u.x / 40), (int)(u.y /40), (int)(move.getX() / 40), (int)(move.getY()/40));
			if( path == null ){
				System.out.println("path was null");
			} else {
				u.enterState(new behavior.FollowPath(path, 2f, 0f));
			}
		}
	}
}
