package net;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//Simple client to test the server, appropriated from the java tutorials as a test...

public class TestClient {
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
		data.Mgr.i().loadInis();
		int portNumber = Integer.parseInt((String) data.Mgr.i().ports.get("0"));
		String hostName = "localhost";
		
		
		//This is mostly ripped from the Java Tutorials, from Sun.
		
		sunsUglyTestCode(hostName, portNumber);
	}
	
	/* The last remains of the Echo Client code have been... swept away.
	 */
	@SuppressWarnings("resource")
	private static void sunsUglyTestCode(String hostName, int portNumber) throws IOException {
	    		  MsgTrnscv transcv = 
	    			  new DataStreamTrnscv( new Socket(hostName, portNumber) );
	              BufferedReader stdIn =
	                  new BufferedReader(
	                      new InputStreamReader(System.in));
	              String userInput;
	              List<Integer> ints;
	              while ((userInput = stdIn.readLine()) != null){
	            	  ints = fmtIn(userInput);
	            	  System.out.println("Sending message:" +  fmtOut(ints));
	                  transcv.transMsg(ints);
	                  ints = transcv.rcvMsg();
	                  System.out.println(fmtOut(ints));
	              }
	}
	public static String fmtOut(Collection<Integer> collection) {
		String toSender = "(" + collection.size() + ") ";
		for(int i : collection){
			toSender += i;
			toSender += " ";
		}
		return toSender;
	}

	private static List<Integer> fmtIn(String userInput) {
		List<Integer> toSender = new LinkedList<Integer>();
		Scanner scanline = new Scanner(userInput);
		while(scanline.hasNextInt()){
			toSender.add(scanline.nextInt());
		}
		//scanline.close();
		return toSender;
	}
}