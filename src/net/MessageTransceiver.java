package net;

//import java.io.DataInputStream;
//import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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
	/* DataInputStream is;
	   DataOutputStream os;
	*/
	
	Scanner is;
	PrintWriter os;
	
	Socket sock;
	public MessageTransceiver(Socket sock) throws IOException{
		/*is = new DataInputStream(sock.getInputStream());
		os = new DataOutputStream(sock.getOutputStream());*/
		
		os = new PrintWriter ( sock.getOutputStream() );
		is = new Scanner( sock.getInputStream() );
		this.sock = sock;
	}
	
	/*
	 * This version uses WriteInt
	public void transMsg(Collection<Integer> msg) throws IOException{
		os.writeInt(msg.size());
		System.out.println("Sending message....");
		for(Integer i : msg){
			os.writeInt(i);
		}
		//os.flush(); //Just in case
	}*/
	
	/*
	 * This version uses ReadInt
	  public List<Integer> rcvMsg() throws IOException{
	 
		//List<Integer> toSender = new LinkedList<Integer>();
		//for(int i = 0; i < is.readInt(); i++){
		//	toSender.add(is.readInt());
		//}
		//return toSender;
		
		List<Integer> toSender = new LinkedList<Integer>();
		System.out.println("Awaiting message");
		int msgSize = is.readInt();
		System.out.println("Reading message: Size = " + msgSize);
		for(int i = 0; i < msgSize; i++){
			toSender.add(is.readInt());
		}
		return toSender;
		
	}*/
	
	//Ugly but currently needed hack-trying to make the thing actually transmit and rcv.
	
	public void transMsg(Collection<Integer> msg) throws IOException{
		System.out.println("Sending...");
		writeLine(msg.size());
		for(int i : msg){
			writeLine(i);
		}
		System.out.println("Done sending.");
	}
	
	public List<Integer> rcvMsg() throws IOException{
		
		List<Integer> toSender = new LinkedList<Integer>();
		System.out.println("Awaiting message");
		int msgSize = readLine();
		System.out.println("Reading message: Size = " + msgSize);
		for(int i = 0; i < msgSize; i++){
			toSender.add(readLine());
		}
		return toSender;
		
	}
	
	private void writeLine(int i){
		os.println(i);
	}
	
	private int readLine(){
		return Integer.parseInt(is.nextLine());
	}
	
	@Override
	public void close() throws Exception {
		System.out.println("Socket closed");
		sock.close();
	}
	
}
