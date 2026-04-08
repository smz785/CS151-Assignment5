package org.example.cs151assignment5;

import javafx.application.Platform;
import javafx.fxml.FXML;
import java.io.IOException;

public class AboutController {
    @FXML
    private void onExitClick() {
        Platform.exit();
    }

    @FXML
    private void onBackClick() {
        try {
            HelloApplication.switchScene("hello-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}