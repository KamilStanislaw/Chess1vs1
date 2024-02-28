package kamil.chess1vs1.logic;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        System.out.println("Szachy 1 vs 1");
        System.out.println("Witajcie w nowej grze!");
        while (true) {
            System.out.println("1. Nowa gra");
            System.out.println("2. Wczytaj grę");
            System.out.println("3. Wyjście");

            Scanner scanner = new Scanner(System.in);
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.println("Witaj w nowerz grze!");
                        gameManager.run();
                    }
                    case 2 -> {
                        System.out.println("Wybierz zapis: ");
                    }
                    case 3 -> System.exit(0);
                }
            } catch (InputMismatchException e) {
                System.err.println("Nieprawidłowa wartość.");
                e.printStackTrace();
            }
        }
    }
}
