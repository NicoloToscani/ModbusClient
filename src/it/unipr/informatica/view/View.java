package it.unipr.informatica.view;
public interface View {
	
	public void show();
	
	public void error(String error);
	
	public void message(String message);
	
	public void sure();
	
}