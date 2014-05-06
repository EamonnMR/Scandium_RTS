package net;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * Uses a socket to send and recieve messages that are composed
 * of Lists of Integers.
 * 
 * @author Eamonn
 *
 */
public abstract class MsgTrnscv implements java.lang.AutoCloseable{
	Socket sock;
	@Override
	public void close() throws Exception {
		System.out.println("About to close socket");
		sock.close();
		System.out.println("Socket closed");
	}
	
	public abstract void transMsg(List<Integer> msg) throws IOException;
	
	public abstract List<Integer> rcvMsg() throws IOException;
}
