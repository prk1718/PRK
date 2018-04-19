package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControlerClient {

	public boolean serverClient;
    public Server svr;
	public Klient kl;

	
	@FXML private Button sendMessageButton;
	@FXML private TextField textMessSend;
	@FXML private TextArea texMessResive;
	
	public void setServerClient(boolean svrCl)
	{
		serverClient = svrCl;
		
		if(svrCl)
		{
			svr = new Server(texMessResive);

		}else
		{
			kl = new Klient(texMessResive);

		}
		
	}
	


	
	@FXML
	public void sendMessageButtonAction()
	{
		if(serverClient)
		{
			svr.send(textMessSend.getText());
    	

			
		}else
		{
		    kl.send(textMessSend.getText());

			
		}
	}
}
