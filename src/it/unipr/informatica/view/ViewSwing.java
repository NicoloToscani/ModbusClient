package it.unipr.informatica.view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import it.unipr.informatica.controller.Modbus_Client;
import it.unipr.informatica.model.Model;

public class ViewSwing implements View {
	
    private Model modello;
	
	private Modbus_Client client;
	
	private static final String titolo = "Modbus TCP/IP Client";
	
	JFrame finestra;
	
	JPanel pannelloSinistra;
	
	JPanel pannelloCentro;
	
	JPanel pannelloDestra;
	
	JPanel pannelloAlto;
	
	JPanel pannelloBasso;
	
	Container frameContentPane;
	
	JButton connetti;
	
	JButton disconnetti;
	
	JTextField indirizzoServer;
	
	JLabel barraStato;

	/*
	 * View constructor 
	*/
	
	public ViewSwing(Modbus_Client client) {
		
		this.client = client;
		
		// Windows
		
		finestra = new JFrame(titolo);
		
		finestra.setVisible(true);
		
		finestra.setSize(1000, 1000);
		
		// JPanel 
		
		pannelloSinistra = new JPanel();
		
		pannelloDestra = new JPanel();
		
		pannelloCentro = new JPanel();
		
		pannelloAlto = new JPanel();
		
		pannelloBasso = new JPanel();
		
		
		
		// Frame Content Pane
		
		finestra.getContentPane();
		
		finestra.setLayout(new BorderLayout());
		
		finestra.add(pannelloAlto,BorderLayout.NORTH);
		
		finestra.add(pannelloBasso, BorderLayout.SOUTH);
		
		// Connection buttons
		
		connetti = new JButton("Connetti");
		
		pannelloAlto.add(connetti);
		
		disconnetti = new JButton("Disconnetti");
		
		pannelloAlto.add(disconnetti);
		
		indirizzoServer = new JTextField("es: 192.168.1.1");
		
		pannelloAlto.add(indirizzoServer);
		
		indirizzoServer.setVisible(true);
		
		indirizzoServer.setEnabled(true);
		
		// Status bar
		
        barraStato = new JLabel();
		
		barraStato.setBorder(new EtchedBorder());
		
		barraStato.setText("Prova");
		
		
		
		
		
	
	}
	
	
	@Override
	public void visualizza() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void errore(String errore) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void messaggio(String messaggio) {
		// TODO Auto-generated method stub
		
	}
	
	

}
