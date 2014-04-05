package gui;

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
		for(int i = 0; i < placement.length; i++){
			for(int j = 0; j < placement[0].length; j++){
				if(!placement[i][j]){
					g.drawRect(tx + 10 + camX, ty + 10 + camX, 20, 20);
				}
			}
		}
	}

	public boolean[][] getPlacement(int tx, int ty, Model m, PathGrid pg){
		int[] size = getBuildingSize();
		boolean[][] toSender = new boolean[size[0]][size[1]];
		for(int i = 0; i < size[0]; i++){
			for(int j = 0; j < size[1]; j++){
				toSender[i][j] = pg.b[i + tx][j + tx];
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
		return false;
	}
	
	public PlayerMouse.Mode setSpr(Sprite spr){
		this.spr = spr;
		return this;
	}

	@Override
	public void update(int dt, int camX, int camY, Model m, PathGrid pg,
			CmdSender sndr, Hud hd, PlayerMouse pm) {
		
		tx = (int) Math.round( Mouse.i().x / 40.0 );
		ty = (int) Math.round( Mouse.i().x / 40.0 );
		
		placement = getPlacement(tx, ty, m, pg);
		
		if(Mouse.i().buttons[1]){
			pm.defaultMode();
			return;
		}
		
		if(Mouse.i().buttons[0] && evaluatePlacement()){
			placeBuilding(sndr, hd.getSelectedUnits());
			pm.defaultMode();
		}
	}
}