package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controler {

	private Main main;

	@FXML private Button clientButton;
	@FXML private Button serverButton;

	public void setMain(Main main)
	{
		this.main = main;
	}

	@FXML
	public void loadClientButton()
	{
	FXMLLoader loader = new FXMLLoader(Main.class.getResource("/controller/client.fxml"));

		try {
			Stage primaryStage = new Stage();
			AnchorPane pane = loader.load();
			primaryStage.setMinWidth(800.0);
			primaryStage.setMinHeight(800.0);
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void loadServerButton()
	{

	}
}
