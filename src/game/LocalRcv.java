package game;

import java.util.LinkedList;
import java.util.List;

import commands.Command;

public class LocalRcv extends Reciever {

	List<Command> cmds;
	
	public void localRcv(){
		cmds = new LinkedList<Command>();
	}
	
	@Override
	List<Command> getLatestCommands() {
		// TODO Auto-generated method stub
		return cmds;
	}

	public void pushCmds(List<Command> cmds) {
		this.cmds = cmds;
		
	}

}
