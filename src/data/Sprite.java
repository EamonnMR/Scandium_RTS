package data;

import org.newdawn.slick.Image;

public class Sprite {
	private int xOffset;
	private int yOffset;
	private Image[] frames;

	public Sprite (Image img, int xSize, int ySize, int xFrames, int yFrames, int xOffset, int yOffset){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		
		frames = new Image[xFrames * yFrames];
		for(int i = 0; i < yFrames; i++){
			for(int j = 0; j < xFrames; j++){
				frames[i * yFrames + j] = img.getSubImage(
						j * xSize, i * ySize, xSize, ySize);
			}
		}
	}
	
	public void draw(int frame, int x, int y){
		frames[frame].draw(xOffset + x, yOffset + y);
	}
}
