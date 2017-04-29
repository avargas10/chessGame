package TCPServer;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class ServerReader implements Runnable {
	
	private ClientThread cliente;
	public Thread thread;
	private static ServerReader reader;
	private ServerWriter writer = ServerWriter.getmyserverwriter();
	private JSONObject current = new JSONObject();
	
	public static ServerReader setserverreader(ClientThread client){
		if (reader == null){
			reader = new ServerReader(client);
		}
		return reader;
	}

	public void Thread(){
		 thread = new Thread(this);
		thread.start();
	}
	
	public ServerReader(ClientThread client){
		cliente = client;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(true){
			try {
				System.out.println("esperando nuevo lectura del buffer...");
				cliente.entrada = cliente.input.readLine();
				System.out.println(cliente.entrada);
				if (cliente.entrada != null){
					if(cliente.entrada.contains("{")){
						Protocol.getInstance().handleFunction(cliente.entrada);	
					}
				}

				
				if (cliente.entrada == null){
					thread.stop();
				}	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
