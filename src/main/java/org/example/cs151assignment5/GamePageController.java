package org.example.cs151assignment5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class GamePageController {
    @FXML
    private Label algorithmLabel;
    @FXML
    private Label roundLabel;
    @FXML
    private Label roundWinnerLabel;
    @FXML
    private Label totalRoundsLabel;
    @FXML
    private Label gameWinnerLabel;
    @FXML
    private Label humanChoiceLabel;
    @FXML
    private Label predictedChoiceLabel;
    @FXML
    private Label computerChoiceLabel;

    private ChoiceAlgorithm algorithm;
    private Game game;
    private Player computer;

    @FXML
    private void initialize(){
        algorithm = FactoryChoiceAlgorithm.create("1");
        computer = new ComputerPlayer(algorithm);
        game = new Game(null, computer, new RulesEngine(), 20);

        algorithmLabel.textProperty().bind(algorithm.getAlgorithmChoiceProperty());
        predictedChoiceLabel.textProperty().bind(algorithm.getPredictedHumanChoiceProperty());
        roundLabel.setText("0");
        totalRoundsLabel.setText(String.valueOf(game.getTotalRounds()));
        roundWinnerLabel.setText("-");
        gameWinnerLabel.setText("-");
        humanChoiceLabel.setText("-");
       // predictedChoiceLabel.setText("N/A");
    }

    @FXML
    private void onScissorsClick(ActionEvent actionEvent) {
        playRound(Choice.SCISSORS);
    }

    @FXML
    private void onPaperClick(ActionEvent actionEvent) {
        playRound(Choice.PAPER);
    }

    @FXML
    private void onRockClick(ActionEvent actionEvent) {
        playRound(Choice.ROCK);
    }

    private void playRound(Choice humanChoice){
        if(game.isGameOver()){
            return;
        }

        Result result = game.playRound(humanChoice);
        humanChoiceLabel.setText(formatChoice(humanChoice));
        computerChoiceLabel.setText(formatChoice(game.getLastComputerChoice()));
        roundLabel.setText(String.valueOf(game.getCurrentRound()));
        roundWinnerLabel.setText(formatResult(result));

        if(game.isGameOver()){
            gameWinnerLabel.setText(formatResult(game.getFinalWinner()));
            game.saveGameData();
        }
    }

    private String formatChoice(Choice choice){
        if (choice == null){
            return "-";
        }
        return switch(choice){
            case SCISSORS -> "Scissors";
            case ROCK -> "Rock";
            case PAPER -> "Paper";
        };
    }

    private String formatResult(Result result){
        if(result == null){
            return "-";
        }
        return switch(result){
            case HUMAN -> "Human";
            case COMPUTER -> "Computer";
            case DRAW -> "Draw";
        };
    }
}
