package net;

import instructions.Command;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ClientTransceiver extends CmdSender implements Reciever{

	MsgTrnscv trns;
	
	public ClientTransceiver (Socket sock) throws IOException{
		trns = new DataStreamTrnscv(sock);
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
