package application;

import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControlerClient
{
	private boolean isAppServer;
	private Server server;
	private Client client;

	@FXML
	private Button sendMessageButton;
	@FXML
	private Button wyczyscButton;
	@FXML
	private TextField textMessSend;
	@FXML
	private TextField nickTextField;
	@FXML
	private TextArea texMessResive;

	public void setServerClient(boolean isApplicationAServer)
	{
		isAppServer = isApplicationAServer;

		if (isAppServer)
		{
			server = new Server(texMessResive);
		} else
		{
			client = new Client(texMessResive);
		}
	}

	@FXML
	public void sendMessageButtonAction()
	{
		String nick = nickTextField.getText();
		Date date = new Date();
		@SuppressWarnings("deprecation")
		String currentTime = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
		String message = currentTime + " [" + nick + "]: " + textMessSend.getText();
		if (isAppServer)
		{
			server.send(message);
			server.addToTextArea(message, texMessResive);
		} else
		{
			client.send(message);
			client.addToTextArea(message, texMessResive);
		}
	}

	@FXML
	public void wyczyscButtonAction()
	{
		texMessResive.setText("");
	}
}
