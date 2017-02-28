package it.unipr.informatica.controller;

import it.unipr.informatica.view.ViewBuilder;
import it.unipr.informatica.view.ViewSwing;


public class Modbus_Client implements Runnable {
	
	private ViewBuilder view;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		view = new ViewBuilder(this);
		
		view.setVisible(true);
		
	}
	
	
	public void esci(){
		System.exit(0);
	}
	
	

}
