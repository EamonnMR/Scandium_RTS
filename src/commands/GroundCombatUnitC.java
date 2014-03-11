package commands;

import org.newdawn.slick.util.pathfinding.Path;

import game.Model;
import game.Unit;

public class GroundCombatUnitC extends CommandCard{

	@Override
	public boolean movesByPath() {
		return true;
	}

	@Override
	public void actuate(Instruction cmd, Unit u, Model m) {
		// TODO Auto-generated method stub
		if(cmd.getClass() == Teleport.class){
			Teleport move = (Teleport) cmd;
			System.out.println((int)(u.x / 40));
			System.out.println((int)(u.y / 40));
			System.out.println(move.getX());
			System.out.println(move.getY());
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
