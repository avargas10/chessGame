

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class SerialPortManager extends Thread  implements SerialPortDataListener {
	private static SerialPortManager _instance;
	private BufferedReader _input;
	private SerialPort _port;
	private String[] _ports;
	private String _info;
	private SerialPortManager(){
	}
	
	public static SerialPortManager getInstance(){
		if(_instance ==null){
			_instance= new SerialPortManager();
		}
		return _instance;
	}
	
	public String[] getPorts(){
		SerialPort[] portNames = SerialPort.getCommPorts();
		_ports = new String[SerialPort.getCommPorts().length];
		for(int i = 0; i < portNames.length; i++){	
			String port = portNames[i].getSystemPortName();
			_ports[i]= port; 
		}
		return _ports;
	}
	
	public SerialPort getPort(){
		return _port;
	}
	
	private void setPort(String pPort){
		_port = SerialPort.getCommPort(pPort); 
		 _input = new BufferedReader(new InputStreamReader(_port.getInputStream()));
		 _port.addDataListener(this);
	}
	
	public boolean connectPort(String pPort){
		setPort(pPort);
		boolean data = false;
		_port.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		if(_port.openPort()) {
			System.out.println("Conectado Correctamente al puerto: "+_port.getSystemPortName());
			data = true;
		}
		else {
			// disconnect from the serial port
			System.out.println("No se pudo conectar correctamente al puerto: "+_port.getSystemPortName());
			data = false;
		}
		return data;
	}
	

	public boolean closePort(){
		boolean closed = false;
		if(_port!=null){_port.closePort();
		closed = true;
		System.out.println("Desconectado Correctamente del puerto: "+_port.getSystemPortName());}
		return closed;
	}

	public void setInformation(String pInfo){
		_info = pInfo;
	}
	public void run(){
		PrintWriter output = new PrintWriter(_port.getOutputStream());
		output.print(_info);
		output.flush();
		System.out.println("Escribiendo en el serial: "+_info);
	}

	@Override
	public int getListeningEvents() {
		// TODO Auto-generated method stub
		return 0;
	}
	public synchronized int read(){

	    int b = 0;  

	    try{
	        if (_input.ready()) {
	            b = (int)_input.read();
	        }
	    }catch (Exception e) {
	        System.err.println(e.toString());
	    }
	    return b;
	}
	@Override
	public synchronized void serialEvent(SerialPortEvent oEvent) {
	    if (oEvent.getEventType() == _port.LISTENING_EVENT_DATA_AVAILABLE) {
	        try {
	            String inputLine=null;
	            if (_input.ready()) {
	                inputLine = _input.readLine();
	                            System.out.println(inputLine);
	            }

	        } catch (Exception e) {
	            System.err.println(e.toString());
	        }
	    }
	}
}