package behavior;

import game.Model;
import game.Unit;

import org.newdawn.slick.util.pathfinding.Path;

public class FollowPath extends UnitState{
	Path p;
	int speed;
	int currentMove;
	int progress;
	int curDist;
	
	private static final int TILE_DIST = 40;
	private static final int DIAGONAL_DIST = 57;
	
	
	public FollowPath(Path p, int Speed){
		this.p = p;
		this.speed = speed;
		currentMove = 0;
		progress = 0;
		curDist = getDist();
	}
	@Override
	public UnitState update(Model m, Unit u) {
		progress += speed;
		if(progress >= curDist){
			if(currentMove < p.getLength() - 2){
				currentMove ++;
				progress = 0;
			} else {
				return new Idle();
			}
		}
		u.setFacing(getFacing());
		return this;
	}
	@Override
	public void updateInterm(Model m, Unit u, int dt) {
		//Nothing here yet
	}

	private int getDist() {
		return ((p.getX(currentMove) != p.getX(currentMove + 1)) &&
				(p.getX(currentMove) != p.getY(currentMove + 1))) 
				? DIAGONAL_DIST : TILE_DIST;
	}
	
	private int getFacing(){
		//So many ways to decode this... I'm gonna try a totally different method...
		
		int x, y;
		x = (p.getX(currentMove) == p.getX(currentMove + 1)) ?  0 :
			(p.getX(currentMove)  > p.getX(currentMove + 1)) ? -1 :
			1;
		y = (p.getY(currentMove) == p.getY(currentMove + 1)) ?  0 :
			(p.getY(currentMove)  > p.getY(currentMove + 1)) ? -1 :
			1;
		
		if(x > 0){
			if(y > 0){
				return 1;
			} else if(y == 0){
				return 0;
			} else {
				return 7;
			}
		} else if(x == 0){
			if(y > 0){
				return 2;
			} else {
				return 6;
			}
		} else {
			if(y > 0){
				return 5;
			} else if(y == 0){
				return 4;
			} else {
				return 3;
			}
		}
		
	}
}
