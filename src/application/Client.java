package application;

import java.io.*;
import java.net.*;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class Client {
	private static Socket clientSocket;
	private BufferedReader inputReader;
	private PrintStream outputPrinter;
	private String textOdb = "";
	private ErrorInfoDisplay errorInfoDisplay;

	public Client(TextArea text, String portAdress, String ipAdress,ControlerClient controler) {
		int port;
		boolean isConnectOk = false;
		try {

			if (portAdress == null) {
				Platform.runLater(new Runnable() {
			        @Override
			        public void run() {
			      
			        	Alert alert = new Alert(AlertType.INFORMATION, "Nie można się połączyć z serverem !!!!", ButtonType.CLOSE);
						alert.showAndWait();
			        	
			        }
				 });
				errorInfoDisplay.showNoServerActive();
				return;
			}

			port = Integer.parseInt(portAdress);
			isConnectOk = true;

		} catch (NumberFormatException e) {
			e.printStackTrace();
			errorInfoDisplay.showWrongAdressNumber();

			return;
		}
		if (isConnectOk) {

			new Thread(() -> {
				try {
					

					inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					outputPrinter = new PrintStream(clientSocket.getOutputStream());
					while (true) {
						String buf = inputReader.readLine();
						if (buf != null) {
							textOdb = buf;
							controler.weryfikacja(textOdb,text);
						}
					}
				} catch (IOException ex) {
					//ex.printStackTrace();
					Platform.runLater(new Runnable() {
				        @Override
				        public void run() {
				      
				        	Alert alert = new Alert(AlertType.INFORMATION, "Nie można się połączyć z serverem !!!!", ButtonType.CLOSE);
							alert.showAndWait();
				        	
				        }
					 });
					
				}
			}).start();
		}
	}
	
	public static boolean sprKlient(String ipAdress,int port)
	{
		try
		{
		    InetAddress inet = InetAddress.getByName(ipAdress);		   
		    if(inet.isReachable(2000)==true)
		    {
				clientSocket = new Socket(ipAdress, port);
				return true;
		    }
			
		}catch(Exception ex)
		{
			
		}	
		
		return false;
	}

	public void close() {
		try {
			clientSocket.close();
			inputReader.close();
			outputPrinter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(String info) {
		outputPrinter.println(info);
	}

	public void addToTextArea(String message, TextArea text) {
		String currentText = text.getText();
		text.setText(message + "\r\n" + currentText);
	}

}
