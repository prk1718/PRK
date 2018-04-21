package application;

import java.net.ConnectException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controler {

	private Main main;
	private String clientPort;
	private String serverPort;
	private String ipAdress;

	private String regexForPort = "^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$";
	private String regexForIP = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	private static String DEFAULT_IP_ADRESS = "127.0.0.1";
	private static String DEFAULT_PORT = "3343";

	@FXML
	private Button clientButton;
	@FXML
	private Button serverButton;
	@FXML
	private TextField ipAdressClientTextField;
	@FXML
	private TextField portClientTextField;
	@FXML
	private TextField portServerTextField;

	public void setMain(Main main) {
		this.main = main;
		ipAdressClientTextField.setText(DEFAULT_IP_ADRESS);
		portClientTextField.setText(DEFAULT_PORT);
		portServerTextField.setText(DEFAULT_PORT);
	}

	@FXML
	public void loadClientButton() {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("/controller/client.fxml"));

		try {
			Stage primaryStage = new Stage();
			AnchorPane pane = loader.load();
			primaryStage.setMinWidth(800.0);
			primaryStage.setMinHeight(800.0);
			Scene scene = new Scene(pane);
			ControlerClient cl = loader.getController();
			getConnectionParametersForClient();
			cl.setServerClient(false, serverPort, ipAdress);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Klient");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void loadServerButton() {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("/controller/client.fxml"));

		try {
			Stage primaryStage = new Stage();
			AnchorPane pane = loader.load();
			primaryStage.setMinWidth(800.0);
			primaryStage.setMinHeight(800.0);
			Scene scene = new Scene(pane);
			ControlerClient cl = loader.getController();
			getConnectionParametersForServer();
			cl.setServerClient(true, serverPort, null);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Serwer");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getConnectionParametersForServer() throws ConnectException {
		serverPort = portServerTextField.getText();
		if (!validatePort(serverPort)) {
			Alert alert = new Alert(AlertType.ERROR, "Nieprawidłowy port ", ButtonType.CLOSE);
			alert.showAndWait();
			throw new ConnectException();
		}
	}

	private void getConnectionParametersForClient() throws ConnectException {
		clientPort = portClientTextField.getText();
		ipAdress = ipAdressClientTextField.getText();
		if (!validatePort(clientPort) || !validateIp(ipAdress)) {
			Alert alert = new Alert(AlertType.ERROR, "Nieprawidłowy port lub adres IP ", ButtonType.CLOSE);
			alert.showAndWait();
			throw new ConnectException();
		}
	}

	private boolean validatePort(String port) {
		Pattern pattern = Pattern.compile(regexForPort);
		Matcher matcher = pattern.matcher(port);
		return matcher.matches();
	}

	private boolean validateIp(String adressIp) {
		Pattern pattern = Pattern.compile(regexForIP);
		Matcher matcher = pattern.matcher(adressIp);
		return matcher.matches();
	}

}
