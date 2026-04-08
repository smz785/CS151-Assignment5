package org.example.cs151assignment5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;


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

    @FXML
    private TableView<RoundStat> roundsTable;

    @FXML
    private TableColumn<RoundStat, Number> roundColumn;

    @FXML
    private TableColumn<RoundStat, Number> humanWinsColumn;

    @FXML
    private Button newGameButton;

    @FXML
    private TableColumn<RoundStat, Number> computerWinsColumn;

    @FXML
    private TableColumn<RoundStat, Number> tiesColumn;

    private final ObservableList<RoundStat> roundStats = FXCollections.observableArrayList();

    private int humanWins = 0;
    private int computerWins = 0;
    private int ties = 0;

    private ChoiceAlgorithm algorithm;
    private Game game;
    private Player computer;

    private static String selectedAlgorithm = "Random";
    private static int selectedRounds = 20;

    public static void setConfig(String algorithm, int rounds) {
        selectedAlgorithm = algorithm;
        selectedRounds = rounds;
    }

    @FXML
    private void initialize() {
        roundColumn.setCellValueFactory(cellData -> cellData.getValue().roundProperty());
        humanWinsColumn.setCellValueFactory(cellData -> cellData.getValue().humanWinsProperty());
        computerWinsColumn.setCellValueFactory(cellData -> cellData.getValue().computerWinsProperty());
        tiesColumn.setCellValueFactory(cellData -> cellData.getValue().tiesProperty());

        roundsTable.setItems(roundStats);

        resetGame();
    }

    @FXML
    private void onBackClick(ActionEvent actionEvent) {
        try {
            HelloApplication.switchScene("hello-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onNewGameClick(ActionEvent actionEvent) {
        try {
            HelloApplication.switchScene("config-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resetGame() {
        String algCode = selectedAlgorithm.equals("ML") ? "2" : "1";

        humanWins = 0;
        computerWins = 0;
        ties = 0;
        roundStats.clear();

        if (algorithm != null) {
            algorithmLabel.textProperty().unbind();
            predictedChoiceLabel.textProperty().unbind();
        }

        algorithm = FactoryChoiceAlgorithm.create(algCode);
        computer = new ComputerPlayer(algorithm);
        game = new Game(null, computer, new RulesEngine(), selectedRounds);

        algorithmLabel.textProperty().bind(algorithm.getAlgorithmChoiceProperty());
        predictedChoiceLabel.textProperty().bind(algorithm.getPredictedHumanChoiceProperty());

        totalRoundsLabel.setText(String.valueOf(game.getTotalRounds()));
        roundLabel.setText("0");
        roundWinnerLabel.setText("-");
        gameWinnerLabel.setText("-");
        humanChoiceLabel.setText("-");
        computerChoiceLabel.setText("-");
        newGameButton.setDisable(true);
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

    private void playRound(Choice humanChoice) {
        if (game.isGameOver()) {
            return;
        }

        Result result = game.playRound(humanChoice);

        humanChoiceLabel.setText(formatChoice(humanChoice));
        computerChoiceLabel.setText(formatChoice(game.getLastComputerChoice()));
        roundLabel.setText(String.valueOf(game.getCurrentRound()));
        roundWinnerLabel.setText(formatResult(result));

        if (result == Result.HUMAN) {
            humanWins++;
        } else if (result == Result.COMPUTER) {
            computerWins++;
        } else {
            ties++;
        }

        roundStats.add(new RoundStat(
                game.getCurrentRound(),
                humanWins,
                computerWins,
                ties
        ));

        if (game.isGameOver()) {
            gameWinnerLabel.setText(formatResult(game.getFinalWinner()));
            newGameButton.setDisable(false);
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
