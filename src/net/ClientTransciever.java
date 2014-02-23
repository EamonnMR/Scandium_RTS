package net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import commands.Command;

public class ClientTransciever extends game.CmdSender implements game.Reciever{

	Socket sock;
	Scanner getter;
	OutputStream giver;
	
	public ClientTransciever (int port, String host) throws IOException{
		try{
			sock = new Socket(host, port);
			getter = new Scanner(sock.getInputStream());
			giver = sock.getOutputStream();
		}
		catch(UnknownHostException e){
			System.out.println("Could not connect to " + host);
			e.printStackTrace();
		}
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
		giver.write(message.getBytes());
	}
}
