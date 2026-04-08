package org.example.cs151assignment5;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ConfigViewController {

    @FXML
    private ChoiceBox<String> algorithmChoiceBox;

    @FXML
    private TextField roundsField;

    @FXML
    public void initialize() {
        algorithmChoiceBox.getItems().addAll("Random", "ML");
        algorithmChoiceBox.setValue("Random");

        roundsField.setText("20");
    }

    @FXML
    private void onStartGameClick() {
        String algorithm = algorithmChoiceBox.getValue();
        int rounds;

        try {
            rounds = Integer.parseInt(roundsField.getText());
            if (rounds <= 0) {
                rounds = 20;
            }
        } catch (NumberFormatException e) {
            rounds = 20;
        }

        GamePageController.setConfig(algorithm, rounds);

        try {
            HelloApplication.switchScene("game-page.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void onBackClick() throws IOException {
        HelloApplication.switchScene("hello-view.fxml");
    }
}