package net;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ServerThread implements Runnable{

	MsgTrnscv trans;
	ServerCore core;
	int id;
	
	public ServerThread(MsgTrnscv trans, ServerCore core, int id){
		this.trans = trans;
		this.core = core;
		this.id = id;
	}
	
	@Override
	public void run() {
		System.out.println("Running server thread");
		sendInitialMsg();
		while(true){
			recieve();
			waitForOthers();
			transmit();
		}
	}

	private void sendInitialMsg() {
		List<Integer> theInitialCommand = new LinkedList<Integer>();
		try {
			trans.transMsg(theInitialCommand);
		} catch (IOException e) {
			report("Connection failed at sending initial command");
			System.exit(1);
		}
	}

	private void waitForOthers() {
		report("Waiting...");
		while(core.waiting()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void recieve() {
		report("Recieving");
		/*These are seperate because
		submit is synchronzed and rcvMsg can
		take a long time to return (as long
		as it wants) and that's the whole 
		purpose of the threaded server.
		*/
		List<Integer> msg;
		try {
			msg = trans.rcvMsg();
			System.out.println("Recieved msg: " + TestClient.fmtOut(msg));
			core.submit(msg);
		} catch (IOException e) {
			// If msg.rcvMsg fails to return,
			// all might be lost. Treat any info
			// sent as lost.
			/*System.out.println("Skipped message");
			e.printStackTrace();
			core.submit(new ArrayList<Integer>());*/
			
			//Disconnect-game over ?
			System.exit(1);
		}
	}
	
	private void transmit() {
		report("transmitting");
		try {
			trans.transMsg(core.getFullMsg());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void report(String rprt){
		System.out.println(id + ": " + rprt);
	}
}
