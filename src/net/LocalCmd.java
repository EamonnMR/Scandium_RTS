package net;

import instructions.Command;

import java.util.List;

/**
 * In this very simple class, we fake an outgoing stream of net information, 
 * looping it back to local receiver.
 * @author Eamonn
 *
 */
public class LocalCmd extends CmdSender{

	LocalRcv r;

	public LocalCmd(LocalRcv r) {
		this.r = r;
	}
	
	@Override
	public void pushToServer(List<Command> cmds) {
		r.pushCmds(cmds);
	}

}
