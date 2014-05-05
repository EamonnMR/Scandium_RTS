package net;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable{
	
	int port, nConnections;
	ServerCore core;
	
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
	
		Thread t = new Thread(new Server(1, Integer.parseInt( (String) data.Mgr.i().ports.get("0"))));
		t.run();
	}
		
	public Server(int nConnections, int port){
		this.nConnections = nConnections;
		this.port = port;
		core = new ServerCore(nConnections);
	}

	@Override
	public void run() {
		ServerSocket sock = null;
		try {
			sock = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("Failed to open ServerSocket");
			e.printStackTrace();
			System.exit(1);
		}
		int slot = 0;
		while(slot < nConnections){
			try {
				new Thread( new ServerThread(
						new DataStreamTrnscv(sock.accept()), core))
						.run();
				slot ++;
			} catch (IOException e) {
				System.out.println("I/O error while waiting on socket");
				System.exit(1);
			}
		}
	}
}