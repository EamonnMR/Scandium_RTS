package gui;

/* Class abandoned. */

import net.CmdSender;

import org.newdawn.slick.Graphics;

import data.Sprite;
import game.Model;
import game.PathGrid;
import gui.PlayerMouse.Mode;

public abstract class PlaceBuilding extends Mode {
	
	Sprite spr;
	boolean [][] placement;
	int tx, ty;

	public abstract int[] getBuildingSize();
	
	public abstract void placeBuilding(CmdSender sndr, int[] is);
	
	@Override
	public void draw(Graphics g, int camX, int camY, Model m) {
		spr.draw(0, (tx * 40) - camX, (ty * 40) - camY);
		for(int i = 0; i < placement.length; i++){
			for(int j = 0; j < placement[0].length; j++){
				if(!placement[i][j]){
					g.drawRect(((tx + i) * 40) + 10 - camX, ((ty + j) * 40) + 10 - camY, 20, 20);
				}
			}
		}
		g.drawString(tx + "," + ty, Mouse.i().x, Mouse.i().y);
	}

	public boolean[][] getPlacement(int tx, int ty, Model m, PathGrid pg){
		int[] size = getBuildingSize();
		boolean[][] toSender = new boolean[size[0]][size[1]];
		for(int i = 0; i < size[0]; i++){
			for(int j = 0; j < size[1]; j++){
				toSender[i][j] = pg.b[i + tx][j + ty];
			}
		}
		return toSender;
	}
	
	/**
	 * Returns true if every piece of land is placeable.
	 */
	public boolean evaluatePlacement(){
		boolean toSender = true;
		for(boolean i[] : placement){
			for(boolean j : i){
				toSender = toSender && j;
			}
		}
		return toSender;
	}
	
	@Override
	public boolean unlocked() {
		return true;
	}
	
	public PlayerMouse.Mode setSpr(Sprite spr){
		this.spr = spr;
		return this;
	}

	@Override
	public void update(int dt, int camX, int camY, Model m, PathGrid pg,
			CmdSender sndr, Hud hd, PlayerMouse pm) {
		
		getTilePos(camX,camY, pg, m);
		
		if(Mouse.i().buttons[1]){
			pm.defaultMode();
			return;
		}
		
		if(Mouse.i().buttons[0] && evaluatePlacement()){
			placeBuilding(sndr, hd.getSelectedUnits());
			//If I don't clear the button press here,
			//it will start dragging once the mouse is down.
			Mouse.i().buttons[0] = false;
			pm.defaultMode();
		}
	}
	
	private void getTilePos(int camX, int camY, PathGrid pg, Model m){
		tx =   ((int) (Mouse.i().x + camX) / 40);
		ty =   ((int) (Mouse.i().y + camY) / 40);
		
		if(tx < 0){
			tx = 0;
		} else if (tx + getBuildingSize()[0] >= pg.b.length){
			tx = pg.b.length - getBuildingSize()[0];
		}
		if(ty < 0){
			ty = 0;
		} else if (ty + getBuildingSize()[1] >= pg.b.length){
			ty = pg.b[0].length - getBuildingSize()[1];
		}
		placement = getPlacement(tx, ty, m, pg);
	}
}