package GameCommunication;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonCreator {

	private static JsonCreator _instance;
	private JsonCreator(){}
	public static JsonCreator getInstance(){
		if(_instance==null){
			_instance = new JsonCreator();
			return _instance;
		}
		else{
			return _instance;
		}
	}
	
	public  JSONObject createJson(){
	JSONObject json = new JSONObject();
	return json;
	}
	
	
	public  String getStrJson(JSONObject pJson){
		String strJson = pJson.toString();
		return strJson;
	}
	
	public  JSONObject getJsonStr(String strJson) {
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(strJson);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public  <T>  JSONObject addData(JSONObject pJson,String pKey, T pValue){
		pJson.put(pKey, pValue);
		return pJson;
	}
	
	public  <T> T getData(JSONObject pJson, String pKey){
		return (T) pJson.get(pKey);
	}
	
	
}
