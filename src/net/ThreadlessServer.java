package net;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/*
 * Trying out a threadless server so I can get the network basics down and document
 * what I'm trying to actually accomplish-I'm trying to isolate problems and this
 * problem is the actual network communication.
 */

public class ThreadlessServer {
	
	private static List<Integer> msg;

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
			msg =  new LinkedList<Integer>();
			for(Connection i : con){
				System.out.println("Calling getMsg");
				processMsg(i.getMsg(), i);
			}
			//Now that processing is complete...
			
			for(Connection i: con){
				i.sendMsgs(msg);
			}			
		}
	}
	
	private static void processMsg(List<Integer> list, Connection i) {
		msg.addAll(list);
	}

	private static class Connection{
		
		MsgTrnscv con;
		int port;
		
		@SuppressWarnings("resource")
		public Connection(int port) throws IOException{
			//FIXME: Put this into a thread
			this.port = port;
			con = new DataStreamTrnscv( new ServerSocket(port).accept());
		}
		
		public List<Integer> getMsg(){
			try {
				return con.rcvMsg();
			} catch (IOException e) {
				System.out.println("Failed to read from " + port);
				System.exit(-1);
			}
			//To satisfy the compiler;
			return null;
		}
		
		public void sendMsgs(List<Integer> msg){
			try {
				con.transMsg(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
