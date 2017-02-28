package it.unipr.informatica.model;

import java.awt.List;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;
import it.unipr.informatica.view.ViewBuilder;


public class Model {
	
	private ViewBuilder view;
	private ModbusClient client;
	
	private String ipAddress;
	private int port;
	
	

	
	public Model(ViewBuilder view) {
		
		this.view = view;
		client = new ModbusClient();
		
		
	}
	
	public void connetti(){
		if(!client.isConnected()){
		  try {
			  client.Connect(ipAddress, port);
			  
			
			  System.out.println("Connession effettuata");
		  } catch (Exception e) {
			  
			  JOptionPane.showMessageDialog(null, "Connection failed", "Connection failed", JOptionPane.OK_CANCEL_OPTION);
		  }
		}
		
	}
	
	public void disconnetti(){
		
		try {
			client.Disconnect();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setIpAddress(String address){
		this.ipAddress = address;
		System.out.println(address);
		
	}
	
	public void setPortNumber(String port){
		this.port = Integer.parseInt(port);
		System.out.println(port);
		
	}
	
	public void readCoil(int startingAddress){
	
		int coilAddress = startingAddress - 1;
		ArrayList<Boolean> responseList = new ArrayList<Boolean>();
		try{
		
			boolean[] serverResponse = client.ReadCoils(coilAddress, 1);
			   for (int i = 0; i < serverResponse.length; i++)
				   responseList.add(serverResponse[i]);
            //System.out.println(serverResponse[0]);
			   view.printResponseBool(responseList);
			
		
		}catch (Exception e) {
		  
			JOptionPane.showMessageDialog(null, "Server response error", "Connection failed", JOptionPane.OK_CANCEL_OPTION);
		}
		
		
	
	}
	
	public void readDiscreteInput(int startingAddress, int numberOfValue){
		int registerAddress = startingAddress -1;
    	ArrayList<Boolean> responseList = new ArrayList<Boolean>();
    	try {
    		boolean[] serverResponse = client.ReadDiscreteInputs(registerAddress, numberOfValue);
    		for (int i = 0; i < serverResponse.length; i++)
				   responseList.add(serverResponse[i]);
    		view.printResponseBool(responseList);
    
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Server response error", "Connection failed", JOptionPane.OK_CANCEL_OPTION);
		}
		
		
	}
	
    public void readMultipleRegisters(int startingAddress, int numberOfValues ){
    	
    	int registerAddress = startingAddress -1;
    	ArrayList<Integer> responseList = new ArrayList<Integer>();
    	try {
    		int[] serverResponse = client.ReadInputRegisters(startingAddress, numberOfValues);
    		for (int i = 0; i < serverResponse.length; i++)
				   responseList.add(serverResponse[i]);
    		view.printResponseInt(responseList);
    
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Server response error", "Connection failed", JOptionPane.OK_CANCEL_OPTION);
		}
    	
		
	}
    
    public void readInputRegisters(int startingAddress, int numberOfValues ){
    	int registerAddress = startingAddress -1;
    	ArrayList<Integer> responseList = new ArrayList<Integer>();
    	try {
    		int[] serverResponse = client.ReadInputRegisters(startingAddress, 1);
    		for (int i = 0; i < serverResponse.length; i++)
				   responseList.add(serverResponse[i]);
    		view.printResponseInt(responseList);
    
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Server response error", "Connection failed", JOptionPane.OK_CANCEL_OPTION);
		}
		
	}
    // Check
    public void writeSingleCoil(int startingAddress, boolean value){
    	
    	int coilAddress = startingAddress - 1;
    	
    	try {
			client.WriteSingleCoil(coilAddress, value );
			System.out.println(value);
		} catch (ModbusException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
	}
    
    public void writeSingleRegister(int startingAddress, int value){
    	try {
			client.WriteSingleRegister(startingAddress, value);
		} catch (ModbusException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
	}
    
    
    // Check
    public void writeMultipleCoils(int startingAddress, int numberOfValues, Boolean values ){
    	
    	int coilAddress = startingAddress - 1;
    	boolean[] clientRequest = new boolean[numberOfValues];
    	for (int i = 0; i< numberOfValues; i++)
    		clientRequest[i] = values;
    	
    	try {
			client.WriteMultipleCoils(startingAddress, clientRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
		
		
	}
    
    // Ready
    
    public void writeMultipleRegisters(int startingAddress, int numberOfValues, int values ){
    	int coilAddress = startingAddress -1;
    	int[] clientRequest = new int[numberOfValues];
    	for (int i = 0; i< numberOfValues; i++)
    		clientRequest[i] = values;
    	
    	try {
			client.WriteMultipleRegisters(startingAddress, clientRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
    
	
	
	
	

}
