package net;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/*
 * Trying out a threadless server so I can get the network basics down and document
 * what I'm trying to actually accomplish-I'm trying to isolate problems and this
 * problem is the actual network communication.
 */

public class ThreadlessServer {
	
	private static String msg;

	public static void main(String[] args){
		try {
			data.Mgr.i().loadInis();
		} catch (FileNotFoundException e) {
			System.out.println("Can't load ports.ini");
			System.exit(-1);
		} catch (IOException e) {
			System.out.println("Can't read ports.ini");
			System.exit(-1);
		}
		Properties ports = data.Mgr.i().ports;
		
		//Setup Connections
		
		Connection[] con = new Connection[ports.size()];
		
		for(int i = 0; i < ports.size(); i++){
			try {
				con[i] = new Connection(Integer.parseInt((String)ports.get(Integer.toString(i))));
			} catch (IOException e) {
				System.out.println("Cannot connect to client on port " + ports.get(Integer.toString(i)));
			}
		}
		while(true){
			msg = "";
			for(Connection i : con){
				processMsg(i.getMsg(), i);
			}
			//Now that processing is complete...
			for(Connection i: con){
				i.sendMsgs(msg);
			}			
		}
	}
	
	private static void processMsg(String inp, Connection i) {
		msg += inp;
	}

	private static class Connection{
		Socket sock;
		public int port;
		PrintWriter out;
		BufferedReader in; 
		@SuppressWarnings("resource")
		public Connection(int port) throws IOException{
			//FIXME: Put this into a thread
			this.port = port;
			sock = new ServerSocket(port).accept();
			out = new PrintWriter( sock.getOutputStream(), true);
			in = new BufferedReader( new InputStreamReader( sock.getInputStream()));
		}
		
		public String getMsg(){
			try {
				return in.readLine();
			} catch (IOException e) {
				System.out.println("Failed to read from " + port);
				System.exit(-1);
			}
			//To satisfy the compiler;
			return null;
		}
		
		public void sendMsgs(String msg){
			out.println(msg);
		}
		
	}
	
}
