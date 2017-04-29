package TCPServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;


public class Server implements Runnable {
	
	
	private ServerSocket sSocket;
	public Socket socket;
	private ClientThread client;
	private static Server server;
	private int numero = 0;
	private String ipdirection;
	public Thread thread;
	
	public ClientThread getClient() {
		return client;
	}

	public void setClient(ClientThread client) {
		this.client = client;
	}
	
	public static Server getServer(){
		
		if(server == null){
			server = new Server();
		}
		return server;
	}

	private Server(){
		
		try{
			sSocket = new ServerSocket(8080);
			thread = new Thread(this);
			thread.start();
			System.out.println("Server started at: "+ new Date());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		
		 try {
			 
			ipdirection = InetAddress.getLocalHost().getHostAddress();
			System.out.println("my ip : " + ipdirection);
			
		 }
		 catch (UnknownHostException e1) {
			 
			e1.printStackTrace();
			
		}
		
		while (true){
			try {
				socket = sSocket.accept();
				client = new ClientThread(socket);
				client.thread();
				//pruaba para ver si conecta mas de 1 ciente
				System.out.println("conectando cliente : "+numero);
				numero+=1;
				//__________fin prueba_________
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
