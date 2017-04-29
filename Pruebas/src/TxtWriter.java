

import java.io.*;

public class TxtWriter {
	private String _file;
	private FileWriter _writer;
	private BufferedWriter _buffer;
	public TxtWriter(String pFile){
		_file = pFile;
		openFile();
		
}
	
	private void openFile(){
		try {
			_writer = new FileWriter(_file);
			_buffer = new BufferedWriter(_writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void closeFile(){
		try {
			_buffer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	public void writeInFile(String pData){
		try {
			_buffer.write(pData);
			_buffer.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}