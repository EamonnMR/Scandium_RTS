package behavior;

import org.newdawn.slick.geom.Rectangle;

import game.Model;
import game.Unit;

public class Extractor extends UnitState {

	int animationTimer;
	final int animationTick = 200;
	boolean gathering = false;
	int animationState;
	final int maxAnimState = 9;
	
	public Extractor(){
		animationTimer = 0;
		animationState = 0;
	}
	
	
	@Override
	public void update(Model m, Unit u) {
		for(Unit uni : m.areaQuerey(new Rectangle(u.x - 40, u.y - 40,160, 160))){
			if(uni.canGather){
				m.incrResource(uni.owner, 1);
				gathering = true;
				return;
				//This needs to be set up so that two gatherers of opposing players cancel eachother out.
			}
		}
		gathering = false;
	}

	@Override
	public void updateInterm(Model m, Unit u, int dt) {
		/*
		 * This animates the unit.
		 */
		if(gathering){
			animationTimer += dt;
			while(animationTimer >= animationTick){
				animationTimer -= animationTick;
				animationState ++;
				if(animationState >= maxAnimState){
					animationState = 0;
				}
				//Make the animation play back and fourth-hacky
				//but it's the only animation in the game, so ...
				if(animationState > 4){
					u.setFacing(9 - animationState);
				} else {
					u.setFacing(animationState);
				}
			}
		}
	}

}
