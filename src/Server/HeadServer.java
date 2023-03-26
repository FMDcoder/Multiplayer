package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import Scene.Playground;
import engine.Main;

public class HeadServer implements Runnable {
	
	private boolean 
		isConnecting = false,
		createServer = false;
	
	private int PORT;
	private String ADRESS, TYPEOFCONNECTION;
	
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader out;
	private PrintWriter in;
	
	public Package pack = new Package();
	
	public synchronized void connect() {
		createServer = true;
		isConnecting = true;
		
		this.TYPEOFCONNECTION = "HEADCLIENT";
	}
	
	public synchronized void connect(String adress, int port) {
		this.ADRESS = adress;
		this.PORT = port;
		
		this.TYPEOFCONNECTION = "SIDECLIENT";
		
		createServer = false;
		isConnecting = true;
	}
	
	public synchronized void closeConnection () {
		try {
			
			if(out != null && in != null) {
				out.close();
				in.close();
			}
			
			if(socket != null) {
				
				Playground play = (Playground)Main.sh.getSelectedScene();
				
				sendMessage(Package.pack("DISCONNECT",
						this.TYPEOFCONNECTION, play.plr.name));
				
				socket.close();
			}
			
			if(serverSocket != null) {
				serverSocket.close();
			} 
			isConnecting = false;
			System.out.println("Connection closed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void sendMessage(String line) {
		try {
			in.println(line);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private synchronized boolean[] connecting() {
		
		try {
			if(isConnecting) {
				System.out.println("Connceting...");
				if(createServer)  {
					serverSocket = new ServerSocket(0);
					serverSocket.setSoTimeout(0);
					
					this.ADRESS = InetAddress.getLocalHost().getHostAddress();
					this.PORT = serverSocket.getLocalPort();
					System.out.println(this.ADRESS+":"+this.PORT);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new boolean[] {isConnecting, createServer};
	}
	
	
	@Override
	public void run() {
		while (true) {
			boolean[] connectionResult = connecting();
			
			try {
				if(connectionResult[0]) {
					if(connectionResult[1]) {
						socket = serverSocket.accept();
					} else {
						socket = new Socket(this.ADRESS, this.PORT);
					}
					System.out.println("Connected!");
					
					synchronized (this) {
						isConnecting = false;
						
						out = new BufferedReader(new InputStreamReader(
								socket.getInputStream()));
						
						in = new PrintWriter(socket.getOutputStream(), true);
					}
				}
			} catch (Exception e) {
				System.out.println("Connection failed!");
				e.printStackTrace();
				
				synchronized (this) {
					isConnecting = false;
					createServer = false;
					
				}
			}
			
			try {
				if(out != null) {
					String line;
					while(out.ready() && !socket.isClosed()) {
						line = out.readLine();
						pack.interperate(line);
					}
				}
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
		}
	}
}