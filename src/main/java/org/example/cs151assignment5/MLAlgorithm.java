package org.example.cs151assignment5;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class MLAlgorithm implements ChoiceAlgorithm {

    private static final int N = 6;
    private final StringProperty mlAlg = new SimpleStringProperty("ML");
    private final StringProperty predictedHumanChoice = new SimpleStringProperty("N/A");

    // key = last N alternating tokens
    // value = counts of next human move [rock, paper, scissors]
    private Map<String, int[]> patternMap;

    private LinkedList<String> history;
    private Random random;
    private MLDataStore dataStore;


    public MLAlgorithm() {
        history = new LinkedList<>();
        random = new Random();
        dataStore = new MLDataStore("ml_data.txt");
        patternMap = dataStore.load();
    }

    @Override
    public Choice makeChoice() {
        if (history.size() < N) {
            predictedHumanChoice.set("N/A");
            return getRandomChoice();
        }

        String key = buildKey();
        int[] counts = patternMap.get(key);

        if (counts == null) {
            predictedHumanChoice.set("N/A");
            return getRandomChoice();
        }

        int predictedIndex = getMaxIndex(counts);
        Choice predictedHuman = indexToChoice(predictedIndex);
        predictedHumanChoice.set(String.valueOf(predictedHuman));

        return getWinningMove(predictedHuman);
    }

    @Override
    public void recordResult(Choice computerChoice, Choice humanChoice) {
        if (history.size() == N) {
            String key = buildKey();
            patternMap.putIfAbsent(key, new int[3]);
            patternMap.get(key)[choiceToIndex(humanChoice)]++;
        }

        history.add("H:" + choiceToChar(humanChoice));
        history.add("C:" + choiceToChar(computerChoice));

        while (history.size() > N) {
            history.removeFirst();
        }
    }

    @Override
    public void saveData() {
        dataStore.save(patternMap);
    }

    private String buildKey() {
        return String.join("|", history);
    }

    private Choice getRandomChoice() {
        int num = random.nextInt(3);
        return indexToChoice(num);
    }

    private int getMaxIndex(int[] arr) {
        int max = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[max]) {
                max = i;
            }
        }
        return max;
    }

    private int choiceToIndex(Choice c) {
        if (c == Choice.ROCK) return 0;
        if (c == Choice.PAPER) return 1;
        return 2;
    }

    private Choice indexToChoice(int i) {
        if (i == 0) return Choice.ROCK;
        if (i == 1) return Choice.PAPER;
        return Choice.SCISSORS;
    }

    private char choiceToChar(Choice c) {
        if (c == Choice.ROCK) return 'R';
        if (c == Choice.PAPER) return 'P';
        return 'S';
    }

    private Choice getWinningMove(Choice humanMove) {
        if (humanMove == Choice.ROCK) return Choice.PAPER;
        if (humanMove == Choice.PAPER) return Choice.SCISSORS;
        return Choice.ROCK;
    }

    @Override
    public StringProperty getAlgorithmChoiceProperty(){
        return mlAlg;
    }

    @Override
    public StringProperty getPredictedHumanChoiceProperty(){
        return  predictedHumanChoice;
    }
}