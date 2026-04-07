package org.example.cs153assignment5;
public interface ChoiceAlgorithm {
    Choice makeChoice();
    void recordResult(Choice computerChoice, Choice humanChoice);
    void saveData();
}
