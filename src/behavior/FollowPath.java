package behavior;

import game.Model;
import game.Unit;

import org.newdawn.slick.util.pathfinding.Path;

public class FollowPath extends UnitState{
	Path p;
	int speed;
	int currentMove;
	public FollowPath(Path p, int Speed){
		this.p = p;
		this.speed = speed;
		currentMove = 0;
	}
	@Override
	public void update(Model m, Unit u) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateInterm(Model m, Unit u, int dt) {
		//Nothing here yet
	}
	
}
