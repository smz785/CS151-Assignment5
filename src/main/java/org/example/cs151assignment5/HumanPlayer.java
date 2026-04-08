package org.example.cs151assignment5;

import java.util.Scanner;

/**
 * Implements Coding To Interface Design Principle
 * @ asmita
 */
public class HumanPlayer implements Player{

    private String name;
    private Scanner scanner;

    /**
     * To Do: Implement the logic
     * @return
     */
    
    @Override
    public Choice getChoice() {

        while (true) {
            System.out.print("Choose (1=rock, 2=paper, 3=scissors): ");

            if (!scanner.hasNextLine()) {
                return null;
            }

            String input = scanner.nextLine().trim();
            Choice move = null;

            if (input.equals("1")) {
                move = Choice.ROCK;
            } 
            else if (input.equals("2")) {
                move = Choice.PAPER;
            } 
            else if (input.equals("3")) {
                move = Choice.SCISSORS;
            } 
            else {
                System.out.println("Invalid input. Please enter 1, 2, or 3.");
                continue;
            }

            //return move.name().toLowerCase();
            return move;
        }
    }

    /**
     *
     * @param name
     */
    public HumanPlayer(String name){
        this.name = name;
        this.scanner = new Scanner(System.in);
    }

    public HumanPlayer(String name, Scanner scanner){
        this.name = name;
        this.scanner = scanner;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

@Override
public void recordResult(Choice computerChoice, Choice humanChoice) {
}

@Override
public void saveData() {
}
}