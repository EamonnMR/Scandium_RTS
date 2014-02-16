package data;

import org.newdawn.slick.Image;

public class Sprite {
	private Image img;
	private int xSize;
	private int ySize;
	private int xFrames;
	private int yFrames;
	private int xOffset;
	private int yOffset;

	public Sprite (Image img, int xSize, int ySize, int xFrames, int yFrames, int xOffset, int yOffset){
		this.img = img;
		this.xSize = xSize;
		this.ySize = ySize;
		this.xFrames = xFrames; 
		this.yFrames = yFrames;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void draw(int frame, int x, int y){
		int yLine = frame % yFrames;
		img.draw(x - xOffset, y - yOffset,xSize * ((frame - yLine) % xFrames), yLine * ySize, xSize, ySize);
	}
}
