package TCPServer;

import java.awt.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class ClientThread implements Runnable {
	
	public String entrada;
	public BufferedReader input;
	public PrintWriter output;
	Socket threadSocket;
	private boolean running = true; 
	public PrintStream streamOut;
	private Thread thread;
	public ServerReader reader;
	private static ArrayList<Socket> clientes = new ArrayList<Socket>();
	

	public static ArrayList getclientes(){
		return clientes;
	}
	public void thread(){
		 thread = new Thread(this);
		thread.start();
	}
		
	public ClientThread(Socket socket){
			this.threadSocket = socket;
			clientes.add(socket);
	}

	@Override
	public void run() {
		try{
			output = new PrintWriter(threadSocket.getOutputStream(),true);
			input = new BufferedReader(new InputStreamReader(threadSocket.getInputStream()));
			streamOut = new PrintStream(threadSocket.getOutputStream(), true);
			ServerWriter writer = ServerWriter.setserverwriter(this);
			reader = new ServerReader(this);
			reader.Thread();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
}