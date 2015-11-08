package com.example.network;

import java.net.*;
import java.io.*;

public class GreetingClient 
{	
	public static void main(String[] args) 
	{	
		//String serverName = args[0];
		//int port = Integer.parseInt(args[1]);
		String serverName = "localhost";
		int port = 6066;		
		
		try 
		{
			System.out.println("Connecting to " + serverName + " on port " + port);
			
			// A socket is an endpoint for communication between two machines. 
			Socket client = new Socket(serverName, port); 
			
			System.out.println("client: Just connected to " + client.getRemoteSocketAddress());
/*			
			// output stream to send messages
			OutputStream outToServer = client.getOutputStream();
			// A data output stream lets an application write primitive Java data types 
			// to an output stream in a portable way. An application can then use a data 
			// input stream to read the data back in.
			DataOutputStream out = new DataOutputStream(outToServer);
			
			out.writeUTF("Hello from " + client.getLocalSocketAddress());
			
			out.writeUTF("\nHELLO\n");
			
			// stream that grabs input
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			
			System.out.println("Server says " + in.readUTF());
*/
			
			DataOutputStream out = new DataOutputStream(client.getOutputStream());
			out.writeUTF("client: username: brain, password: pw");
			
			client.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
