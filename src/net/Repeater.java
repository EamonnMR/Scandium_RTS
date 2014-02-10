package net;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;

public class Repeater {
	
	ConThread[] threads;
	private int[] ports;
	String[] messages;
	
	public Repeater(int[] ports) {
		this.ports = ports;
		threads = new ConThread[ports.length];
		messages = new String[ports.length];
	}
	
	public void rcvMsg(String msg, int thread){
		if(messages[thread] != null){
			System.err.println("Synch error: thread" + new Integer(thread).toString()  + "attempted to write to msg buffer twice in a row");
			System.exit(-1);
		} else {
			messages[thread] = msg;
		}
	}
	
	public void makeConnections(){
		for(int i = 0; i < ports.length; i++){
			try (ServerSocket serverSocket = new ServerSocket(i)) { 
				threads[i] = new ConThread(serverSocket.accept(), i, this);
				threads[i].start();
			} catch (IOException e) {
				System.err.println("Could not listen on port " + i);
				System.exit(-1);
			}
		}
	}
	
	public void updateLoop() {
		//Wait for messages in the while loop
		boolean remain = true;
		while(remain){
			remain = false;
			for(String i : messages){
				if (i != null){
					remain = true;
					break;
				}
			}
		}
		
		//Each thread has reported in;
		
		String message = processMsgs(messages);
		
		while(true){
			for(ConThread i : threads){
				i.pushMsg(message);
			}
		}
	}
	private String processMsgs(String[] messages) {
		String toSender = "";
		for(String i : messages){
			toSender += i;
		}
		return toSender;
	}

	public static void main(String args[]) throws FileNotFoundException, IOException{
		//Processes args; if none are provided, use the defaults in the ports.ini file.
		int[] ports = null;
		boolean useIni = true;//args.length == 0;
		
		data.Mgr.i().loadInis();
		
		int length = useIni ?
				data.Mgr.i().ports.size()://:
				args.length;
		
		ports = new int[length];
		for(int i = 0; i < length; i++){
			String si = new Integer(i).toString();
			ports[i] = Integer.parseInt( useIni ? 
					(String) data.Mgr.i().ports.get(si):
					args[i]
			);
		}
		Repeater me = new Repeater(ports);
		me.makeConnections();
		me.updateLoop();
	}
}
