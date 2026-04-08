package org.example.cs151assignment5;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class HelloController {
    @FXML
    private void onAboutClick() {
        try {
            HelloApplication.switchScene("about-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onNewGameClick() {
        try {
            HelloApplication.switchScene("config-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onExitClick() {
        Platform.exit();
    }
}