package application;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

import controller.ControlerClient;

public class Server {
	public static ServerSocket serverSocket;
	public static Socket socket;
	private static ErrorInfoDisplay errorInfoDisplay = new ErrorInfoDisplay();
	public static BufferedReader inputReader;
	public static PrintStream outputPrinter;
	private String textOdb = "";
	public static Thread th=null;

	public Server(TextArea text, String portAdress, ControlerClient controler) {

		th = new Thread(() -> {
			try {
				socket = serverSocket.accept();
				inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				outputPrinter = new PrintStream(socket.getOutputStream());

				while (true) {
					String buf = inputReader.readLine();
					if (buf != null) {
						textOdb = buf;
						controler.weryfikacja(textOdb, text);

					}
				}
			} catch (BindException be) {
				
				try {
				   
					socket.close();
					inputReader.close();
					outputPrinter.close();
					serverSocket.close();

					
				} catch (Exception e) {}
				
				be.printStackTrace();
				System.out.println("Server uległ awarii");
				Platform.runLater(() -> errorInfoDisplay.serverCrashed());
				
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

		});
		
		th.start();
	}

	public static boolean sprSvr(String portAdress) {
		try {
			int port = Integer.parseInt(portAdress);
			serverSocket = new ServerSocket(port);

			return true;
		} catch (BindException be) {
			be.printStackTrace();
			System.out.println("Serwer jest już utworzony");
			Platform.runLater(() -> errorInfoDisplay.serverAlreadyRunning());
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
			Platform.runLater(() -> errorInfoDisplay.clientNotFound());
		}
	}

	public void addToTextArea(String message, TextArea text) {
		String currentText = text.getText();
		text.setText(message + "\r\n" + currentText);
	}

}
