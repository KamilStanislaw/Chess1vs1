package com.kamil.chess1vs1.pieces;

public interface TurningCoordinates {
    default int[] turnFieldIntoIndex(String coords) {
        String lower = coords.toLowerCase();
        char[] field = lower.toCharArray();
        char letter = field[0];
        int number = Integer.parseInt(String.valueOf(field[1]));
        int coordOfLetter = letter - 97;
        int coordOfNumber = 8 - number;
        return new int[]{coordOfNumber, coordOfLetter};
    }

    default String turnIndexIntoField(int[] field) {
        int numb = (- 8 + field[0]) * -1;
        char letter = (char) (field[1] + 97);
        return String.join("", String.valueOf(letter), String.valueOf(numb));
    }
}
