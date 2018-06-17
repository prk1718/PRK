package application;

import java.io.*;
import java.net.*;

import javafx.scene.control.TextArea;

public class Client {
	private Socket clientSocket;
	private BufferedReader inputReader;
	private PrintStream outputPrinter;
	private String textOdb = "";
	private ErrorInfoDisplay errorInfoDisplay;

	public Client(TextArea text, String portAdress, String ipAdress,ControlerClient controler) {
		int port;
		boolean isConnectOk = false;
		try {
			if (portAdress == null) {
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
					clientSocket = new Socket(ipAdress, port);

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
					ex.printStackTrace();
				}
			}).start();
		}
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
