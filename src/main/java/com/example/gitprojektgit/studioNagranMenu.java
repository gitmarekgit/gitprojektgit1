package com.example.gitprojektgit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static com.example.gitprojektgit.zmianaTrybu.getTryb;

public class studioNagranMenu {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane scenePane;
    private static zmianaTrybu zmienTryb = new zmianaTrybu();
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
    }

    public void ustawTrybCiemny() throws IOException{
        zmienTryb.trybCiemny(scenePane);
    }


    public void listaUtworow(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("studioNagranLista.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void ustawienia(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ustawienia.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void zamknijAplikacje(ActionEvent event){
        //wyskakuje okienko ktore pyta czy napewno chcesz wyjsc
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("EXIT");
        alert.setHeaderText("Aplikacja zostanie wyłączona");
        alert.setContentText("Czy napewno chcesz wyjść?");

        if(alert.showAndWait().get()== ButtonType.OK){
            //jak kliknie sie ok to sie wylacza a jak cancel to wraca do poprzedniej sceny
            stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private Label nazwaStudia;

    public void zmienNazweStudia() throws IOException{
        //zmiana nazwy studia
        String nowaNazwaStudia = zmianaNazwyStudia.pokazOkno();
        nazwaStudia.setText(nowaNazwaStudia);
    }

}
