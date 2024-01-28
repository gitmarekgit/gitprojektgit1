package com.example.gitprojektgit;


import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class zmianaTrybu {
    @FXML
    public static double tryb = 0;
    public static double getTryb(){
        return tryb;
    }

    public void trybJasny(AnchorPane scenePane) throws IOException {
        scenePane.getStylesheets().add("file:/C:/Users/marek/Desktop/gitprojektgit/gitprojektgit1/src/styles/light_mode.css");
        scenePane.getStylesheets().remove("file:/C:/Users/marek/Desktop/gitprojektgit/gitprojektgit1/src/styles/dark_mode.css");
         tryb = 0;
    }

    public void trybCiemny(AnchorPane scenePane) throws IOException{
        scenePane.getStylesheets().add("file:/C:/Users/marek/Desktop/gitprojektgit/gitprojektgit1/src/styles/dark_mode.css");
        scenePane.getStylesheets().remove("file:/C:/Users/marek/Desktop/gitprojektgit/gitprojektgit1/src/styles/light_mode.css");
         tryb = 1;
    }
}
