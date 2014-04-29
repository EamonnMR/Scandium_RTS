package net;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * This handles message sending and receiving.
 * 
 * The first int of a message is always the number of (remaining) ints in the message-
 * so if your message is 3,4,5,6, it'll be sent as 4,3,4,5,6. An empty message will be
 * sent simply as 0.
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
