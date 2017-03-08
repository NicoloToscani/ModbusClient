package it.unipr.informatica.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import de.re.easymodbus.modbusclient.ModbusClient.RegisterOrder;
import de.re.easymodbus.modbusclient.ReceiveDataChangedListener;
import de.re.easymodbus.modbusclient.SendDataChangedListener;
import it.unipr.informatica.controller.Modbus_Client;
import it.unipr.informatica.model.Model;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JTextArea;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.JRadioButton;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import java.awt.Toolkit;
import javax.swing.JSeparator;

public class ViewBuilder extends JFrame implements SendDataChangedListener, ReceiveDataChangedListener {
	
	private Model model;

	private JPanel contentPane;
	private JTextField addressTextField;
	private JTextField portTextField;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;
	private JButton btnConnect;
	private JButton button_7;
	private JLabel lblStartingAddress;
	private JLabel lblNumberOfValues;
	public JList<String> responseList;
	private JTextArea textAreaWrite;
	private JLabel lblServerResponse;
	private JTextField startAddressTextFieldR;
	private JTextField numberValueTextFieldR;
	private JTextField unitTextField;
	private JTextField startAddressTextFieldW;
	private JTextField numberValueTextFieldW;
	private JTextField valueTextFieldW;
	private JLabel lblWritingOnServer;
	private JTextArea textAreaRead;
	public JTextArea textAreaPacketRead;
	private JTextField startAddressFloatW;
	private JTextField valueFloatW;
	private JTextField startAddressFloatR;
	private JTextField valueFloatR;
	private JLabel label_3;
	public Timer timer;
	private JLabel statusConnectionlabel;

	/**
	 * Create the frame.
	 */
	public ViewBuilder(Modbus_Client client) {
		setResizable(false);
		displayStatusConnection();
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\nToscani\\Desktop\\175x175bb.png"));
		
		model = new Model(this);
		this.setTitle("ModbusClient");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 729);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		JLabel lblIpAddress = new JLabel("IP Address : ");
		lblIpAddress.setBounds(12, 13, 76, 16);
		contentPane.add(lblIpAddress);
		
		addressTextField = new JTextField();
		addressTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				
				model.setIpAddress(addressTextField.getText());
				
				
				
			}
		});
		addressTextField.setBounds(84, 10, 116, 22);
		contentPane.add(addressTextField);
		addressTextField.setColumns(10);
		
		JLabel lblPortNumber = new JLabel("Port :");
		lblPortNumber.setBounds(212, 13, 76, 16);
		contentPane.add(lblPortNumber);
		
		portTextField = new JTextField();
		portTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				model.setPortNumber(portTextField.getText());

			}
		});
		portTextField.setBounds(248, 10, 58, 22);
		contentPane.add(portTextField);
		portTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Read Coils - FC1");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.readCoils(Integer.valueOf(startAddressTextFieldR.getText()),Integer.valueOf(numberValueTextFieldR.getText()) );
			}
		});
		btnNewButton.setBounds(12, 85, 188, 36);
		contentPane.add(btnNewButton);
		
		button = new JButton("Read Discrete Input - FC2");
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.readDiscreteInputs(Integer.valueOf(startAddressTextFieldR.getText()),Integer.valueOf(numberValueTextFieldR.getText()));
			}
		});
		button.setBounds(12, 134, 188, 36);
		contentPane.add(button);
		
		button_1 = new JButton("Read Holding Registers - FC3");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.readHoldingRegisters(Integer.valueOf(startAddressTextFieldR.getText()), Integer.valueOf(numberValueTextFieldR.getText()));
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_1.setBounds(12, 183, 188, 36);
		contentPane.add(button_1);
		
		button_2 = new JButton("Read Input Registers - FC4");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.readInputRegisters(Integer.valueOf(startAddressTextFieldR.getText()), Integer.valueOf(numberValueTextFieldR.getText()));
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_2.setBounds(12, 232, 188, 36);
		contentPane.add(button_2);
		
		button_3 = new JButton("Write Single Coil - FC5");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.writeSingleCoil(Integer.valueOf(startAddressTextFieldW.getText()),Boolean.valueOf(valueTextFieldW.getText()) );
				
			}
		});
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_3.setBounds(12, 310, 188, 36);
		contentPane.add(button_3);
		
		button_4 = new JButton("Write Single Register - FC6");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.writeSingleRegister(Integer.valueOf(startAddressTextFieldW.getText()), Integer.valueOf(valueTextFieldW.getText()));
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_4.setBounds(12, 359, 188, 36);
		contentPane.add(button_4);
		
		button_5 = new JButton("Write Multiple Coils - FC15");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.writeMultipleCoils(Integer.valueOf(startAddressTextFieldW.getText()), Integer.valueOf(numberValueTextFieldW.getText()), Boolean.valueOf(valueTextFieldW.getText()));
			}
		});
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_5.setBounds(12, 408, 188, 36);
		contentPane.add(button_5);
		
		button_6 = new JButton("Write Multiple Registers - FC16");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				model.writeMultipleRegisters(Integer.valueOf(startAddressTextFieldW.getText()), Integer.valueOf(numberValueTextFieldW.getText()), Integer.valueOf(valueTextFieldW.getText()));
			}
		});
		
		button_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_6.setBounds(12, 457, 188, 36);
		contentPane.add(button_6);
		
		btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.connect();
				
			}
		});
		btnConnect.setBounds(442, 9, 116, 25);
		contentPane.add(btnConnect);
		
		button_7 = new JButton("Disconnect");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.disconnect();
				
			}
		});
		button_7.setBounds(563, 9, 116, 25);
		contentPane.add(button_7);
		
		lblStartingAddress = new JLabel("Starting Address:");
		lblStartingAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartingAddress.setBounds(442, 94, 116, 16);
		contentPane.add(lblStartingAddress);
		
		lblNumberOfValues = new JLabel("Quantity :");
		lblNumberOfValues.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfValues.setBounds(477, 123, 91, 16);
		contentPane.add(lblNumberOfValues);
		
		
		
		textAreaWrite = new JTextArea();
		textAreaWrite.setEditable(false);
		textAreaWrite.setBounds(229, 310, 177, 181);
		contentPane.add(textAreaWrite);
		
		lblServerResponse = new JLabel("Reading from Server");
		lblServerResponse.setHorizontalAlignment(SwingConstants.CENTER);
		lblServerResponse.setBounds(229, 59, 177, 16);
		contentPane.add(lblServerResponse);
		
		startAddressTextFieldR = new JTextField();
		startAddressTextFieldR.setBounds(563, 91, 116, 22);
		contentPane.add(startAddressTextFieldR);
		startAddressTextFieldR.setColumns(10);
		
		numberValueTextFieldR = new JTextField();
		numberValueTextFieldR.setBounds(563, 123, 116, 22);
		contentPane.add(numberValueTextFieldR);
		numberValueTextFieldR.setColumns(10);
		
		unitTextField = new JTextField();
		unitTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				model.setUnitIdentifier(unitTextField.getText());
			}
		});
		unitTextField.setBounds(353, 10, 58, 22);
		contentPane.add(unitTextField);
		unitTextField.setColumns(10);
		
		JLabel lblUnit = new JLabel("Unit :");
		lblUnit.setBounds(318, 13, 38, 16);
		contentPane.add(lblUnit);
		
		startAddressTextFieldW = new JTextField();
		startAddressTextFieldW.setColumns(10);
		startAddressTextFieldW.setBounds(563, 309, 116, 22);
		contentPane.add(startAddressTextFieldW);
		
		JLabel label = new JLabel("Starting Address:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(442, 312, 116, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Quantity :");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(477, 341, 91, 16);
		contentPane.add(label_1);
		
		numberValueTextFieldW = new JTextField();
		numberValueTextFieldW.setColumns(10);
		numberValueTextFieldW.setBounds(563, 341, 116, 22);
		contentPane.add(numberValueTextFieldW);
		
		JLabel label_2 = new JLabel("Value:");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(504, 373, 64, 16);
		contentPane.add(label_2);
		
		valueTextFieldW = new JTextField();
		valueTextFieldW.setColumns(10);
		valueTextFieldW.setBounds(563, 373, 116, 22);
		contentPane.add(valueTextFieldW);
		
		lblWritingOnServer = new JLabel("Writing on Server");
		lblWritingOnServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblWritingOnServer.setBounds(229, 281, 177, 16);
		contentPane.add(lblWritingOnServer);
		
		textAreaRead = new JTextArea();
		textAreaRead.setBounds(229, 88, 177, 180);
		contentPane.add(textAreaRead);
		
		textAreaPacketRead = new JTextArea();
		textAreaPacketRead.setBounds(12, 552, 394, 106);
		contentPane.add(textAreaPacketRead);
		
		JLabel lblPacketExchanged = new JLabel("Packets Exchanged");
		lblPacketExchanged.setHorizontalAlignment(SwingConstants.CENTER);
		lblPacketExchanged.setBounds(95, 523, 177, 16);
		contentPane.add(lblPacketExchanged);
		
		JButton writeFloatButton = new JButton("Write Float ");
		writeFloatButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		writeFloatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				  model.writeFloat(Integer.valueOf(startAddressFloatW.getText()),Float.parseFloat(valueFloatW.getText()));
			}
		});
		writeFloatButton.setBounds(563, 506, 116, 36);
		contentPane.add(writeFloatButton);
		
		startAddressFloatW = new JTextField();
		startAddressFloatW.setBounds(563, 440, 116, 22);
		contentPane.add(startAddressFloatW);
		startAddressFloatW.setColumns(10);
		
		valueFloatW = new JTextField();
		valueFloatW.setBounds(563, 471, 116, 22);
		contentPane.add(valueFloatW);
		valueFloatW.setColumns(10);
		
		JLabel lblStartingAddress_1 = new JLabel("Starting Address: ");
		lblStartingAddress_1.setBounds(452, 443, 116, 16);
		contentPane.add(lblStartingAddress_1);
		
		JLabel lblValue = new JLabel("Value: ");
		lblValue.setBounds(517, 477, 48, 16);
		contentPane.add(lblValue);
		
		startAddressFloatR = new JTextField();
		startAddressFloatR.setBounds(563, 552, 116, 22);
		contentPane.add(startAddressFloatR);
		startAddressFloatR.setColumns(10);
		
		valueFloatR = new JTextField();
		valueFloatR.setBounds(563, 587, 116, 22);
		contentPane.add(valueFloatR);
		valueFloatR.setColumns(10);
		
		JButton readFloatButton = new JButton("Read Float ");
		readFloatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.readFloat(Integer.valueOf(startAddressFloatR.getText()));
			}
		});
		readFloatButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		readFloatButton.setBounds(563, 622, 116, 36);
		contentPane.add(readFloatButton);
		
		JLabel lblReadFloat = new JLabel("Read Float: ");
		lblReadFloat.setBounds(493, 589, 76, 16);
		contentPane.add(lblReadFloat);
		
		label_3 = new JLabel("Starting Address: ");
		label_3.setBounds(460, 555, 116, 16);
		contentPane.add(label_3);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 671, 773, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 47, 773, 2);
		contentPane.add(separator_1);
		
		JLabel lblConnectionStatus = new JLabel("Connection status: ");
		lblConnectionStatus.setHorizontalAlignment(SwingConstants.LEFT);
		lblConnectionStatus.setBounds(3, 671, 110, 28);
		contentPane.add(lblConnectionStatus);
		
		statusConnectionlabel = new JLabel("");
		statusConnectionlabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusConnectionlabel.setBounds(114, 671, 110, 28);
		contentPane.add(statusConnectionlabel);
		
	}
	

	// Print Response Boolean
	
	public void printResponseBool(ArrayList<Boolean> l){
		textAreaRead.setText(null);
		for(int i = 0; i< l.size(); i++){
			textAreaRead.append(Boolean.toString(l.get(i)));
			textAreaRead.append("\n");
		}
			
	}
	
	// Print Response Integer
	
	public void printResponseInt(ArrayList<Integer> l){
		textAreaRead.setText(null);
		for(int i = 0; i < l.size(); i++){
			textAreaRead.append(Integer.toString(l.get(i)));
			textAreaRead.append("\n");
		}
	}
	
	// Print Request Boolean
	
	public void printRequiredBool(ArrayList<Boolean> l){
		textAreaWrite.setText(null);
		for(int i = 0; i < l.size(); i++){
			textAreaWrite.append(Boolean.toString(l.get(i)));
			textAreaWrite.append("\n");
		}
		
	}
	
	// Print Request Integer
	
    public void printRequiredInt(ArrayList<Integer> l){
    	textAreaWrite.setText(null);
		for(int i = 0; i < l.size(); i++){
			textAreaWrite.append(Integer.toString(l.get(i)));
			textAreaWrite.append("\n");
		}
    }
    
    // Print value float write on the server
    
    public void printFloatValue(float f){
    	valueFloatR.setText(null);
    	valueFloatR.setText(Float.toString(f));
    }


	@Override
	public void ReceiveDataChanged() {
		
		textAreaPacketRead.append("Rx:");
        for (int i = 0; i < model.client.receiveData.length; i++)
        {
        	textAreaPacketRead.append(" ");
            if (model.client.receiveData[i] < 16) 
            	textAreaPacketRead.append("0");
            textAreaPacketRead.append(Integer.toHexString(model.client.receiveData[i]));
            
        }
        
        textAreaPacketRead.append("\n");
	}


	@Override
	public void SendDataChanged() {
		textAreaPacketRead.setText(null);
		textAreaPacketRead.append("Tx:");
        for (int i = 0; i < model.client.sendData.length; i++)
        {
        	textAreaPacketRead.append(" ");
            if (model.client.sendData[i] < 16) 
            	textAreaPacketRead.append("0");
            textAreaPacketRead.append(Integer.toHexString(model.client.sendData[i]));
        }
        textAreaPacketRead.append("\n" );
		
	}
	
	public void displayStatusConnection(){
		
		ActionListener updateStatusConnection = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(model.client.isConnected() == true){
				statusConnectionlabel.setText("connect");
				}
				else statusConnectionlabel.setText("disconnect");
			}
		};
		
		Timer timer = new Timer(1000, updateStatusConnection);
		timer.start();
		
	}
  }
