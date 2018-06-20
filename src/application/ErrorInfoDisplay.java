package application;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

/**
 * This class is designed to return alerts for all actions which requires user notification<br>
 * This class requires no params, class is public, visible for all project classes
 *
 * @author Rafał Witkowski
 **/
public class ErrorInfoDisplay {
	public void showWrongAdressNumber() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText("Podane wartość nie jest prawidłowa!");
		alert.getDialogPane().setExpandableContent(
				new ScrollPane(new TextArea("Nie można dla połączyć się z podanym adresem.")));
		alert.showAndWait();
	}

	/**
	 * @author Rafał Witkowski
	 * showNoServerActive shows alert for not active server
	 **/
	public void showNoServerActive() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText("Brak uruchomionoego serwera!");
		alert.getDialogPane().setExpandableContent(
				new ScrollPane(new TextArea("Proszę najpierw uruchomić serwer.")));
		alert.showAndWait();
	}

	/**
	 * @author Rafał Witkowski
	 * showNoServerActive shows alert for not possible ship placement
	 **/
	public void placingShipNotPossible() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText("Nie można postawić statku!");
		alert.getDialogPane().setExpandableContent(
				new ScrollPane(new TextArea("Statek nie może zostać postawiony w tym miejscu.")));
		alert.showAndWait();
	}

	/**
	 * @author Rafał Witkowski
	 * enemyMissInfo shows alert of an enemy shoot miss
	 **/
	public void enemyMissInfo() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Przeciwnik spudłował twoja kolej", ButtonType.CLOSE);
		alert.showAndWait();
	}

	/**
	 * @author Rafał Witkowski
	 * youGotHitInfo shows alert of an enemy shoot hit
	 **/
	public void youGotHitInfo() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Zostałeś trafiony, przeciwnik strzela dalej", ButtonType.CLOSE);
		alert.showAndWait();
	}

	/**
	 * @author Rafał Witkowski
	 * youHitInfo shows alert of a players shoot hit
	 **/
	public void youHitInfo() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Trafiłeś, strzelaj dalej", ButtonType.CLOSE);
		alert.showAndWait();
	}

	/**
	 * @author Rafał Witkowski
	 * youMissedInfo shows alert of a players shoot missed
	 **/
	public void youMissedInfo() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Spudłowałeś, strzela przeciwnik", ButtonType.CLOSE);
		alert.showAndWait();
	}

	/**
	 * @author Rafał Witkowski
	 * pickEnemyPositionInfo shows alert of nessesity of picking a field before shooting
	 **/
	public void pickEnemyPositionInfo() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Należy zaznaczyć pozycję okrętu przeciwnika", ButtonType.CLOSE);
		alert.showAndWait();
	}

	/**
	 * @author Rafał Witkowski
	 * wrongPort shows alert of wrong port for connection
	 **/
	public void wrongPort() {
		Alert alert = new Alert(Alert.AlertType.ERROR, "Nieprawidłowy port", ButtonType.CLOSE);
		alert.showAndWait();
	}

	/**
	 * @author Rafał Witkowski
	 * cannotConnectToServer shows alert of not possible connection establishment
	 **/
	public void cannotConnectToServer() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nie można się połączyć z serverem", ButtonType.CLOSE);
		alert.showAndWait();
	}

	/**
	 * @author Rafał Witkowski
	 * wrongPortOrIpAdress shows alert of wrong port or ip adress
	 **/
	public void wrongPortOrIpAdress() {
		Alert alert = new Alert(Alert.AlertType.ERROR, "Nieprawidłowy port lub adres IP ", ButtonType.CLOSE);
		alert.showAndWait();
	}

	/**
	 * @author Rafał Witkowski
	 * clientNotFound shows alert of client application not running
	 **/
	public void clientNotFound() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nie znaleziono klienta", ButtonType.CLOSE);
		alert.showAndWait();
	}

	/**
	 * @author Rafał Witkowski
	 * serverAlreadyRunning shows alert of client server appliaction is already running
	 **/
	public void serverAlreadyRunning() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Server jest już uruchomiony", ButtonType.CLOSE);
		alert.showAndWait();
	}

	/**
	 * @author Rafał Witkowski
	 * serverCrashed shows alert of client server appliaction runetime error
	 **/
	public void serverCrashed() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Server uległ awarii", ButtonType.CLOSE);
		alert.showAndWait();
	}

	/**
	 * @author Rafał Witkowski
	 * showNotStartOfAShip shows alert of not picking a beginning of a ship in deleting ship action
	 **/
	public void showNotStartOfAShip() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText("To nie jest początek statku");
		alert.getDialogPane().setExpandableContent(
				new ScrollPane(new TextArea("Do usunięcia statku należy zaznaczyć jego górną lub lewą część")));
		alert.showAndWait();
	}

	/**
	 * @author Rafał Witkowski
	 * showShipCannotBePlacedHere shows alert of not possible ship placement in picked place
	 **/
	public void showShipCannotBePlacedHere() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText("Nie można tutaj postawić statku");
		alert.getDialogPane().setExpandableContent(
				new ScrollPane(new TextArea("Statek nie może stykać się z innym statkiem")));
		alert.showAndWait();
	}
	

	/**
	 * @author Seweryn Czapiewski
	 * Alert w przypadku gdy przeciwnik ustawi wszystkie statki i potwierdzi gotowosc
	 */
	public void potwierdzeniePrzeciwnika() {

		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Przeciwnik potwierdza gotowość do gry !!!", ButtonType.CLOSE);
		alert.showAndWait();
	}
	
	/**
	 * @author Seweryn Czapiewski
	 * Alert w przypadku gdy gracz bedzie chcial rozpoczac gre a nie ustawi wszystkich okretow
	 */
	public void bladRozpoczecia() {

		Alert alert1 = new Alert(Alert.AlertType.INFORMATION,"Należy wpowadzić wszystkie swoje statki !!!!", ButtonType.CLOSE);
		alert1.showAndWait();
	}
	

	/**
	 * @author Seweryn Czapiewski
	 * Alert rozpoczecia pojedynku w przypadku gdy przeciwnik szybciej kliknal przycisk start
	 */
	public void przeciwnikRozpoczyna() {

		Alert alert2 = new Alert(Alert.AlertType.INFORMATION,"Rozpoczyna przeciwnik !!!!", ButtonType.CLOSE);
		alert2.showAndWait();
	}
	
	/**
	 * @author Seweryn Czapiewski
	 * Alert rozpoczecia pojedynku w przypadku gdy gracz szybciej kliknal przycisk start niz przeciwnik
	 */
	public void jaRozpoczynam() {


		Alert alert2 = new Alert(Alert.AlertType.INFORMATION,"Rozpoczynasz grę czekaj na potwierdzenie od przeciwnika !!!!", ButtonType.CLOSE);
		alert2.showAndWait();
	}
	
	/**
	 * @author Seweryn Czapiewski
	 * Alert proba strzalu w to miejsce byla juz podejmowana
	 */
	public void strzalJuzByl() {


		Alert alert2 = new Alert(Alert.AlertType.INFORMATION,"Już strzelałeś w to miejsce !!!!", ButtonType.CLOSE);
		alert2.showAndWait();
	}
	
	/**
	 * @author Seweryn Czapiewski
	 * Alert informujący o trafieniu wszystkich statków
	 */
	public void weryfikacjaTrafien(boolean jaPrzeciwnik)
	{
		

if(jaPrzeciwnik)
{

	Alert alert2 = new Alert(Alert.AlertType.INFORMATION,"Koniec gry zniszczyłeś wszystkie statki przeciwnika !!!!", ButtonType.CLOSE);
	alert2.showAndWait();
}else
{
	Alert alert2 = new Alert(Alert.AlertType.INFORMATION,"Koniec gry przeciwnik zniszczył ci wszystkie statki !!!!", ButtonType.CLOSE);
	alert2.showAndWait();
	
}

	}
}
