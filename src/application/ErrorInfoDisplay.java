package application;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

	public void enemyMissInfo() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Przeciwnik spudłował twoja kolej", ButtonType.CLOSE);
		alert.showAndWait();
	}

	public void youGotHitInfo() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Zostałeś trafiony, przeciwnik strzela dalej", ButtonType.CLOSE);
		alert.showAndWait();
	}

	public void youHitInfo() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Trafiłeś, strzelaj dalej", ButtonType.CLOSE);
		alert.showAndWait();
	}

	public void youMissedInfo() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Spudłowałeś, strzela przeciwnik", ButtonType.CLOSE);
		alert.showAndWait();
	}

	public void pickEnemyPositionInfo() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Należy zaznaczyć pozycję okrętu przeciwnika", ButtonType.CLOSE);
		alert.showAndWait();
	}

	public void wrongPort() {
		Alert alert = new Alert(Alert.AlertType.ERROR, "Nieprawidłowy port", ButtonType.CLOSE);
		alert.showAndWait();
	}

	public void cannotConnectToServer() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nie można się połączyć z serverem", ButtonType.CLOSE);
		alert.showAndWait();
	}

	public void wrongPortOrIpAdress() {
		Alert alert = new Alert(Alert.AlertType.ERROR, "Nieprawidłowy port lub adres IP ", ButtonType.CLOSE);
		alert.showAndWait();
	}

	public void clientNotFound() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nie znaleziono klienta", ButtonType.CLOSE);
		alert.showAndWait();
	}

	public void serverAlreadyRunning() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Server jest już uruchomiony", ButtonType.CLOSE);
		alert.showAndWait();
	}

	public void serverCrashed() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Server uległ awarii", ButtonType.CLOSE);
		alert.showAndWait();
	}

	public void showNotStartOfAShip() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText("To nie jest początek statku");
		alert.getDialogPane().setExpandableContent(
				new ScrollPane(new TextArea("Do usunięcia statku należy zaznaczyć jego górną lub lewą część")));
		alert.showAndWait();
	}

}
