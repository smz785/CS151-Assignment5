package org.example.cs151assignment5;

import javafx.beans.property.StringProperty;

public interface ChoiceAlgorithm {
    Choice makeChoice();
    void recordResult(Choice computerChoice, Choice humanChoice);
    void saveData();
    StringProperty getAlgorithmChoiceProperty();
    StringProperty getPredictedHumanChoiceProperty();
}
