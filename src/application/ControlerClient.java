package application;

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
	private TextField textMessSend;
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
		if (isAppServer)
		{
			server.send(textMessSend.getText());
		} else
		{
			client.send(textMessSend.getText());
		}
	}
}
