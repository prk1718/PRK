package application;

import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

public class ErrorInfoDisplay {
	public void showWrongAdressNumber() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText("Podane wartość nie jest prawidłowa!");
		alert.getDialogPane().setExpandableContent(
				new ScrollPane(new TextArea("Nie można dla połączyć się z podanym adresem.")));
		alert.showAndWait();
	}

	public void showNoServerActive() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText("Brak uruchomionoego serwera!");
		alert.getDialogPane().setExpandableContent(
				new ScrollPane(new TextArea("Proszę najpierw uruchomić serwer.")));
		alert.showAndWait();
	}

	public void placingShipNotPossible() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText("Nie można postawić statku!");
		alert.getDialogPane().setExpandableContent(
				new ScrollPane(new TextArea("Statek nie może zostać postawiony w tym miejscu.")));
		alert.showAndWait();
	}
}
