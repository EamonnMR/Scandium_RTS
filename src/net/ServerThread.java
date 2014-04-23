package net;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ServerThread implements Runnable{

	MessageTransceiver trans;
	ServerCore core;
	
	public ServerThread(MessageTransceiver trans, ServerCore core){
		this.trans = trans;
		this.core = core;
	}
	
	@Override
	public void run() {
		while(true){
			recieve();
			waitForOthers();
			transmit();
		}
	}

	private void waitForOthers() {
		while(core.waiting()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void recieve() {
		/*These are seperate because
		submit is synchronzed and rcvMsg can
		take a long time to return (as long
		as it wants) and that's the whole 
		purpose of the threaded server.
		*/
		Collection<Integer> msg;
		try {
			msg = trans.rcvMsg();
			core.submit(msg);
		} catch (IOException e) {
			// If msg.rcvMsg fails to return,
			// all might be lost. Treat any info
			// sent as lost.
			e.printStackTrace();
			core.submit(new ArrayList<Integer>());
		}
	}
	
	private void transmit() {
		try {
			trans.transMsg(core.getFullMsg());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
