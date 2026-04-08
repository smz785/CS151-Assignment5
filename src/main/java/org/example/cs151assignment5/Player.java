package org.example.cs151assignment5;

public interface Player {
    Choice getChoice();
    void recordResult(Choice computerChoice, Choice humanChoice);
    void saveData();
}