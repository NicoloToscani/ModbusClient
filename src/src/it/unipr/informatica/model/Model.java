package it.unipr.informatica.model;

import java.awt.List;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;
import de.re.easymodbus.modbusclient.ModbusClient.RegisterOrder;
import it.unipr.informatica.view.ViewBuilder;


public class Model {
	
	private ViewBuilder view;
	public ModbusClient client;
	
	private String ipAddress;
	private int port;
	private byte unitId;
	
	

	/*
	 * 
	 * Constructor
	 * 
	 */
	
	public Model(ViewBuilder view) {
		
		this.view = view;
		client = new ModbusClient();
		client.addReveiveDataChangedListener(view);
		client.addSendDataChangedListener(view);
		
		
	}
	
	/*
	 * 
	 * Connect to server
	 * 
	 */
	
	public void connect(){
		if(!client.isConnected()){
		  try {
			  client.setUnitIdentifier(unitId);
			  client.Connect(ipAddress, port);
			  client.setUnitIdentifier(unitId);
			  JOptionPane.showMessageDialog(null, "Connected to server " + "\n" + "IP: " 
			  + this.ipAddress + "\n" + "Port: " + this.port + 
			  "\n" + "Unit: " + this.unitId , "Connection status", JOptionPane.OK_CANCEL_OPTION);
			  
			 } catch (Exception e) {
			  
			  JOptionPane.showMessageDialog(null, e.getMessage(), "Connection status", JOptionPane.OK_CANCEL_OPTION);
			 
		  }
		}
		
	}
	
	/*
	 * 
	 * Disconnect to server
	 * 
	 */
	
	public void disconnect(){
		
		try {
			client.Disconnect();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception from server", JOptionPane.OK_CANCEL_OPTION);
		}
		
	}
	
	/*
	 * 
	 * Set IP Address
	 * 
	 */
	
	public void setIpAddress(String address){
		this.ipAddress = address;
		
		
	}
	
	
	/*
	 * 
	 * Get IP Address
	 * 
	 */
	
	public String getIpAddress(){
		return this.ipAddress;
	}
	
	/*
	 * 
	 * Get Unit Identifier
	 * 
	 */
	
	public byte getUnitIdentifier(){
		return client.getUnitIdentifier();
	}
	
	/*
	 * 
	 * Set Unit Identifier
	 * 
	 */
	
	public void setUnitIdentifier(String unitId){
		this.unitId = Byte.parseByte(unitId);
		
	}
	
	/*
	 * 
	 * Set Port Number
	 * 
	 */
	
	public void setPortNumber(String port){
		this.port = Integer.parseInt(port);
		
		
	}
	
	
	/*
	 * 
	 * Get Port Number
	 * 
	 */
	
	public int getPortNumber(){
		return this.port;
	}
	
	
	/*
	 * Read Coils from Server
	 * 
	 * Parameters:
     * startingAddress - First Address to read; Shifted by -1
     * quantity - Number of Inputs to read
     *  
	 */
	
	public void readCoils(int startingAddress, int quantity){
	
		ArrayList<Boolean> responseList = new ArrayList<Boolean>();
		try{
		
			boolean[] serverResponse = client.ReadCoils(startingAddress - 1, quantity);
			
			   for (int i = 0; i < serverResponse.length; i++)
				   responseList.add(serverResponse[i]);
            view.printResponseBool(responseList);
            
         
            
			
		
		}catch (Exception e) {
		  
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception from server", JOptionPane.OK_CANCEL_OPTION);
		}
		
	}
	
	/*
	 * Read Discrete Inputs from Server
	 * 
	 * Parameters:
     * startingAddress - First Address to read; Shifted by -1
     * quantity - Number of Inputs to read
     *  
	 */
	
	public void readDiscreteInputs(int startingAddress, int quantity){
		
    	ArrayList<Boolean> responseList = new ArrayList<Boolean>();
    	try {
    		boolean[] serverResponse = client.ReadDiscreteInputs(startingAddress - 1, quantity);
    		for (int i = 0; i < serverResponse.length; i++)
				   responseList.add(serverResponse[i]);
    		view.printResponseBool(responseList);
    
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception from server", JOptionPane.OK_CANCEL_OPTION);
		}
		
	}
	
	
	/*
	 * Read Holding Registers from Server
	 * 
	 * Parameters:
     * startingAddress - First Address to read; Shifted by -1
     * quantity - Number of Registers to read
     *  
	 */
	
	
    public void readHoldingRegisters(int startingAddress, int quantity ){
    	
    	ArrayList<Integer> responseList = new ArrayList<Integer>();
    	try {
    		int[] serverResponse = client.ReadHoldingRegisters(startingAddress - 1, quantity);
    		for (int i = 0; i < serverResponse.length; i++)
				   responseList.add(serverResponse[i]);
    		view.printResponseInt(responseList);
    
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception from server", JOptionPane.OK_CANCEL_OPTION);
		}
    	
	}
    
    public void readInputRegisters(int startingAddress, int quantity){
    	
    	ArrayList<Integer> responseList = new ArrayList<Integer>();
    	try {
    		
    		int[] serverResponse = client.ReadInputRegisters(startingAddress - 1, quantity);
    		for (int i = 0; i < serverResponse.length; i++)
				   responseList.add(serverResponse[i]);
    		view.printResponseInt(responseList);
    
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception from server", JOptionPane.OK_CANCEL_OPTION);
		}
		
	}
    
    /*
	 * Write Single Coil to Server
	 * 
	 * Parameters:
     * startingAddress - First Address to write; Shifted by -1
     * values - value to write to coil
     *  
	 */
    
    public void writeSingleCoil(int startingAddress, boolean value){
    	
    	ArrayList<Boolean> requiredList = new ArrayList<Boolean>();
    	try {
    		
			client.WriteSingleCoil(startingAddress - 1, value );
			requiredList.add(value);
			view.printRequiredBool(requiredList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception from server", JOptionPane.OK_CANCEL_OPTION);
		}
    	
		
	}
    
    /*
	 * Write Single Registers to Server
	 * 
	 * Parameters:
     * startingAddress - First Address to write; Shifted by -1
     * value - value to write to registers
     *  
	 */
    
    public void writeSingleRegister(int startingAddress, int value){
    	ArrayList<Integer> requiredList = new ArrayList<Integer>();
    	try {
			client.WriteSingleRegister(startingAddress - 1, value);
			requiredList.add(value);
			view.printRequiredInt(requiredList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception from server", JOptionPane.OK_CANCEL_OPTION);
		}
    	
		
	}
    
    
    /*
	 * Write Multiple Coils to Server
	 * 
	 * Parameters:
     * startingAddress - First Address to write; Shifted by -1
     * quantity - Number of registers to write
     * value - value to write to registers (true or false)
     *  
	 */
    
    
    public void writeMultipleCoils(int startingAddress, int quantity, Boolean value ){
    	ArrayList<Boolean> requiredList = new ArrayList<Boolean>();
    	boolean[] clientRequest = new boolean[quantity];
    	for (int i = 0; i< quantity; i++){
    		clientRequest[i] = value;
    		requiredList.add(clientRequest[i]);
    	}
    	try {
			client.WriteMultipleCoils(startingAddress - 1, clientRequest);
			view.printRequiredBool(requiredList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception from server", JOptionPane.OK_CANCEL_OPTION);
		}
    	
    	
		
		
	}
    
    
    /*
	 * Write Multiple Registers to Server
	 * 
	 * Parameters:
     * startingAddress - First Address to write; Shifted by -1
     * quantity - Number of registers to write
     * values - value to write to registers
     *  
	 */
    
    
    public void writeMultipleRegisters(int startingAddress, int quantity, int values ){
    	
    	ArrayList<Integer> requiredList = new ArrayList<Integer>();
    	int[] clientRequest = new int[quantity];
    	for (int i = 0; i< quantity; i++){
    		clientRequest[i] = values;
    		requiredList.add(clientRequest[i]);
    	
    	}
    	try {
			client.WriteMultipleRegisters(startingAddress - 1, clientRequest);
			view.printRequiredInt(requiredList);
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception from server", JOptionPane.OK_CANCEL_OPTION);
		}
		
	}
    
    /*
     * Write float number in two registers
     * Parameters:
     * startingAddress - First Address to write; Shifted by -1
     * value - value to write to registers (example: 12.89)
     */
    
    public void writeFloat(int startingAddress, float value){
    	try {
			client.WriteMultipleRegisters(startingAddress -1 ,ModbusClient.ConvertFloatToTwoRegisters((float)value ));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception from server", JOptionPane.OK_CANCEL_OPTION);
		}
    }
    
    /*
     * Read two registers in float number
     * Parameters:
     * startingAddress - First Address to read; Shifted by -1
     * 
     */
    
    public void readFloat(int startingAddress){
    	
		float read;
    	try {
			read = ModbusClient.ConvertRegistersToFloat(client.ReadHoldingRegisters(startingAddress -1 , 2));
			view.printFloatValue(read);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception from server", JOptionPane.OK_CANCEL_OPTION);
		}
    }
    
    
}
