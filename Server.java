package com.example.networking;

import java.net.*;
import java.io.*;

public class GreetingServer extends Thread {
	private ServerSocket serverSocket;

	public GreetingServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		//serverSocket.setSoTimeout(10000);
	}

	public void run() {
		while (true) {
			try {
				System.out.println("Waiting for the client on port "
						+ serverSocket.getLocalPort() + "...");

				Socket server = serverSocket.accept();

				System.out.println("server: Just connected to " + server.getRemoteSocketAddress());
/*
				DataInputStream in = new DataInputStream(server.getInputStream());

				System.out.println(in.readUTF());
				System.out.println(in.readUTF());
				//System.out.println(in.readUTF());

				DataOutputStream out = new DataOutputStream(server.getOutputStream());

				out.writeUTF("Thank you for connecting to "
						+ server.getLocalSocketAddress() + "\nGoodbye!");
*/
				
				DataInputStream in = new DataInputStream(server.getInputStream());
				System.out.println("server: received from client, " + in.readUTF());
				
				server.close();
			} catch (SocketTimeoutException s) {
				System.out.println("Socket timed out!");
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	public static void main(String[] args) 
	{	
		//int port = Integer.parseInt(args[0]);
		int port = 6066;
		
		try 
		{
			Thread thread = new GreetingServer(port);
			thread.start();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
