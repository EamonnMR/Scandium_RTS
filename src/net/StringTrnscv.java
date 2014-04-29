package net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class StringTrnscv extends MsgTrnscv{
	Scanner is;
	PrintWriter os;
	
	Socket sock;
	public StringTrnscv(Socket sock) throws IOException{
		
		os = new PrintWriter ( sock.getOutputStream() );
		is = new Scanner( sock.getInputStream() );
		this.sock = sock;
	}

	public void transMsg(List<Integer> msg) throws IOException{
		System.out.println("Sending...");
		writeLine(msg.size());
		for(int i : msg){
			writeLine(i);
		}
		System.out.println("Done sending.");
	}
	
	public List<Integer> rcvMsg() throws IOException{
		
		List<Integer> toSender = new LinkedList<Integer>();
		System.out.println("Awaiting message");
		int msgSize = readLine(); //The problem appears to be here: it never reads a line.
		System.out.println("Reading message: Size = " + msgSize);
		for(int i = 0; i < msgSize; i++){
			toSender.add(readLine());
		}
		return toSender;
		
	}
	
	private void writeLine(int i){
		System.out.println(i);
		os.println(i);
	}
	
	private int readLine(){
		return Integer.parseInt(is.nextLine());
	}
}
