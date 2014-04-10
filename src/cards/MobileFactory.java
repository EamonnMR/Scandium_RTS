package cards;

import org.newdawn.slick.util.pathfinding.Path;

import behavior.ProduceUnit;
import commands.Instruction;
import commands.RequisitionUnit;
import commands.Teleport;
import game.Model;
import game.Unit;
import gui.Hud;
import gui.Hud.Button;

public class MobileFactory extends CommandCard {

	public MobileFactory(Button[] earlyAccessToButtons) {
		super(earlyAccessToButtons);
		btns[0] = earlyAccessToButtons[4];
		btns[1] = earlyAccessToButtons[0];
		btns[2] = earlyAccessToButtons[1];
		btns[3] = earlyAccessToButtons[2];
	}
	
	public boolean movesByPath() {
		return true;
	}

	@Override
public void actuate(Instruction cmd, Unit u, Model m) {
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
		if(cmd.getClass() == RequisitionUnit.class){
			u.enterState(new ProduceUnit(
					((RequisitionUnit) cmd).unitToProduce
					)); 
		}
		
	}
	
}
