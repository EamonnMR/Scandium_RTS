package game;

import org.newdawn.slick.tiled.TiledMap;
/**
 * Creates and holds data for building location acceptability and pathfinding.
 * It uses data stored in the tileset to determine if a given tile can be traversed,
 * and if so, can it be built upon.
 * @author Eamonn
 *
 */
public class PathGrid {
	public boolean [][] p; //Pathable grid
	public boolean [][] b; //buildable grid
	
	//I've left them public because they will be modified by other classes,
	//and I want to keep the nice subscript notation.
	
	public PathGrid(TiledMap t){
		p = new boolean[t.getWidth()][t.getHeight()];
		b = new boolean[t.getWidth()][t.getHeight()];
		
		for(int i = 0; i < t.getWidth(); i ++){
			for(int j = 0; j < t.getHeight(); j ++){
				//FIXME: This is totally untested, who knows if it's really this simple
				if(getTileFlag(t, i, j, "p")){
					p[i][j] = true;
					b[i][j] = getTileFlag(t, i, j, "b");
				}
			}
		}
	}
	private static boolean getTileFlag(TiledMap m, int x, int y, String prop){
		return m.getTileProperty(m.getTileId(x, y, 0), prop, "f").equals("t");
	}
}