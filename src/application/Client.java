package application;

import java.io.*;
import java.net.*;

import javafx.scene.control.TextArea;

public class Client
{
	private Socket clientSocket;
	private BufferedReader inputReader;
	private PrintStream outputPrinter;
	private String textOdb = "";

	public Client(TextArea text)
	{
		new Thread(() -> {
			try
			{
				clientSocket = new Socket("127.0.0.1", 3344);

				inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				outputPrinter = new PrintStream(clientSocket.getOutputStream());
				while (true)
				{
					String buf = inputReader.readLine();
					if (buf != null)
					{
						textOdb = buf;
						text.setText(textOdb + "\r\n" + text.getText());
					}
				}
			} catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}).start();
	}

	public void close()
	{
		try
		{
			clientSocket.close();
			inputReader.close();
			outputPrinter.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void send(String info)
	{
		outputPrinter.println(info);
	}
}
