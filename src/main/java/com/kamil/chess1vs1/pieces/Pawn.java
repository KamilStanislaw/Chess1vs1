package com.kamil.chess1vs1.pieces;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    public String pawnAttackSchema(Piece piece, String destCoordsString) {
        int[] startCoords = piece.getField();
        String attackLeft;
        String attackRight;
        if (piece.color.equals(Color.WHITE)) {
            attackLeft = turnIndexIntoField(new int[]{startCoords[0]-1, startCoords[1]-1});
            attackRight = turnIndexIntoField(new int[]{startCoords[0]-1, startCoords[1]+1});
        } else {
            attackLeft = turnIndexIntoField(new int[]{startCoords[0]+1, startCoords[1]-1});
            attackRight = turnIndexIntoField(new int[]{startCoords[0]+1, startCoords[1]+1});
        }
        if (destCoordsString.equals(attackLeft) || destCoordsString.equals(attackRight)) {
            return destCoordsString; //porównuj¹c koordynaty w tablicach, nie porównujê wartoœci, tylko ca³e tablice;
        } else return turnIndexIntoField(startCoords);
    }

    @Override
    public String possibleMoves(Piece piece, String destCoordsString) {
        int[] startCoords = piece.getField();
        int[] destCoords = turnFieldIntoIndex(destCoordsString);
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
        return turnIndexIntoField(moveTo);
    }



}
