package application;

import java.util.Date;
import java.util.HashMap;


import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ControlerClient {
	private boolean isAppServer;
	private Server server;
	private Client client;
	public String[] getRowColMoj = new String[]{"", ""}, getRowColPrzeciwnik = new String[]{"", ""};
	public Background backDefault = null;
	private HashMap<String, Integer> statkiMap = new HashMap<>();
	private HashMap<String, Integer> statkiPlacedMap = new HashMap<>();

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
	@FXML
	private GridPane mojGrid;
	@FXML
	private GridPane przeciwnikGrid;
	@FXML
	private CheckBox czteroM;
	@FXML
	private CheckBox trzyM;
	@FXML
	private CheckBox dwuM;
	@FXML
	private CheckBox jednoM;


	@FXML
	private void initialize() {
		initButtonView();
		initStatkiMap();
		initStatkiPlacedMap();
		klikGridMojGetRowCol();
		klikGridPrzeciwnikGetRowCol();

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					Thread.sleep(100);

					Platform.runLater(new Runnable() {
						@Override
						public void run() {

							backDefault = ((Button) getButtonByRowColumnIndex(0, 1, mojGrid)).getBackground();

						}
					});

				} catch (InterruptedException e) {

				}

			}

		}).start();

	}

	private void initButtonView() {
		for (Node node : mojGrid.getChildren()) {
			setImageAsBackground(node);
		}
		for (Node node : przeciwnikGrid.getChildren()) {
			setImageAsBackground(node);
		}
	}

	private void setImageAsBackground(Node node) {
		Button button = (Button) node;
		button.setStyle("-fx-border-style: none; -fx-border-width: 0px; -fx-border-insets: 0; -fx-font-size:4px; -fx-background-image: url('button.jpg')");
		Image image = new Image(getClass().getResourceAsStream("../view/button.jpg"));
		button.setGraphic(new ImageView(image));

	}

	public void setServerClient(boolean isApplicationAServer, String port, String ipAdress) {
		isAppServer = isApplicationAServer;

		if (isAppServer) {
			server = new Server(texMessResive, port, this);
		} else {
			client = new Client(texMessResive, port, ipAdress, this);
		}
	}

	@FXML
	public void sendMessageButtonAction() {
		String nick = nickTextField.getText();
		Date date = new Date();
		@SuppressWarnings("deprecation")
		String currentTime = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
		String message = currentTime + " [" + nick + "]: " + textMessSend.getText();
		if (isAppServer) {
			server.send("#mess#" + message + "$mess$");
			server.addToTextArea(message, texMessResive);
		} else {
			client.send("#mess#" + message + "$mess$");
			client.addToTextArea(message, texMessResive);
		}
	}

	@FXML
	public void wyczyscButtonAction() {
		texMessResive.setText("");
	}

	public void klikGridMojGetRowCol() {

		ustawIndexyGridMoj();

		mojGrid.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {

			for (Node node : mojGrid.getChildren()) {

				if (node instanceof Button) {
					if (node.getBoundsInParent().contains(e.getX(), e.getY())) {
						getRowColMoj[0] = "" + GridPane.getRowIndex(node);
						getRowColMoj[1] = "" + GridPane.getColumnIndex(node);
						break;
					}
				}
			}
		});
	}

	public void klikGridPrzeciwnikGetRowCol() {

		ustawIndexyGridPrzeciwnik();

		przeciwnikGrid.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {

			for (Node node : przeciwnikGrid.getChildren()) {

				if (node instanceof Button) {
					if (node.getBoundsInParent().contains(e.getX(), e.getY())) {
						getRowColPrzeciwnik[0] = "" + GridPane.getRowIndex(node);
						getRowColPrzeciwnik[1] = "" + GridPane.getColumnIndex(node);
						break;
					}
				}
			}
		});
	}


	public Node getButtonByRowColumnIndex(final int row, final int column, GridPane gridPane) {
		Node result = null;
		ObservableList<Node> childrens = gridPane.getChildren();

		for (Node node : childrens) {
			if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null) {
				if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
					result = node;
					break;
				}
			}

		}

		return result;
	}

	public void ustawIndexyGridMoj() {
		for (int i = 0; i <= 9; i++) {

			for (int j = 0; j <= 9; j++) {
				GridPane.setRowIndex(mojGrid.getChildren().get((i * 10) + j), i);
				GridPane.setColumnIndex(mojGrid.getChildren().get((i * 10) + j), j);

			}
		}
	}

	public void ustawIndexyGridPrzeciwnik() {
		for (int i = 0; i <= 9; i++) {

			for (int j = 0; j <= 9; j++) {
				GridPane.setRowIndex(przeciwnikGrid.getChildren().get((i * 10) + j), i);
				GridPane.setColumnIndex(przeciwnikGrid.getChildren().get((i * 10) + j), j);

			}
		}
	}

	@FXML
	public void akcjaCheck4() {

		if (czteroM.isSelected() == true) {
			dwuM.setSelected(false);
			trzyM.setSelected(false);
			jednoM.setSelected(false);

		}
	}

	@FXML
	public void akcjaCheck3() {
		if (trzyM.isSelected() == true) {
			dwuM.setSelected(false);
			czteroM.setSelected(false);
			jednoM.setSelected(false);

		}
	}

	@FXML
	public void akcjaCheck2() {
		if (dwuM.isSelected() == true) {
			czteroM.setSelected(false);
			trzyM.setSelected(false);
			jednoM.setSelected(false);

		}
	}

	@FXML
	public void akcjaCheck1() {
		if (jednoM.isSelected() == true) {
			dwuM.setSelected(false);
			trzyM.setSelected(false);
			czteroM.setSelected(false);
		}
	}

	@FXML
	public void akcjaWstaw() {
		String text = "";

		if ((dwuM.isSelected() || jednoM.isSelected() || czteroM.isSelected() || trzyM.isSelected()) && (!getRowColMoj[0].equals("") && !getRowColMoj[1].equals(""))) {
			if (dwuM.isSelected()) {
				text = "2";
			}
			if (trzyM.isSelected()) {
				text = "3";
			}
			if (czteroM.isSelected()) {
				text = "4";
			}
			if (jednoM.isSelected()) {
				text = "1";
			}

			((Button) getButtonByRowColumnIndex(Integer.parseInt(getRowColMoj[0]), Integer.parseInt(getRowColMoj[1]), mojGrid)).setBackground(new Background(new BackgroundFill(
					Color.GRAY, null, null)));
			((Button) getButtonByRowColumnIndex(Integer.parseInt(getRowColMoj[0]), Integer.parseInt(getRowColMoj[1]), mojGrid)).setText(text);

			getRowColMoj[0] = "";
			getRowColMoj[1] = "";
		}

	}

	@FXML
	public void akcjaUsun() {
		if ((!getRowColMoj[0].equals("") && !getRowColMoj[1].equals(""))) {
			((Button) getButtonByRowColumnIndex(Integer.parseInt(getRowColMoj[0]), Integer.parseInt(getRowColMoj[1]), mojGrid)).setBackground(backDefault);
			((Button) getButtonByRowColumnIndex(Integer.parseInt(getRowColMoj[0]), Integer.parseInt(getRowColMoj[1]), mojGrid)).setText("");

			getRowColMoj[0] = "";
			getRowColMoj[1] = "";
		}

	}

	@FXML
	public void akcjaStrzelaj() {
		String message = "#strzal#" + getRowColPrzeciwnik[0] + "," + getRowColPrzeciwnik[1] + "$strzal$";

		if ((!getRowColPrzeciwnik[0].equals("") && !getRowColPrzeciwnik[1].equals(""))) {
			if (isAppServer) {
				server.send(message);
			} else {
				client.send(message);
			}

			getRowColPrzeciwnik[0] = "";
			getRowColPrzeciwnik[1] = "";

		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "Należy zaznaczyć pozycję okrętu przeciwnika !!!", ButtonType.CLOSE);
			alert.showAndWait();
		}


	}

	public void weryfikacja(String mess, TextArea text) {

		if (mess.indexOf("#mess#") != -1 && mess.indexOf("$mess$") != -1) {

			Platform.runLater(new Runnable() {
				@Override
				public void run() {

					text.setText(mess.substring(6, mess.length() - 6) + " \r\n" + text.getText());

				}
			});

		} else if (mess.indexOf("#strzal#") != -1 && mess.indexOf("$strzal$") != -1) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {

					String[] messZapas = mess.substring(8, mess.length() - 8).split(",");

					if (!((Button) getButtonByRowColumnIndex(Integer.parseInt(messZapas[0]), Integer.parseInt(messZapas[1]), mojGrid)).getText().equals("")) {
						((Button) getButtonByRowColumnIndex(Integer.parseInt(messZapas[0]), Integer.parseInt(messZapas[1]), mojGrid)).setBackground(new Background(new BackgroundFill(
								Color.WHITE, null, null)));

						String mess = "#trafiony#" + messZapas[0] + "," + messZapas[1] + "$trafiony$";
						if (isAppServer) {
							server.send(mess);
						} else {
							client.send(mess);
						}

						Alert alert = new Alert(AlertType.INFORMATION, "Zostałeś trafiony strzela dalej przeciwnik !!!!", ButtonType.CLOSE);
						alert.showAndWait();

					} else {
						((Button) getButtonByRowColumnIndex(Integer.parseInt(messZapas[0]), Integer.parseInt(messZapas[1]), mojGrid)).setBackground(new Background(new BackgroundFill(
								Color.LIGHTGREY, null, null)));
						((Button) getButtonByRowColumnIndex(Integer.parseInt(messZapas[0]), Integer.parseInt(messZapas[1]), mojGrid)).setText("P");


						String mess = "#pudlo#" + messZapas[0] + "," + messZapas[1] + "$pudlo$";
						if (isAppServer) {
							server.send(mess);
						} else {
							client.send(mess);
						}

						Alert alert = new Alert(AlertType.INFORMATION, "Przeciwnik spudłował twoja kolej !!!!", ButtonType.CLOSE);
						alert.showAndWait();


					}

				}
			});


		} else if (mess.indexOf("#trafiony#") != -1 && mess.indexOf("$trafiony$") != -1) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {

					String[] messZapas = mess.substring(10, mess.length() - 10).split(",");
					((Button) getButtonByRowColumnIndex(Integer.parseInt(messZapas[0]), Integer.parseInt(messZapas[1]), przeciwnikGrid)).setBackground(new Background(new BackgroundFill(
							Color.GRAY, null, null)));

					Alert alert = new Alert(AlertType.INFORMATION, "Trafiłeś strzelaj dalej !!!!", ButtonType.CLOSE);
					alert.showAndWait();

				}
			});


		} else if (mess.indexOf("#pudlo#") != -1 && mess.indexOf("$pudlo$") != -1) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {

					String[] messZapas = mess.substring(7, mess.length() - 7).split(",");
					((Button) getButtonByRowColumnIndex(Integer.parseInt(messZapas[0]), Integer.parseInt(messZapas[1]), przeciwnikGrid)).setBackground(new Background(new BackgroundFill(
							Color.LIGHTGREY, null, null)));
					((Button) getButtonByRowColumnIndex(Integer.parseInt(messZapas[0]), Integer.parseInt(messZapas[1]), przeciwnikGrid)).setText("P");

					Alert alert = new Alert(AlertType.INFORMATION, "Spudłowałeś strzela przeciwnik !!!!", ButtonType.CLOSE);
					alert.showAndWait();
				}
			});


		}

	}


	private void initStatkiPlacedMap() {
		statkiPlacedMap.put("1", 0);
		statkiPlacedMap.put("2", 0);
		statkiPlacedMap.put("3", 0);
		statkiPlacedMap.put("4", 0);
	}

	private void initStatkiMap() {
		statkiMap.put("1", 4);
		statkiMap.put("2", 3);
		statkiMap.put("3", 2);
		statkiMap.put("4", 1);
	}
}
