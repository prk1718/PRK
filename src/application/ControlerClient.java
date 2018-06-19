package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ControlerClient {

	private Server server;
	private Client client;
	private boolean isAppServer;

	public String[] getRowColMoj = new String[]{"", ""}, getRowColPrzeciwnik = new String[]{"", ""};
	public Background backDefault = null;
	private HashMap<String, Integer> statkiMap = new HashMap<>();
	private HashMap<String, Integer> statkiPlacedMap = new HashMap<>();
	private ArrayList<CheckBox> checkBoxArrayList = new ArrayList<>();
	private ErrorInfoDisplay errorInfoDisplay = new ErrorInfoDisplay();
	private SoundPlayer soundPlayer = new SoundPlayer();

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
	private CheckBox orientacja;

	@FXML
	private void initialize() {
		initcheckBoxArrayList();
		initButtonView();
		initStatkiMap();
		initStatkiPlacedMap();
		klikGridMojGetRowCol();
		klikGridPrzeciwnikGetRowCol();

		new Thread(() -> {
			try {

				Thread.sleep(100);

				Platform.runLater(() -> backDefault = ((Button) getButtonByRowColumnIndex(0, 1, mojGrid)).getBackground());

			} catch (InterruptedException e) {

			}

		}).start();

	}

	private void initcheckBoxArrayList() {
		checkBoxArrayList.add(jednoM);
		checkBoxArrayList.add(dwuM);
		checkBoxArrayList.add(trzyM);
		checkBoxArrayList.add(czteroM);
	}

	private String getSelectedCheckboxValue() {
		String selectedCheckbox = "";
		for (CheckBox checkBox : checkBoxArrayList) {
			if (checkBox.isSelected()) {
				selectedCheckbox = checkBox.getText().substring(0, 1);
			}
		}
		return selectedCheckbox;
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
		button.setStyle("-fx-border-style: none; -fx-border-width: 0px; -fx-border-insets: 0; -fx-font-size:1px; -fx-background-image: url('button.jpg')");
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

		setPickedFieldIndex(mojGrid, getRowColMoj);
	}

	private void setPickedFieldIndex(GridPane mojGrid, String[] getRowColMoj) {
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

		setPickedFieldIndex(przeciwnikGrid, getRowColPrzeciwnik);
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
		setProperIndexForGrid(mojGrid);
	}

	public void ustawIndexyGridPrzeciwnik() {
		setProperIndexForGrid(przeciwnikGrid);
	}

	private void setProperIndexForGrid(GridPane przeciwnikGrid) {
		for (int i = 0; i <= 9; i++) {

			for (int j = 0; j <= 9; j++) {
				GridPane.setRowIndex(przeciwnikGrid.getChildren().get((i * 10) + j), i);
				GridPane.setColumnIndex(przeciwnikGrid.getChildren().get((i * 10) + j), j);

			}
		}
	}

	@FXML
	public void akcjaCheck4() {
		setRadioButtonsNotSelected();
		czteroM.setSelected(true);
	}

	@FXML
	public void akcjaCheck3() {
		setRadioButtonsNotSelected();
		trzyM.setSelected(true);
	}

	@FXML
	public void akcjaCheck2() {
		setRadioButtonsNotSelected();
		dwuM.setSelected(true);
	}

	@FXML
	public void akcjaCheck1() {
		setRadioButtonsNotSelected();
		jednoM.setSelected(true);
	}

	private void setRadioButtonsNotSelected() {
		jednoM.setSelected(false);
		dwuM.setSelected(false);
		trzyM.setSelected(false);
		czteroM.setSelected(false);
	}

	@FXML
	public void akcjaWstaw() {
		String selectedShipModel = getSelectedCheckboxValue();
		if (checkBoxArrayList.stream().anyMatch(checkBox -> checkBox.isSelected()) && !selectedShipModel.equals("") && (!getRowColMoj[0].equals("") && !getRowColMoj[1].equals(""))) {
			Integer howManyShipPlaced = statkiPlacedMap.get(selectedShipModel);
			String pickedField = getRowColMoj[0] + "/" + getRowColMoj[1];
			if (!checkIfShipCanBePlaced()) {
				errorInfoDisplay.showShipCannotBePlacedHere();
				return;
			}

			if (howManyShipPlaced >= 0 && howManyShipPlaced < statkiMap.get(selectedShipModel)) {
				for (Node node : mojGrid.getChildren()) {
					if (node instanceof Button) {

						String selectedStartingPoint = GridPane.getRowIndex(node) + "/" + GridPane.getColumnIndex(node);
						if (selectedStartingPoint.equals(pickedField)) {

							Integer howManyShipToPlace = Integer.parseInt(selectedShipModel);
							Integer row = GridPane.getRowIndex(node);
							Integer col = GridPane.getColumnIndex(node);
							boolean ustawiacPoziomo = orientacja.isSelected();
							if (ustawiacPoziomo) {
								if (row + howManyShipToPlace > 10) {
									errorInfoDisplay.placingShipNotPossible();
									break;
								}
								for (int i = row; i < row + howManyShipToPlace; i++) {
									Button button = (Button) getButtonByRowColumnIndex(i, col, mojGrid);
									setDisplayForPlacedShip(button);
									button.setText("|");
								}
							} else {
								if (col + howManyShipToPlace > 10) {
									errorInfoDisplay.placingShipNotPossible();
									break;
								}
								for (int i = col; i < col + howManyShipToPlace; i++) {
									Button button = (Button) getButtonByRowColumnIndex(row, i, mojGrid);
									setDisplayForPlacedShip(button);
									button.setText("-");
								}
							}

							Integer placed = statkiPlacedMap.get(selectedShipModel) + 1;
							statkiPlacedMap.replace(selectedShipModel, placed);
						}
					}
				}

				getRowColMoj[0] = "";
				getRowColMoj[1] = "";
			}
		}
		renderFieldsNotPossibleForPlacingShip();
		setProperTextForCheckbox();
	}

	private void setNotPossibleImageForFields(int row, int col) {
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				if (i >= 0 && i <= 9 && j >= 0 && j <= 9) {
					Button button = (Button) getButtonByRowColumnIndex(i, j, mojGrid);
					if (button.getText().equals("")) {
						button.setStyle("-fx-border-style: none; -fx-border-width: 0px; -fx-border-insets: 0; -fx-font-size:1px; -fx-background-image: url('placingShipNotPossible.jpg')");
						Image image = new Image(getClass().getResourceAsStream("../view/placingShipNotPossible.jpg"));
						button.setGraphic(new ImageView(image));
					}
				}
			}
		}
	}


	private void setDisplayForPlacedShip(Button button) {
		button.setStyle("-fx-border-style: none; -fx-border-width: 0px; -fx-border-insets: 0; -fx-font-size:1px; -fx-background-image: url('button.jpg')");
		Image image = new Image(getClass().getResourceAsStream("../view/ship.jpg"));
		button.setGraphic(new ImageView(image));
	}

	@FXML
	public void akcjaUsun() {
		if ((!getRowColMoj[0].equals("") && !getRowColMoj[1].equals(""))) {
			int count = 0;
			Integer row = Integer.parseInt(getRowColMoj[0]);
			Integer col = Integer.parseInt(getRowColMoj[1]);
			String orientacjaStatku = ((Button) getButtonByRowColumnIndex(row, col, mojGrid)).getText();

			for (int i = 0; i < 4; i++) {
				if (orientacjaStatku.equals("|") && (row + i < 10)) {
					if (validateVerticalDeleteButtonAction(row, col))
						return;
					Button button = (Button) getButtonByRowColumnIndex(row + i, col, mojGrid);
					if (button.getText().equals("|")) {
						setDefaultBackgroundAndTextForField(button);
						count++;
					}
				} else if (orientacjaStatku.equals("-") && (col + i < 10)) {
					if (validateHorizontalDeleteButtonAction(row, col))
						return;
					Button button = (Button) getButtonByRowColumnIndex(row, col + i, mojGrid);
					if (button.getText().equals("-")) {
						setDefaultBackgroundAndTextForField(button);
						count++;
					}
				}
			}
			if (count != 0) {
				int howManyShipPlaced = statkiPlacedMap.get(String.valueOf(count));
				if (howManyShipPlaced > 0) {
					statkiPlacedMap.replace(String.valueOf(count), howManyShipPlaced - 1);
				}
			}
			getRowColMoj[0] = "";
			getRowColMoj[1] = "";
		}
		renderFieldsNotPossibleForPlacingShip();
		setProperTextForCheckbox();
	}

	private void renderFieldsNotPossibleForPlacingShip() {
		//RW: tu muszą być dwie oddzielne pętle
		for (Node node : mojGrid.getChildren()) {
			if (node instanceof Button) {
				Button button = (Button) node;
				if (button.getText().equals("")) {
					setDefaultBackgroundForField(button);
				}
			}
		}
		for (Node node : mojGrid.getChildren()) {
			if (node instanceof Button) {
				int row = GridPane.getRowIndex(node);
				int col = GridPane.getColumnIndex(node);
				Button button = (Button) node;
				if (!button.getText().equals(""))
					setNotPossibleImageForFields(row, col);
			}
		}
	}

	private void setProperTextForCheckbox() {
		jednoM.setText("1 (" + (statkiMap.get("1") - statkiPlacedMap.get("1")) + ")");
		dwuM.setText("2 (" + (statkiMap.get("2") - statkiPlacedMap.get("2")) + ")");
		trzyM.setText("3 (" + (statkiMap.get("3") - statkiPlacedMap.get("3")) + ")");
		czteroM.setText("4 (" + (statkiMap.get("4") - statkiPlacedMap.get("4")) + ")");
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
			errorInfoDisplay.pickEnemyPositionInfo();
		}
	}

	public void weryfikacja(String mess, TextArea text) {

		if (mess.indexOf("#mess#") != -1 && mess.indexOf("$mess$") != -1) {

			Platform.runLater(() -> text.setText(mess.substring(6, mess.length() - 6) + " \r\n" + text.getText()));

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
							soundPlayer.playSound(this.getClass().getClassLoader(), "view/hit.wav");
						} else {
							client.send(mess);
							soundPlayer.playSound(this.getClass().getClassLoader(), "view/hit.wav");
						}
						errorInfoDisplay.youGotHitInfo();

					} else {
						((Button) getButtonByRowColumnIndex(Integer.parseInt(messZapas[0]), Integer.parseInt(messZapas[1]), mojGrid)).setBackground(new Background(new BackgroundFill(
								Color.LIGHTGREY, null, null)));
						((Button) getButtonByRowColumnIndex(Integer.parseInt(messZapas[0]), Integer.parseInt(messZapas[1]), mojGrid)).setText("P");


						String mess = "#pudlo#" + messZapas[0] + "," + messZapas[1] + "$pudlo$";
						if (isAppServer) {
							server.send(mess);
							soundPlayer.playSound(this.getClass().getClassLoader(), "view/miss.wav");
						} else {
							client.send(mess);
							soundPlayer.playSound(this.getClass().getClassLoader(), "view/miss.wav");
						}
						errorInfoDisplay.enemyMissInfo();
					}
				}
			});
		} else if (mess.indexOf("#trafiony#") != -1 && mess.indexOf("$trafiony$") != -1) {
			Platform.runLater(() -> {

				String[] messZapas = mess.substring(10, mess.length() - 10).split(",");
				((Button) getButtonByRowColumnIndex(Integer.parseInt(messZapas[0]), Integer.parseInt(messZapas[1]), przeciwnikGrid)).setBackground(new Background(new BackgroundFill(
						Color.GRAY, null, null)));

				errorInfoDisplay.youHitInfo();
			});
		} else if (mess.indexOf("#pudlo#") != -1 && mess.indexOf("$pudlo$") != -1) {
			Platform.runLater(() -> {

				String[] messZapas = mess.substring(7, mess.length() - 7).split(",");
				((Button) getButtonByRowColumnIndex(Integer.parseInt(messZapas[0]), Integer.parseInt(messZapas[1]), przeciwnikGrid)).setBackground(new Background(new BackgroundFill(
						Color.LIGHTGREY, null, null)));
				((Button) getButtonByRowColumnIndex(Integer.parseInt(messZapas[0]), Integer.parseInt(messZapas[1]), przeciwnikGrid)).setText("P");

				errorInfoDisplay.youMissedInfo();
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

	private void setDefaultBackgroundForField(Button button) {
		button.setStyle("-fx-border-style: none; -fx-border-width: 0px; -fx-border-insets: 0; -fx-font-size:1px; -fx-background-image: url('button.jpg')");
		Image image = new Image(getClass().getResourceAsStream("../view/button.jpg"));
		button.setGraphic(new ImageView(image));
	}

	private void setDefaultBackgroundAndTextForField(Button button) {
		button.setStyle("-fx-border-style: none; -fx-border-width: 0px; -fx-border-insets: 0; -fx-font-size:1px; -fx-background-image: url('button.jpg')");
		Image image = new Image(getClass().getResourceAsStream("../view/button.jpg"));
		button.setText("");
		button.setGraphic(new ImageView(image));
	}

	private boolean validateHorizontalDeleteButtonAction(Integer row, Integer col) {
		if (col - 1 > 0) {
			Button button = (Button) getButtonByRowColumnIndex(row, col - 1, mojGrid);
			if (button.getText().equals("-")) {
				errorInfoDisplay.showNotStartOfAShip();
				return true;
			}
		}
		return false;
	}

	private boolean validateVerticalDeleteButtonAction(Integer row, Integer col) {
		if (row - 1 > 0) {
			Button button = (Button) getButtonByRowColumnIndex(row - 1, col, mojGrid);
			if (button.getText().equals("|")) {
				errorInfoDisplay.showNotStartOfAShip();
				return true;
			}
		}
		return false;
	}

	private boolean checkIfShipCanBePlaced() {
		Boolean isOk = true;
		Integer row = Integer.parseInt(getRowColMoj[0]);
		Integer col = Integer.parseInt(getRowColMoj[1]);

		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				if (i >= 0 && i <= 9 && j >= 0 && j <= 9) {
					if (validatePlacingShipForRowAndCol(i, j)) {
						return false;
					}
				}
			}
		}
		return isOk;
	}

	private boolean validatePlacingShipForRowAndCol(Integer row, Integer col) {
		Button button = (Button) getButtonByRowColumnIndex(row, col, mojGrid);
		if (!button.getText().equals(""))
			return true;
		return false;
	}

}
