package org.example.cs153assignment5;

import java.util.Random;

public class RandomAlgorithm implements ChoiceAlgorithm
{
    private final Random random = new Random();
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
}
