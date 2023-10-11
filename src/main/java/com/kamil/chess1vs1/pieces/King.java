package com.kamil.chess1vs1.pieces;

import java.util.Arrays;

public class King extends Piece {
    public King(Color color) {
        super(color);
    }


    @Override
    public int[] possibleMoves(Piece piece, int[] startCoords, int[] destCoords) {
        int[] moveTo = null;
        int[][] possibleAttacks = new int[8][2];
        //correct attack
        if (piece.color.equals(Color.BLACK) || piece.getColor().equals(Color.WHITE)) {
            possibleAttacks[0] = new int[]{startCoords[0]-1, startCoords[1]};//Up
            possibleAttacks[1] = new int[] {startCoords[0]+1, startCoords[1]};//Down
            possibleAttacks[2] = new int[] {startCoords[0], startCoords[1]-1};//Left
            possibleAttacks[3] = new int[] {startCoords[0], startCoords[1]+1};//Right
            possibleAttacks[4] = new int[] {startCoords[0]-1, startCoords[1]-1};//NorthWest
            possibleAttacks[5] = new int[] {startCoords[0]-1, startCoords[1]+1};//NorthEast
            possibleAttacks[6] = new int[] {startCoords[0]+1, startCoords[1]-1};//SouthWest
            possibleAttacks[7] = new int[] {startCoords[0]+1, startCoords[1]+1};//SouthEast
        }
        for (int[] possibleAttack : possibleAttacks) {
            if (Arrays.equals(possibleAttack,destCoords)) {
                moveTo = destCoords;
                break;
            } else moveTo = startCoords;
        }
        return moveTo;
    }
}
