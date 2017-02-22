package it.unipr.informatica.controller;

import it.unipr.informatica.view.ViewSwing;

public class Modbus_Client implements Runnable {
	
	private ViewSwing vista;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		vista = new ViewSwing(this);
		
		vista.visualizza();
		
	}
	
	
	public void esci(){
		System.exit(0);
	}
	
	

}
