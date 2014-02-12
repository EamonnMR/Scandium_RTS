package net;

import java.io.IOException;
import java.net.Socket;

public class ConThread extends Thread{
	
	String messageBuffer;
	int index;
	Socket sock; 
	boolean pushed = false;
	Repeater owner;

	public ConThread(Socket sock, int index, Repeater owner) {
		this.sock = sock;
		this.index = index;
		this.owner = owner;
	}

	public void pushMsg(String message) {
		pushed = true;
		messageBuffer = message.substring(0);
	}
	
	public void run(){
		while(!sock.isClosed()){
			if(pushed){
				pushed = false;
				try {
					sock.getOutputStream().write(messageBuffer.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//Read from socket, send as message
			String msg = "";
			try {
				while(sock.getInputStream().available() > 0){
					msg += Byte.toString((byte) sock.getInputStream().read());
				}
				System.out.println(msg);
				owner.rcvMsg(msg,index);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
