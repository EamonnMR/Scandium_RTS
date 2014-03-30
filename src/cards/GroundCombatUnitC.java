package cards;

import org.newdawn.slick.util.pathfinding.Path;

import commands.Instruction;
import commands.Teleport;

import game.Model;
import game.Unit;

public class GroundCombatUnitC extends CommandCard{

	@Override
	public boolean movesByPath() {
		return true;
	}

	@Override
	public void actuate(Instruction cmd, Unit u, Model m) {
		// Wow: 
		//Letting the command card have total control over how instructions are
		//Executed is actually a really good idea...
		if(cmd.getClass() == Teleport.class){
			Teleport move = (Teleport) cmd;
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
