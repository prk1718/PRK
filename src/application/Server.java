package application;

import java.io.*;
import java.net.*;

import javafx.scene.control.TextArea;

public class Server
{
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader inputReader;
	private PrintStream outputPrinter;
	private String textOdb = "";

	public Server(TextArea text)
	{
		new Thread(() -> {
			try
			{
				serverSocket = new ServerSocket(3344);

				socket = serverSocket.accept();

				inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				outputPrinter = new PrintStream(socket.getOutputStream());

				while (true)
				{
					String buf = inputReader.readLine();
					if (buf != null)
					{
						textOdb = buf;
						text.setText(textOdb + "\r\n" + text.getText());
					}
				}
			} catch (BindException be)
			{
				be.printStackTrace();
				System.out.println("Serwer jest ju¿ utworzony");
			} catch (IOException ioe)
			{
				ioe.printStackTrace();
			}

		}).start();
	}

	public void close()
	{
		try
		{
			socket.close();
			serverSocket.close();
			inputReader.close();
			outputPrinter.close();
		} catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

	public void send(String info)
	{
		try
		{
			outputPrinter.println(info);
		} catch (NullPointerException npe)
		{
			npe.printStackTrace();
			System.out.println("Nie znaleziono klienta");
		}
	}

}
