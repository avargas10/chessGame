package TCPServer;

import java.io.IOException;

import org.json.simple.JSONObject;

import GameCommunication.IConstants;
import GameCommunication.JsonCreator;



public class Protocol {
	private static Protocol _instance;
	private Protocol(){

	}
	
	public static Protocol getInstance(){
		if(_instance==null){
			_instance = new Protocol();
		}
		return _instance;
	}
	
	public void handleFunction(String pJson){
		JSONObject json = JsonCreator.getInstance().getJsonStr(pJson);
		String function = JsonCreator.getInstance().getData(json,IConstants.K_function);
		System.out.println("Function: "+function);
		switch(function) {
		   case IConstants.F_Interpretar :
		      
		      break; 
		   case IConstants.F_ejecutar :
		      // Statements
		      break; // optional
		   case IConstants.F_puertos :
			      // Statements
			      break; // optional
		   case IConstants.F_Acceso :
			      Verificar_Acceso(json);
			      break; // optional
		   // You can have any number of case statements.
		   default : // Optional
		      // Statements
		}
	}
	
	private void Verificar_Acceso(JSONObject json) {
		// TODO Auto-generated method stub
		String state = JsonCreator.getInstance().getData(json,IConstants.K_Acceso);
		if(state.contains("Connected")){
			System.out.println(state);
			JSONObject object = new JSONObject();
			object.put(IConstants.K_function,IConstants.F_Acceso);
            object.put(IConstants.K_Acceso,"Connected");
			ServerWriter.getmyserverwriter().sendJson(object);
		}
	}

}
