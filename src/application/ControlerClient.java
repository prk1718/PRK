package application;

import java.util.Date;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControlerClient {
	private boolean isAppServer;
	private Server server;
	private Client client;
	public String getRowColMoj = "", getRowColPrzeciwnik = "";

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
	private void initialize() {

		klikGridMojGetRowCol();
		klikGridPrzeciwnikGetRowCol();

	}

	public void setServerClient(boolean isApplicationAServer, String port, String ipAdress) {
		isAppServer = isApplicationAServer;

		if (isAppServer) {
			server = new Server(texMessResive, port);
		} else {
			client = new Client(texMessResive, port, ipAdress);
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
			server.send(message);
			server.addToTextArea(message, texMessResive);
		} else {
			client.send(message);
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
						getRowColMoj = GridPane.getRowIndex(node) + "/" + GridPane.getColumnIndex(node);
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
						getRowColPrzeciwnik = GridPane.getRowIndex(node) + "/" + GridPane.getColumnIndex(node);
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
}
