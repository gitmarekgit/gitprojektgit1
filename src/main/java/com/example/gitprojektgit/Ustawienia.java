package com.example.gitprojektgit;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static com.example.gitprojektgit.zmianaTrybu.getTryb;

public class Ustawienia{
    @FXML
    private AnchorPane scenePane;
    private final zmianaTrybu zmienTryb = new zmianaTrybu();
    double tryb = getTryb();
    public void initialize() throws IOException {
        if(tryb == 0){
            ustawTrybJasny();
        }
        else{
            ustawTrybCiemny();
        }
    }

    public void ustawTrybJasny() throws IOException {
        zmienTryb.trybJasny(scenePane);
        System.out.println(tryb);
    }

    public void ustawTrybCiemny() throws IOException{
        zmienTryb.trybCiemny(scenePane);
        System.out.println(tryb);
    }


    @FXML
    public void zmienNazweStudia(){
        try {
            // zmiana nazwy studia
           zmianaNazwyStudia.pokazOkno();

        } catch (IOException e) {
            obsluzBlad("Błąd podczas zmiany nazwy studia.", e);
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
