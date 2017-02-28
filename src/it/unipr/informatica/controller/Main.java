package it.unipr.informatica.controller;
import javax.swing.JFrame;

import de.re.easymodbus.modbusclient.gui.EasyModbusTCPClientExampleGUI;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Modbus_Client client = new Modbus_Client();
		client.run();
		
	}

}
