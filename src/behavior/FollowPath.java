package behavior;

import game.Model;
import game.Unit;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.pathfinding.Path;

public class FollowPath extends UnitState{
	Path p;
	float speed;
	int currentMove;
	float progress;
	float curDist;
	
	private static final float TILE_DIST = 40;
	private static final float DIAGONAL_DIST = 56.5685f;
	
	/*
	 * Vectors that point in different directions.
	 * They're multiplied by progress to find the correct offset
	 * at a given time (see getDirVec)
	 * 
	 * They're stored as constants so that they don't need to be
	 * initialized separately at every call, and perhaps to make
	 * the code a bit more readable.
	 * 
	 */

	private static final float DIAG = .7071068f; //Sin( pi / 4 )
	private static final Vector2f RIGHT = new Vector2f(1, 0);
	private static final Vector2f DOWN_RIGHT = new Vector2f(DIAG, DIAG);
	private static final Vector2f DOWN = new Vector2f(0, 1);
	private static final Vector2f DOWN_LEFT = new Vector2f(-DIAG, DIAG);
	private static final Vector2f LEFT = new Vector2f(-1, 0);
	private static final Vector2f UP_LEFT = new Vector2f(-DIAG, -DIAG);
	private static final Vector2f UP = new Vector2f(0,-1);
	private static final Vector2f UP_RIGHT = new Vector2f(DIAG,-DIAG);
	
	public FollowPath(Path p, float speed, float progress){
		this.p = p;
		this.speed = speed;
		currentMove = 0;
		//Implicit: 
		//Progress should never exceed curDist. Ensure that you never pass
		//a number that high; or if you do, account for it somehow.
		this.progress = progress;
		curDist = getDist();
	}
	@Override
	public void update(Model m, Unit u) {
		if(curDist == 0){
			curDist = getDist();
		}
		progress += speed;
		if(progress >= curDist){
			if(currentMove < p.getLength() - 2){
				progress -= curDist;
				currentMove ++;
				curDist = getDist();
			} else {
				u.nextState();
			}
		}
		int facing = getFacing();
		u.setFacing(facing);
		
		Vector2f dir = getDirVec(facing);
		
		u.setPos((40 * p.getX(currentMove)) + 20 + (int)(dir.x * progress),
				( 40 * p.getY(currentMove)) + 20 + (int)(dir.y * progress)
				);
	}
	private Vector2f getDirVec(int facing) {
		switch(facing){
		case 0: return RIGHT;
		case 1: return DOWN_RIGHT;
		case 2: return DOWN;
		case 3: return DOWN_LEFT;
		case 4: return LEFT;
		case 5: return UP_LEFT;
		case 6: return UP;
		case 7: return UP_RIGHT;
		}
		//FIXME: This should be two lookup arrays, that's probably more efficient.
		return null; //The number shall ALWYAS be within 0-7.
		//It's ok to crash the program if it's outside that.
	}
	@Override
	public void updateInterm(Model m, Unit u, int dt) {
		//Nothing here yet
	}

	private float getDist() {
		return ((p.getX(currentMove) != p.getX(currentMove + 1)) &&
				(p.getY(currentMove) != p.getY(currentMove + 1)))
				? DIAGONAL_DIST: TILE_DIST;
	}
	
	private int getFacing(){
		//So many ways to decode this... I'm gonna try a totally different method...
		//Maybe I should centralize it in the code somewhere, find the most optimal method
		//with K-maps or some such.
		
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
				return 3;
			} else if(y == 0){
				return 4;
			} else {
				return 5;
			}
		}
	}
}
