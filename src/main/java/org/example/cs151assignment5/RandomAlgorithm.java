package org.example.cs151assignment5;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Random;

public class RandomAlgorithm implements ChoiceAlgorithm
{
    private final Random random = new Random();
    private final StringProperty randAlg = new SimpleStringProperty("Random");
    private final StringProperty predictedHumanChoice = new SimpleStringProperty("N/A");
    @Override
    public Choice makeChoice(){
        Choice choices[] = Choice.values();
        return choices[random.nextInt(choices.length)];
    }

    @Override
    public void recordResult(Choice computerChoice, Choice humanChoice) {

    }
    @Override
    public void saveData() {}

    @Override
    public StringProperty getAlgorithmChoiceProperty(){
        return randAlg;
    }

    @Override
    public StringProperty getPredictedHumanChoiceProperty(){
        return predictedHumanChoice;
    }
}
