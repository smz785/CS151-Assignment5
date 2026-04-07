package org.example.cs153assignment5;

public interface Player {
    Choice getChoice();
    void recordResult(Choice computerChoice, Choice humanChoice);
    void saveData();
}