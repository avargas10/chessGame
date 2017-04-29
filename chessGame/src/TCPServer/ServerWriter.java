package TCPServer;

import java.net.Socket;
import java.util.ArrayList;

import org.json.simple.JSONObject;


public class ServerWriter{
	
	private ClientThread cliente;
	private static ServerWriter writer;
	private ArrayList<Socket> clientes = ClientThread.getclientes();
	
	
	public static ServerWriter setserverwriter(ClientThread client){
		if(writer == null){
			writer = new ServerWriter(client);
		}
		return writer;
	}
	
	public ServerWriter(ClientThread client){
		
		cliente = client;
		
	}
	
	public void send(String mensage){
		
		
		cliente.streamOut.println(mensage);
		cliente.streamOut.flush();
		System.out.println("Enviando: "+mensage);
		
	}
	
    public void sendJson(JSONObject object){
    	cliente.streamOut.println(object);
    	cliente.streamOut.flush();
    }
	
	public static ServerWriter getmyserverwriter(){
		return writer;
	}
	
	public void SendtoAll(String mensaje){
		for(int i = 0; i<clientes.size();i++){
			cliente.streamOut.println(mensaje);
			cliente.streamOut.flush();
		}
		
	}
	

	public void JSONtoAll(Object mensage){
		for(int i = 0; i<clientes.size();i++){
			cliente.streamOut.println(mensage);
			cliente.streamOut.flush();
		}
		
	}
}