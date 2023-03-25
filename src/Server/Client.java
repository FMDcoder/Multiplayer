package Server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

import engine.Main;

public class Client {
	public Socket socket;
	public BufferedReader br;
	public PrintWriter pw;
	
	public void connect(int port) {
		try {
			socket = new Socket("localhost", port);
			
			System.out.println("User connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void connect(String adress, int port) {
		try {
			socket = new Socket(adress, port);
			Main.serverThread.setUpInfo(socket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		if(socket == null) {return;}
		
		try {
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
