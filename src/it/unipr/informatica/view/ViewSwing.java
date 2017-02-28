package it.unipr.informatica.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.re.easymodbus.modbusclient.ModbusClient;
import it.unipr.informatica.controller.Modbus_Client;

public class ViewSwing implements View {
	
	public ViewSwing(Modbus_Client modbus_Client) {
		initComponent();
		modbusClient = new ModbusClient();
	}
	
	
	 
	/**
     * This method is called from within the constructor to initialize the form.
     */
	private void initComponent(){
		frame = new JFrame("ModbusClient");
		connectButton = new JButton();
		connectButton.setText("Connect");
		disconnectButton = new JButton();
		disconnectButton.setText("Disconnect");
		addressIpField = new JTextField();	
		portField = new JTextField();
		startAddressField = new JTextField();
		numberValuesField = new JTextField();
		readCoilsButton = new JButton();
		readCoilsButton.setText("Read Coils - FC1");
		readDiscreteInputsButton = new JButton();
		readDiscreteInputsButton.setText("Read Discrete Inputs - FC2");
		readHoldingRegistersButton = new JButton();
		readHoldingRegistersButton.setText("Read Holding Registers - FC3");
		readInputRegistersBUtton = new JButton();
		readInputRegistersBUtton.setText("Read Input Registers - FC4");
		
		// Setup frame size
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int widthFrame = (3 * screenSize.width) / 4;
		int heightFrame = (3 * screenSize.height) / 4;
        int x = (screenSize.width - widthFrame) / 2;
        int y = (screenSize.height - heightFrame) / 2;
        frame.setBounds(x, y, widthFrame, heightFrame);
        
        connectionJPanel = new JPanel();
        connectionJPanel.setLayout(new BorderLayout());
        connectionJPanel.add(connectButton,BorderLayout.NORTH);
        connectionJPanel.add(disconnectButton,BorderLayout.NORTH);
        
        
        frame.add(connectionJPanel,BorderLayout.WEST);
      
        
       
        
        
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void show() {
		frame.setVisible(true);
		
	}



	@Override
	public void error(String error) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void message(String message) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void sure() {
		// TODO Auto-generated method stub
		
	}










	// Variables declaration GUI
	
	private JFrame frame;
	private JButton connectButton;
	private JButton disconnectButton;
	private JButton readCoilsButton;
	private JButton readDiscreteInputsButton;
	private JButton readHoldingRegistersButton;
	private JButton readInputRegistersBUtton;
	private JTextField addressIpField;
	private JTextField portField;
	private JTextField startAddressField;
	private JTextField numberValuesField;
	private JPanel connectionJPanel;
	
	// Variables declaration 
	
	private  ModbusClient modbusClient;

}
