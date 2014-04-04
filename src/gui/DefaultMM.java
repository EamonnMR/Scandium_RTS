package gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;

import data.Sprite;
import game.CmdSender;
import game.Model;
import game.PathGrid;
import game.Util;

public class DefaultMM extends PlayerMouse.Mode{
	boolean isDragging;
	Rectangle selectBox;
	int selectionX, selectionY;
	private Sprite sprite;
	boolean mouseRight;
	
	public DefaultMM(Sprite mouseSpr) {
		this.sprite = mouseSpr;
	}

	@Override
	public void draw(Graphics g, int camX, int camY, Model m) {
		if(selectBox != null){
			g.draw(selectBox);
		}
		if(!hidden){
			sprite.draw(11, Mouse.i().x, Mouse.i().y);
		}
	}

	@Override
	public void update(int dt, int camX, int camY, Model m, PathGrid pg,
			CmdSender sndr, Hud hd, PlayerMouse pm) {
		if(Mouse.i().y <= hd.getMaxY()){
			if(isDragging){
				Line selln = new org.newdawn.slick.geom.Line(Mouse.i().x, Mouse.i().y, selectionX, selectionY);
				selectBox = new Rectangle(selln.getMinX(), selln.getMinY(), Math.abs(selln.getDX()), Math.abs(selln.getDY()));
				if(!Mouse.i().buttons[0]){
				//FIXME: Mouse released from selection: querey the model for units inside the selection box
					isDragging = false;
					
					hd.changeSelection(m.areaQuerey(
							new Rectangle(selectBox.getX() - camX, 
							selectBox.getY() - camY,
							selectBox.getWidth(),
							selectBox.getHeight())));
					
					selectBox = null;
				}
			} else if(Mouse.i().buttons[0] && !isDragging){
				isDragging = true;
				selectionX = Mouse.i().x;
				selectionY = Mouse.i().y;
			} 
			
			//Handle right click
			if(mouseRight){
				mouseRight = Mouse.i().buttons[1];
			} else if(Mouse.i().buttons[1]){
				mouseRight = true;
				Util.issueMoveCmd(Mouse.i().x - camX, Mouse.i().y - camY, m, pg,  hd.getSelectedUnits(), sndr);
			}
		} else {
			isDragging = false;
		}
	}
	
	public boolean unlocked(){
		return !isDragging;
	}

}
