package org.example.cs151assignment5;

public class ComputerPlayer implements Player {

    private final ChoiceAlgorithm algorithm;

    public ComputerPlayer(ChoiceAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public Choice getChoice() {
        return algorithm.makeChoice();
    }

    @Override
    public void recordResult(Choice computerChoice, Choice humanChoice) {
        algorithm.recordResult(computerChoice, humanChoice);
    }

    @Override
    public void saveData() {
        algorithm.saveData();
    }
}