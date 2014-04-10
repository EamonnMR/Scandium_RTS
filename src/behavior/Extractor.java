package behavior;

import org.newdawn.slick.geom.Circle;

import game.Model;
import game.Unit;

public class Extractor extends UnitState {

	int animationTimer = 0;
	int animationTick = 10;
	boolean gathering = false;
	int animationState = 0;
	int maxAnimState = 4;
	
	@Override
	public void update(Model m, Unit u) {
		for(Unit uni : m.areaQuerey(new Circle(u.x, u.y,100000))){
			if(uni.canGather){
				m.incrResource(u.owner, 1);
				gathering = true;
				break;
				//This needs to be set up so that two gatherers of opposing players cancel eachother out.
			}
		}
		gathering = false;
	}

	@Override
	public void updateInterm(Model m, Unit u, int dt) {
		if(gathering){
			animationTimer += dt;
			while(animationTimer >= animationTick){
				animationTimer -= animationTick;
				animationState ++;
				if(animationState >= maxAnimState){
					animationState = 0;
				}
				u.setFacing(animationState);
			}
		}
	}

}
