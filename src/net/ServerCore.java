package net;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ServerCore {
	private int nSub;
	private int nGot;
	private int total;
	private List<Integer> fullMessage;
	private boolean state; //True = Listening False = doneListening
	
	public ServerCore(int total){
		this.total = total;
		fullMessage = new LinkedList<Integer>();
		state = true;
	}
	
	public synchronized void submit(Collection<Integer> c){
		fullMessage.addAll(c);
		nSub++;
		//System.out.println("Message Submitted; nSub = " + nSub);
		if(nSub == total){
			nSub = 0;
			state = false;
		}
	}
	
	public boolean waiting(){
		return state;
	}
	
	public synchronized List<Integer> getFullMsg(){
		List<Integer> toSender = fullMessage;
		nGot++;
		//System.out.println("Got message; nGot = " + nGot);
		if(nGot == total){
			nGot = 0;
			state = true;
			fullMessage = new LinkedList<Integer>();
		}
		return toSender;
	}
}
