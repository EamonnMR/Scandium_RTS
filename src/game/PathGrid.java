package game;

import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;
/**
 * Creates and holds data for building location acceptability and pathfinding.
 * It uses data stored in the tileset to determine if a given tile can be traversed,
 * and if so, can it be built upon.
 * @author Eamonn
 *
 */
public class PathGrid implements TileBasedMap{
	
	private static int MAX_LEN = 500;
	
	public boolean [][] p; //Pathable grid
	public boolean [][] b; //buildable grid
	private AStarPathFinder a;
	
	//I've left them public because they will be modified by other classes,
	//and I want to keep the nice subscript notation.
	
	public PathGrid(TiledMap t){
		p = new boolean[t.getWidth()][t.getHeight()];
		b = new boolean[t.getWidth()][t.getHeight()];
		
		a = new AStarPathFinder(this, MAX_LEN, false); //Right now we're not allowing diagonal mvmnt
		
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
	
	public Path getPath(int startX, int startY, int goalx, int goaly){
		return a.findPath(null, startX, startY, goalx, goaly );
	}
	
	@Override
	public boolean blocked(PathFindingContext arg0, int x, int y) {
		return p[x][y];
	}
	@Override
	public float getCost(PathFindingContext arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public int getHeightInTiles() {
		return p[0].length;
	}
	@Override
	public int getWidthInTiles() {
		return p.length;
	}
	@Override
	public void pathFinderVisited(int arg0, int arg1) {
		//This is empty for now
	}
	private static boolean getTileFlag(TiledMap m, int x, int y, String prop){
		return m.getTileProperty(m.getTileId(x, y, 0), prop, "f").equals("t");
	}
}