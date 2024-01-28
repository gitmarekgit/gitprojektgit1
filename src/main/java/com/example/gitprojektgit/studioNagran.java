package com.example.gitprojektgit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static com.example.gitprojektgit.zmianaTrybu.getTryb;


public class studioNagran {

    @FXML
    private Label nazwaStudia;

    @FXML
    private TableView<Muzyka> tabelaMuzyki;
    @FXML
    private AnchorPane scenePane;
    private Lista lista;
    private final zmianaTrybu zmienTryb = new zmianaTrybu();

    double tryb = getTryb();
    @FXML
    public void initialize() {
        lista = new Lista();
        lista.inicjalizujTabele(tabelaMuzyki);

    if(tryb == 0){
        try {
            ustawTrybJasny();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    else{
        try {
            ustawTrybCiemny();
        } catch (IOException e) {
            throw new RuntimeException(e);
    }
}
        System.out.println(tryb);
    }
    public void ustawTrybJasny() throws IOException {
        zmienTryb.trybJasny(scenePane);
    }

    public void ustawTrybCiemny() throws IOException{
        zmienTryb.trybCiemny(scenePane);
    }

    @FXML
    public void zmienNazweStudia() {
        try {
            // zmiana nazwy studia
            String nowaNazwaStudia = zmianaNazwyStudia.pokazOkno();
            nazwaStudia.setText(nowaNazwaStudia);
        } catch (IOException e) {
            obsluzBlad("Błąd podczas zmiany nazwy studia.", e);
        }
    }

    @FXML
    public void dodaj() {
        try {
            String nazwaUzytkownika = Session.getLoggedInUserName();
            oknoDodaj.dodaj(nazwaUzytkownika, lista);
        } catch (IOException e) {
            obsluzBlad("Błąd podczas dodawania utworu.", e);
        }
    }

    @FXML
    public void usunUtwor() {
        // Pobierz zaznaczony utwór
        Muzyka wybranyUtwor = tabelaMuzyki.getSelectionModel().getSelectedItem();

        if (wybranyUtwor != null) {
            // Wyświetl komunikat potwierdzający usunięcie
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Usuń Utwór");
            alert.setHeaderText(null);
            alert.setContentText("Czy na pewno chcesz usunąć ten utwór?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(ButtonType.CANCEL) == ButtonType.OK) {
                // Usuń zaznaczony utwór z listy
                lista.usunUtworZListy(wybranyUtwor);
            }
        } else {
            // Jeżeli nie zaznaczono utworu, wyświetl komunikat
            wyswietlKomunikatBledu("Błąd", "Nie zaznaczono utworu do usunięcia.");
        }
    }

    @FXML
    public void zamknijAplikacje(ActionEvent event) {
        // wyskakuje okienko, które pyta, czy na pewno chcesz wyjść
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("EXIT");
        alert.setHeaderText("Aplikacja zostanie wyłączona");
        alert.setContentText("Czy napewno chcesz wyjść?");

        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            // jak kliknie się OK to się wyłącza, a jak Cancel to wraca do poprzedniej sceny
            Stage stage = (Stage) nazwaStudia.getScene().getWindow();
            stage.close();
        }
    }

    private void wyswietlKomunikatBledu(String tytul, String tresc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(tytul);
        alert.setHeaderText(null);
        alert.setContentText(tresc);
        alert.showAndWait();
    }

    private void obsluzBlad(String tytul, Exception e) {
        e.printStackTrace();
        wyswietlKomunikatBledu(tytul, e.getMessage());
    }

    @FXML
    public void wrocDoMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("studioNagran.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
