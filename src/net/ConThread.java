package net;

import java.net.Socket;

public class ConThread extends Thread{
	
	String[] messageBuffer;
	int index;
	Socket sock; 
	boolean pushed = false;

	public ConThread(Socket accept, int i, int length) {
		messageBuffer = new String[length];
	}

	public void pushMsg(String[] messages) {
		pushed = true;
		for(int i = 0; i < messages.length; i++){
			//Copy the message FIXME: Does this actually work?
			messageBuffer[i] = messages[i].substring(0);
		}
	}
}
