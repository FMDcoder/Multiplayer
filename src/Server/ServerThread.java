package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import engine.Main;

public class ServerThread implements Runnable {
	
	private boolean 
		createServer = false;
		
	public ServerSocket serverSocket;
	public Socket socket;
	public BufferedReader br;
	public PrintWriter pw;
	
	public String adress;
	public int port;
	
	public synchronized int createServer() {
		createServer = true;
		try {
			serverSocket = new ServerSocket(0);
			System.out.println("192.168.1.153:"+serverSocket.getLocalPort());
			serverSocket.accept();
			
			return serverSocket.getLocalPort();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public synchronized void createServer(int port) {
		createServer = true;
		try {
			serverSocket = new ServerSocket(port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void connectToServer(String host, int port) {
		Main.client.connect(host, port);
	}
	
	public synchronized void setUpInfo(Socket socket) {
		try {
			br = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			
			pw = new PrintWriter(socket.getOutputStream(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void closeConnection() {
		Main.client.closeConnection();
		if(socket != null) {
			try {
				socket.close();
				socket = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(serverSocket != null) {
			try {
				serverSocket.close();
				serverSocket = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Server Closed!");
	}
	
	public synchronized void sendMessage(String str) {
		System.out.println(socket.getRemoteSocketAddress());
		pw.println(str);
	}
	
	@Override
	public void run() {
		while(true) {
			synchronized (this) {
				if(createServer) {
					try {
						socket = serverSocket.accept();
						
						System.out.println("Server connected!");
						
						adress = InetAddress.getLocalHost().getHostAddress();
						port = socket.getLocalPort();
						
						System.out.println(adress+":"+port);
						
						pw = new PrintWriter(socket.getOutputStream(), true);
						br = new BufferedReader(
								new InputStreamReader(socket.getInputStream()));
					} catch (Exception e) {
						e.printStackTrace();
					}
					createServer = false;
				}
			}
			
			try {
				if(br != null) {
					String line;
					while(br.ready()) {
						line = br.readLine();
						System.out.println(line);
						Main.pack.interperate(line);
					}
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
