package net;

import instructions.Command;

import java.util.LinkedList;
import java.util.List;

public class LocalRcv implements Reciever {

	List<Command> cmds;
	
	public void localRcv(){
		cmds = new LinkedList<Command>();
	}
	
	@Override
	public List<Command> getLatestCommands() {
		// TODO Auto-generated method stub
		return cmds;
	}

	public void pushCmds(List<Command> cmds) {
		this.cmds = cmds;
		if(cmds == null);
			cmds = new LinkedList<Command>();
	}

}
