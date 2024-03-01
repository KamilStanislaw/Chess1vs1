package kamil.chess1vs1.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final String SAVED_GAMES_PATHS = "src/main/resources/saved/savedGamesList.txt";
    static Set<String> savedGamesPaths = new LinkedHashSet<>();
    public static void main(String[] args) {
        GameManager gameManager = new GameManager(savedGamesPaths);
        System.out.println("Welcome in");
        System.out.println("Chess 1 vs 1");
        while (true) {
            System.out.println();
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
                        gameManager.run(choice, "");
                    }
                    case 2 -> {
                        Scanner scanner2 = new Scanner(System.in);
                        System.out.println();
                        System.out.println("Select saved game name:");
                        loadListOfGames(savedGamesPaths);
                        printNamesOfSavedGames();
                        String saveName = scanner2.nextLine();
                        gameManager.run(choice, saveName);
                    }
                    case 3 -> System.exit(0);
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid value.");
            }
        }
    }

    private static void loadListOfGames(Set<String> savedGamesPaths) {
        try {
            Scanner scanner = new Scanner(new File(SAVED_GAMES_PATHS));
            while (scanner.hasNextLine()) {
                savedGamesPaths.add(scanner.nextLine());
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("There is a problem with printing list of saved games.");
            System.out.println("You could have no saved games, or they are hidden.");
            System.out.println("Press any button and enter to load new game, or to write name of save, if you remember.");
            System.err.println("ERROR");
        }
    }

    private static void printNamesOfSavedGames() {
        savedGamesPaths
                .forEach(s -> {
                    String[] split = s.split("/");
                    String[] file = split[4].split("\\.");
                    String realname = file[0];
                    System.out.println(realname);
                });
    }
}
