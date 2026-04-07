package org.example.cs153assignment5;

public class Game {
    private final Player human;
    private final Player computer;
    private final RulesEngine rulesEngine;

    private int humanScore;
    private int computerScore;
    private int draws;
    private int currentRound;

    private Choice lastHumanChoice;
    private Choice lastComputerChoice;
    private Result lastWinner;

    private final int totalRounds;

    public Game(Player human, Player computer, RulesEngine rulesEngine) {
        this(human, computer, rulesEngine, 20);
    }

    public Game(Player human, Player computer, RulesEngine rulesEngine, int totalRounds) {
        this.human = human;
        this.computer = computer;
        this.rulesEngine = rulesEngine;
        this.totalRounds = totalRounds;
        this.currentRound = 0;
    }

    public Result playRound(Choice humanChoice) {
        if (currentRound >= totalRounds) {
            return null;
        }

        currentRound++;

        lastHumanChoice = humanChoice;
        lastComputerChoice = computer.getChoice();
        lastWinner = rulesEngine.determineWinner(lastHumanChoice, lastComputerChoice);

        switch (lastWinner) {
            case HUMAN:
                humanScore++;
                break;
            case COMPUTER:
                computerScore++;
                break;
            case DRAW:
                draws++;
                break;
        }

        computer.recordResult(lastComputerChoice, lastHumanChoice);
        return lastWinner;
    }

    public void saveGameData() {
        computer.saveData();
    }

    public boolean isGameOver() {
        return currentRound >= totalRounds;
    }

    public Result getFinalWinner() {
        if (humanScore > computerScore) {
            return Result.HUMAN;
        } else if (computerScore > humanScore) {
            return Result.COMPUTER;
        } else {
            return Result.DRAW;
        }
    }

    public int getHumanScore() {
        return humanScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public int getDraws() {
        return draws;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int getTotalRounds() {
        return totalRounds;
    }

    public Choice getLastHumanChoice() {
        return lastHumanChoice;
    }

    public Choice getLastComputerChoice() {
        return lastComputerChoice;
    }

    public Result getLastWinner() {
        return lastWinner;
    }
}