package org.example.cs151assignment5;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;

public class RoundStat {
    private final IntegerProperty round;
    private final IntegerProperty humanWins;
    private final IntegerProperty computerWins;
    private final IntegerProperty ties;

    public RoundStat(int round, int humanWins, int computerWins, int ties) {
        this.round = new SimpleIntegerProperty(round);
        this.humanWins = new SimpleIntegerProperty(humanWins);
        this.computerWins = new SimpleIntegerProperty(computerWins);
        this.ties = new SimpleIntegerProperty(ties);
    }

    public IntegerProperty roundProperty() {
        return round;
    }

    public IntegerProperty humanWinsProperty() {
        return humanWins;
    }

    public IntegerProperty computerWinsProperty() {
        return computerWins;
    }

    public IntegerProperty tiesProperty() {
        return ties;
    }

    public int getRound() {
        return round.get();
    }

    public int getHumanWins() {
        return humanWins.get();
    }

    public int getComputerWins() {
        return computerWins.get();
    }

    public int getTies() {
        return ties.get();
    }
}