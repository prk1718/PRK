package application;

import java.io.*;
import java.net.*;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Server {
	private static ServerSocket serverSocket;
	private static Socket socket;
	private BufferedReader inputReader;
	private PrintStream outputPrinter;
	private String textOdb = "";

	public Server(TextArea text, String portAdress,ControlerClient controler) {
				
		new Thread(() -> {
			try {
			

				socket = serverSocket.accept();
				inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				outputPrinter = new PrintStream(socket.getOutputStream());

				while (true) {
					String buf = inputReader.readLine();
					if (buf != null) {
						textOdb = buf;
						controler.weryfikacja(textOdb,text);

					}
				}
			} catch (BindException be) {
				be.printStackTrace();
				System.out.println("Server uległ awarii");
				Platform.runLater(new Runnable() {
			        @Override
			        public void run() {
			      
			        	Alert alert = new Alert(AlertType.INFORMATION, "Server uległ awarii!!!!", ButtonType.CLOSE);
						alert.showAndWait();
			        	
			        }
			   });
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

		}).start();
	}
	
	public static boolean sprSvr(String portAdress)
	{

		try {
			int port = Integer.parseInt(portAdress);
			serverSocket = new ServerSocket(port);

         return true;
		} catch (BindException be) {
			be.printStackTrace();
			System.out.println("Serwer jest ju� utworzony");
			Platform.runLater(new Runnable() {
		        @Override
		        public void run() {
		      
		        	Alert alert = new Alert(AlertType.INFORMATION, "Server jest już uruchomiony!!!!", ButtonType.CLOSE);
					alert.showAndWait();
					
		        	
		        }
		   });			
			
			
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	
		return false;
		
	}

	public void close() {
		try {
			socket.close();
			serverSocket.close();
			inputReader.close();
			outputPrinter.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void send(String info) {
		try {
			outputPrinter.println(info);
		} catch (NullPointerException npe) {
			npe.printStackTrace();
			System.out.println("Nie znaleziono klienta");
			Platform.runLater(new Runnable() {
		        @Override
		        public void run() {
		      
		        	Alert alert = new Alert(AlertType.INFORMATION, "Nie znaleziono klienta !!!!", ButtonType.CLOSE);
					alert.showAndWait();
		        	
		        }
		   });
		}
	}

	public void addToTextArea(String message, TextArea text) {
		String currentText = text.getText();
		text.setText(message + "\r\n" + currentText);
	}

}
