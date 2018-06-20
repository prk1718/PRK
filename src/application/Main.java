package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

	public Stage primaryStage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		mainWindow();
	}

	public void mainWindow() {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/mainwindow.fxml"));

		try {
			AnchorPane pane = loader.load();
			primaryStage.setMinWidth(400.0);
			primaryStage.setMinHeight(300.0);
			Scene scene = new Scene(pane);
			Controler mainWinCon = loader.getController();
			mainWinCon.setMain(this);
			primaryStage.setScene(scene);
			primaryStage.setTitle("GRA w STATKI");
			primaryStage.resizableProperty().setValue(Boolean.FALSE);
			primaryStage.show();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
