package net;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

//Simple client to test the server

public class Client {
	
	public static void Main(String[] args) throws FileNotFoundException, IOException{
		data.Mgr.i().loadInis();
		int port = Integer.parseInt((String) data.Mgr.i().ports.get("0"));
		
		//This is mostly ripped from the 
		
		Socket kkSocket = new Socket("localhost", port);
	    PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
	    
	}
}