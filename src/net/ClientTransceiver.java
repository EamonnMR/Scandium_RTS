package net;

import instructions.Command;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * This handles both sending and recieving messages for the client.
 * It's a thin layer over MsgTrnscv
 * @author Eamonn
 *
 */

public class ClientTransceiver extends CmdSender implements Reciever{

	MsgTrnscv trns;
	
	public ClientTransceiver (MsgTrnscv trns) throws IOException{
		this.trns = trns;
	}
	
	@Override
	public List<Command> getLatestCommands() {
		List<Command> toSender = new LinkedList<Command>();
		List<Integer> code;
		try {
			code = trns.rcvMsg();
			while(code.size() > 0){
				toSender.add(new Command(code));
			}
			return toSender;
		} catch (IOException e) {
			System.out.println("Error reading from MsgTrnscv");
			System.exit(1);
		}
		return null;
	}

	@Override
	public void pushToServer(List<Command> cmds) throws IOException {
		List<Integer> msg = new LinkedList<Integer>();
		for(Command c : cmds){
			msg.addAll(c.toInts());
		}
		trns.transMsg(msg);
	}
}
