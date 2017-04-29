

import java.io.*;

public class TxtReader {
	private String _file;
	private FileReader _fileReader;
	private BufferedReader _bufferedReader;
	public TxtReader(){
		
	}
	
	public void openFile(String pFile){
		_file = pFile;
		 try {
			_fileReader = new FileReader(_file);
			_bufferedReader = new BufferedReader(_fileReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String readLine(int pLine){
		String line = null;
		int x = 0;
		while(x<pLine){
		try {
			 line = _bufferedReader.readLine();
			 System.out.println("leyendo linea "+x);
			 x++;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		return line;
	}
    public void closeFile(){
    	try {
			_bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public boolean isEmpty(String pFile){
    	openFile(pFile);
    	try {
			if(_bufferedReader.readLine()==""){
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }
    public int lineCounter(String pFile){
    	int cont = 0;
    	while(true){
    		String data = readLine(cont);
    		if(data!=""){
    		cont++;}
    		else{
    			return cont;
    		}
    	}
    }

    
}
