package com.kamil.chess1vs1.pieces;

import java.util.Arrays;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    public int[] pawnAttackSchema(Piece piece, int[] startCoords, int[] destCoords) {
        int[] attackLeft;
        int[] attackRight;
        if (piece.color.equals(Color.WHITE)) {
            attackLeft = new int[]{startCoords[0]-1, startCoords[1]-1};
            attackRight = new int[]{startCoords[0]-1, startCoords[1]+1};
        } else {
            attackLeft = new int[]{startCoords[0]+1, startCoords[1]-1};
            attackRight = new int[]{startCoords[0]+1, startCoords[1]+1};
        }
        if (Arrays.equals(destCoords, attackLeft) || Arrays.equals(destCoords, attackRight)) {
            return destCoords; //porównuj¹c koordynaty w tablicach, nie porównujê wartoœci, tylko ca³e tablice;
        } else return startCoords;
    }

    @Override
    public int[] possibleMoves(Piece piece, int[] startCoords, int[] destCoords) {
        int[] moveTo;
        if (piece.color.equals(Color.WHITE)) {
            if (startCoords[0] == 6 && destCoords[0] == startCoords[0] - 2) {
                moveTo = new int[]{startCoords[0] - 2, startCoords[1]};
            } else if (startCoords[0] == 6 && destCoords[0] == startCoords[0] - 1){
                moveTo = new int[]{startCoords[0] - 1, startCoords[1]};
            } else {
                moveTo = new int[]{startCoords[0] - 1, startCoords[1]};
            }
        } else { //BLACK
            if (startCoords[0] == 1 && destCoords[0] == startCoords[0] + 2) {
                moveTo = new int[]{startCoords[0] + 2, startCoords[1]};
            } else if (startCoords[0] == 6 && destCoords[0] == startCoords[0] + 1){
                moveTo = new int[]{startCoords[0] + 1, startCoords[1]};
            } else {
                moveTo = new int[]{startCoords[0] + 1, startCoords[1]};
            }
        }
        return moveTo;
    }



}
