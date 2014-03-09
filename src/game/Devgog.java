package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.pathfinding.Path;
/**
 * Utility functions for development. Named in honor of the similar class (or at
 * least concept) that made Zond/RedShift so easy to debug.
 * @author Eamonn
 *
 */
public class Devgog {
	
	public static void drawPathGrid(Graphics g, PathGrid pg, int camX, int camY){
		drawGrid(g, pg.p, camX, camY);
	}
	
	public static void drawBuildGrid(Graphics g, PathGrid pg, int camX, int camY){
		drawGrid(g, pg.b, camX, camY);
	}
	
	public static void drawPath(Graphics g, Path p, int camX, int camY){
		for(int i = 0; i < p.getLength(); i++){
			hilightTile(g, p.getX(i), p.getY(i), camX, camY);
		}
	}
	
	private static void drawGrid(Graphics g, boolean[][] grid, int camX, int camY) {
		for(int i = 0; i < grid.length; i ++){
			for(int j = 0; j < grid[i].length; j++){
				if(grid[i][j]){
					hilightTile(g, i, j, camX, camY);
				}
			}
		}
	}
	
	private static void hilightTile(Graphics g, int x, int y, int camX, int camY){
		g.draw(new Rectangle(camX + (40 * x) + 10,
				camY + (40 * y) + 10,
				20, 20));
	}
}
