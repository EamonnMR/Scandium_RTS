package game;

/**
 * A simple animation FSA timer 
 * @author Eamonn
 *
 */
public class AnimationTimer {
	int animStates;
	int waitTime;
	int state;
	int time;
	int frameFactor;
	
	/**
	 * 
	 * @param animStates Total number of states
	 * @param waitTime Milliseconds before it advances a state
	 * @param frameFactor How many frames to shift in the sprite 
	 */
	public AnimationTimer(int animStates, int waitTime, int frameFactor){
		this.animStates = animStates;
		this.waitTime = waitTime;
		this.frameFactor = frameFactor;
		state = 0;
		time = 0;
	}
	
	
}
