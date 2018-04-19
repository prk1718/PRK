package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controler
{

	private Main main;

	@FXML
	private Button clientButton;
	@FXML
	private Button serverButton;

	public void setMain(Main main)
	{
		this.main = main;
	}

	@FXML
	public void loadClientButton()
	{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("/controller/client.fxml"));

		try
		{
			Stage primaryStage = new Stage();
			AnchorPane pane = loader.load();
			primaryStage.setMinWidth(800.0);
			primaryStage.setMinHeight(800.0);
			Scene scene = new Scene(pane);
			ControlerClient cl = loader.getController();
			cl.setServerClient(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Klient");
			primaryStage.show();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	public void loadServerButton()
	{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("/controller/client.fxml"));

		try
		{
			Stage primaryStage = new Stage();
			AnchorPane pane = loader.load();
			primaryStage.setMinWidth(800.0);
			primaryStage.setMinHeight(800.0);
			Scene scene = new Scene(pane);
			ControlerClient cl = loader.getController();
			cl.setServerClient(true);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Serwer");
			primaryStage.show();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
