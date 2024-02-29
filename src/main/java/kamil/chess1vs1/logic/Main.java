package kamil.chess1vs1.logic;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        System.out.println("Chess 1 vs 1");
        System.out.println("Welcome!!!");
        while (true) {
            System.out.println("1. New game");
            System.out.println("2. Load game");
            System.out.println("3. Exit game");

            Scanner scanner = new Scanner(System.in);
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.println();
                        System.out.println("New Game is starting...");
                        scanner.nextLine();
                        gameManager.run();
                    }
                    case 2 -> {
                        System.out.println("Select saved game: ");
                        //load file
                    }
                    case 3 -> System.exit(0);
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid value.");
                e.printStackTrace();
            }
        }
    }
}
