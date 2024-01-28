package com.example.gitprojektgit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleStringProperty;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Lista {

    private ObservableList<Muzyka> listaUtworow;

    public Lista() {
        this.listaUtworow = FXCollections.observableArrayList();
        pobierzUtworyZBazy();
    }

    private void pobierzUtworyZBazy() {
        List<Muzyka> listaMuzyki = Muzyka.pobierzDaneZBazy(Session.getLoggedInUserName());
        listaUtworow.addAll(listaMuzyki);
    }

    public void inicjalizujTabele(TableView<Muzyka> tabelaMuzyki) {
        // Usuń wszystkie istniejące kolumny przed dodaniem nowych
        tabelaMuzyki.getColumns().clear();

        TableColumn<Muzyka, String> autorColumn = new TableColumn<>("Autor");
        autorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAutor()));

        TableColumn<Muzyka, String> tytulColumn = new TableColumn<>("Tytuł");
        tytulColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUtwor()));

        TableColumn<Muzyka, String> albumColumn = new TableColumn<>("Album");
        albumColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlbum()));

        TableColumn<Muzyka, String> dodanoPrzezColumn = new TableColumn<>("Dodano przez");
        dodanoPrzezColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDodanoPrzez()));

        tabelaMuzyki.setItems(listaUtworow);
        tabelaMuzyki.getColumns().addAll(autorColumn, tytulColumn, albumColumn, dodanoPrzezColumn);
    }

    public ObservableList<Muzyka> getListaUtworow() {
        return listaUtworow;
    }

    // Dodaj nowy utwór do listy
    public void dodajUtworDoListy(String autor, String utwor, String album, String dodanoPrzez) {
        Muzyka nowyUtwor = new Muzyka(0, autor, utwor, album, dodanoPrzez);
        nowyUtwor.dodajDoBazy();  // Dodaj najpierw do bazy danych
        listaUtworow.add(nowyUtwor);
    }

    // Usuń utwór z listy
    public void usunUtworZListy(Muzyka utwor) {
        listaUtworow.remove(utwor);
        utwor.usunZBazy();
    }
}
