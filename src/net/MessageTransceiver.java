package net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.LinkedList;
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
public class MessageTransceiver implements java.lang.AutoCloseable{
	DataInputStream is;
	DataOutputStream os;
	Socket sock;
	public MessageTransceiver(Socket sock) throws IOException{
		is = new DataInputStream(sock.getInputStream());
		os = new DataOutputStream(sock.getOutputStream());
		this.sock = sock;
	}
	
	public void transMsg(Collection<Integer> msg) throws IOException{
		os.writeInt(msg.size());
		for(Integer i : msg){
			os.writeInt(i);
		}
		os.flush(); //Just in case
	}
	
	public List<Integer> rcvMsg() throws IOException{
		List<Integer> toSender = new LinkedList<Integer>();
		for(int i = 0; i < is.readInt(); i++){
			toSender.add(is.readInt());
		}
		return toSender;
	}

	@Override
	public void close() throws Exception {
		sock.close();
	}
	
}
