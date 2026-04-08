package org.example.cs151assignment5;

/**
 * RulesEngine determines the winner of a Rock-Paper-Scissors round.
 *
 * @author Mahika
 */
public class RulesEngine implements IRulesEngine {

    @Override
    public Result determineWinner(Choice humanChoice, Choice computerChoice) {
        if (humanChoice == null || computerChoice == null) {
            throw new IllegalArgumentException("Choices cannot be null.");
        }

        if (humanChoice == computerChoice) {
            return Result.DRAW;
        }

        if ((humanChoice == Choice.ROCK && computerChoice == Choice.SCISSORS) ||
            (humanChoice == Choice.PAPER && computerChoice == Choice.ROCK) ||
            (humanChoice == Choice.SCISSORS && computerChoice == Choice.PAPER)) {
            return Result.HUMAN;
        }

        return Result.COMPUTER;
    }
}