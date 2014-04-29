package net;

import instructions.Command;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ClientTransceiver extends CmdSender implements Reciever{

	MsgTrnscv trns;
	
	public ClientTransceiver (Socket sock) throws IOException{
		trns = new StringTrnscv(sock);
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
		}
		return null;
	}

	@Override
	public void pushToServer(List<Command> cmds) throws IOException {
		List<Integer> code = new LinkedList<Integer>();
		for(Command c : cmds){
			code.addAll(c.toInts());
		}
	}
}
