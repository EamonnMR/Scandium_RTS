package net;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

//Simple client to test the server, appropriated from the java tutorials as a test...

public class EchoClient {
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
		data.Mgr.i().loadInis();
		int portNumber = Integer.parseInt((String) data.Mgr.i().ports.get("0"));
		String hostName = "localhost";
		
		
		//This is mostly ripped from the Java Tutorials, from Sun.
	      try (
	              Socket echoSocket = new Socket(hostName, portNumber);
	              PrintWriter out =
	                  new PrintWriter(echoSocket.getOutputStream(), true);
	              BufferedReader in =
	                  new BufferedReader(
	                      new InputStreamReader(echoSocket.getInputStream()));
	              BufferedReader stdIn =
	                  new BufferedReader(
	                      new InputStreamReader(System.in))
	          ) {
	              String userInput;
	              while ((userInput = stdIn.readLine()) != null) {
	                  out.println(userInput);
	                  System.out.println(in.readLine());
	              }
	          } catch (UnknownHostException e) {
	              System.err.println("Don't know about host " + hostName);
	              System.exit(1);
	          } catch (IOException e) {
	              System.err.println("Couldn't get I/O for the connection to " +
	                  hostName);
	              System.exit(1);
	          } 
	      }
}