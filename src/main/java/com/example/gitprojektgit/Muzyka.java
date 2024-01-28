package com.example.gitprojektgit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Muzyka {
    private int id;
    private String autor;
    private String utwor;
    private String album;
    private String dodanoPrzez;

    public Muzyka(int id, String autor, String utwor, String album, String dodanoPrzez) {
        this.id = id;
        this.autor = autor;
        this.utwor = utwor;
        this.album = album;
        this.dodanoPrzez = dodanoPrzez;
    }

    public int getId() {
        return id;
    }

    public String getAutor() {
        return autor;
    }

    public String getUtwor() {
        return utwor;
    }

    public String getAlbum() {
        return album;
    }

    public String getDodanoPrzez() {
        return dodanoPrzez;
    }

    public static List<Muzyka> pobierzDaneZBazy(String username) {
        List<Muzyka> listaMuzyki = new ArrayList<>();

        String query = "SELECT m.ID, m.Autor, m.Utwor, m.Album, l.Login AS DodanoPrzez " +
                "FROM muzyka m " +
                "JOIN logowanie l ON m.userID = l.ID " +
                "WHERE l.Login = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String autor = resultSet.getString("Autor");
                String utwor = resultSet.getString("Utwor");
                String album = resultSet.getString("Album");
                String dodanoPrzez = resultSet.getString("DodanoPrzez");

                Muzyka muzyka = new Muzyka(id, autor, utwor, album, dodanoPrzez);
                listaMuzyki.add(muzyka);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Obsługa błędu pobierania danych
        }

        return listaMuzyki;
    }

    // Dodaj nowy utwór do bazy danych
    public boolean dodajDoBazy() {
        String query = "INSERT INTO muzyka (Autor, Utwor, Album, userID) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, this.autor);
            preparedStatement.setString(2, this.utwor);
            preparedStatement.setString(3, this.album);
            preparedStatement.setString(4, this.dodanoPrzez);

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Database operation failed");
            e.printStackTrace();
            return false;
        }
    }

    // Usuń utwór z bazy danych
    public void usunZBazy() {
        String query = "DELETE FROM muzyka WHERE ID = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, this.id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Obsługa błędu usuwania z bazy danych
        }
    }
}
