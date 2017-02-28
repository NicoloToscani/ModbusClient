package it.unipr.informatica.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

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
import javax.swing.JTextArea;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import javax.swing.JRadioButton;

public class ViewBuilder extends JFrame {
	
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
	private JTextArea textArea;
	private JLabel lblValue;
	private JLabel lblServerResponse;
	private JTextField startAddressTextField;
	private JTextField valueTextField;
	private JTextField numberValueTextField;
	
	




	/**
	 * Create the frame.
	 */
	public ViewBuilder(Modbus_Client client) {
		
		model = new Model(this);
		this.setTitle("ModbusClient");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 786, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		
		
		JLabel lblIpAddress = new JLabel("IP Address: ");
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
		
		JLabel lblPortNumber = new JLabel("Port number:");
		lblPortNumber.setBounds(212, 13, 76, 16);
		contentPane.add(lblPortNumber);
		
		portTextField = new JTextField();
		portTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				model.setPortNumber(portTextField.getText());

			}
		});
		portTextField.setBounds(294, 10, 58, 22);
		contentPane.add(portTextField);
		portTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Read Coil - FC1");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.readCoil(Integer.valueOf(startAddressTextField.getText()));
			}
		});
		btnNewButton.setBounds(12, 85, 188, 36);
		contentPane.add(btnNewButton);
		
		button = new JButton("Read Discrete Input - FC2");
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.readDiscreteInput(Integer.valueOf(startAddressTextField.getText()),Integer.valueOf(numberValueTextField.getText()));
			}
		});
		button.setBounds(12, 134, 188, 36);
		contentPane.add(button);
		
		button_1 = new JButton("Read Multiple Registers - FC3");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.readMultipleRegisters(Integer.valueOf(startAddressTextField.getText()), Integer.valueOf(numberValueTextField.getText()));
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_1.setBounds(12, 183, 188, 36);
		contentPane.add(button_1);
		
		button_2 = new JButton("Read Input Registers - FC4");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.readInputRegisters(Integer.valueOf(startAddressTextField.getText()), 1);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_2.setBounds(12, 232, 188, 36);
		contentPane.add(button_2);
		
		button_3 = new JButton("Write Single Coil - FC5");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.writeSingleCoil(Integer.valueOf(startAddressTextField.getText()),Boolean.valueOf(valueTextField.getText()) );
				
			}
		});
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_3.setBounds(12, 374, 188, 36);
		contentPane.add(button_3);
		
		button_4 = new JButton("Write Single Register - FC6");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.writeSingleRegister(Integer.valueOf(startAddressTextField.getText()), Integer.valueOf(valueTextField.getText()));
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_4.setBounds(12, 423, 188, 36);
		contentPane.add(button_4);
		
		button_5 = new JButton("Write Multiple Coils - FC15");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.writeMultipleCoils(Integer.valueOf(startAddressTextField.getText()), Integer.valueOf(numberValueTextField.getText()), Boolean.valueOf(valueTextField.getText()));
			}
		});
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_5.setBounds(12, 276, 188, 36);
		contentPane.add(button_5);
		
		button_6 = new JButton("Write Multiple Registers - FC16");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			model.writeMultipleRegisters(Integer.valueOf(startAddressTextField.getText()), Integer.valueOf(numberValueTextField.getText()), Integer.valueOf(valueTextField.getText()));
			}
		});
		button_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_6.setBounds(12, 325, 188, 36);
		contentPane.add(button_6);
		
		btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.connetti();
				
			}
		});
		btnConnect.setBounds(364, 9, 116, 25);
		contentPane.add(btnConnect);
		
		button_7 = new JButton("Disconnect");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.disconnetti();
				
			}
		});
		button_7.setBounds(489, 9, 116, 25);
		contentPane.add(button_7);
		
		lblStartingAddress = new JLabel("Starting Address:");
		lblStartingAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartingAddress.setBounds(442, 94, 116, 16);
		contentPane.add(lblStartingAddress);
		
		lblNumberOfValues = new JLabel("Number of Values:");
		lblNumberOfValues.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfValues.setBounds(442, 126, 116, 16);
		contentPane.add(lblNumberOfValues);
		
		
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(229, 88, 177, 371);
		contentPane.add(textArea);
		
		lblValue = new JLabel("Value:");
		lblValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblValue.setBounds(504, 155, 64, 16);
		contentPane.add(lblValue);
		
		lblServerResponse = new JLabel("Reading Registers");
		lblServerResponse.setHorizontalAlignment(SwingConstants.CENTER);
		lblServerResponse.setBounds(254, 59, 116, 16);
		contentPane.add(lblServerResponse);
		
		startAddressTextField = new JTextField();
		startAddressTextField.setBounds(563, 91, 116, 22);
		contentPane.add(startAddressTextField);
		startAddressTextField.setColumns(10);
		
		valueTextField = new JTextField();
		valueTextField.setBounds(563, 155, 116, 22);
		contentPane.add(valueTextField);
		valueTextField.setColumns(10);
		
		numberValueTextField = new JTextField();
		numberValueTextField.setBounds(563, 123, 116, 22);
		contentPane.add(numberValueTextField);
		numberValueTextField.setColumns(10);
		
	}
	
	public void printResponseBool(ArrayList<Boolean> l){
		textArea.setText(null);
		for(int i = 0; i< l.size(); i++){
			textArea.append(Boolean.toString(l.get(i)));
			textArea.append("\n");
		}
			
	}
	
	// Mettere stampa per numeri interi
	
	public void printResponseInt(ArrayList<Integer> l){
		textArea.setText(null);
		for(int i = 0; i < l.size(); i++){
			textArea.append(Integer.toString(l.get(i)));
			textArea.append("\n");
		}
	}
}
