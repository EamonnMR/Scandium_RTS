package net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import commands.Command;

public class ClientTransciever extends game.CmdSender implements game.Reciever{

	Scanner getter;
	OutputStream giver;
	
	public ClientTransciever (Socket sock) throws IOException{
		getter = new Scanner(sock.getInputStream());
		giver = sock.getOutputStream();
	}
	
	@Override
	public List<Command> getLatestCommands() {
		List<Command> toSender = new LinkedList<Command>();
		String code = getter.nextLine(); //FIXME: Is there a better way to make it wait for a space?
		Scanner breaker = new Scanner(code);
		while(breaker.hasNext()){
			toSender.add(new Command(breaker.next()));
		}
		breaker.close();
		return toSender;
	}

	@Override
	public void pushToServer(List<Command> cmds) throws IOException {
		String message = "";
		for(Command cmd : cmds){
			message += cmd.toCode();
		}
		giver.write((message + "\n").getBytes());
	}
}
