package kamil.chess1vs1.pieces;

import java.util.Arrays;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public String possibleMoves(Piece piece, String destCoordsString) {
        int[] startCoords = piece.getField();
        int[] destCoords = turnFieldIntoIndex(destCoordsString);
        int[] moveTo = null;
        int[][] possibleAttacks = new int[8][2];
        //correct attack
        if (piece.color.equals(Color.BLACK) || piece.getColor().equals(Color.WHITE)) {
            possibleAttacks[0] = new int[]{startCoords[0]-2, startCoords[1]-1};//NorthWest
            possibleAttacks[1] = new int[] {startCoords[0]-1, startCoords[1]-2};//WestNorth
            possibleAttacks[2] = new int[] {startCoords[0]+1, startCoords[1]-2};//WestSouth
            possibleAttacks[3] = new int[] {startCoords[0]+2, startCoords[1]-1};//SouthWest
            possibleAttacks[4] = new int[] {startCoords[0]+2, startCoords[1]+1};//SouthEast
            possibleAttacks[5] = new int[] {startCoords[0]+1, startCoords[1]+2};//EastSouth
            possibleAttacks[6] = new int[] {startCoords[0]-1, startCoords[1]+2};//EastNorth
            possibleAttacks[7] = new int[] {startCoords[0]-2, startCoords[1]+1};//NorthEast
        }
        for (int[] possibleAttack : possibleAttacks) {
            if (Arrays.equals(possibleAttack,destCoords)) {
                moveTo = destCoords;
                break;
            } else moveTo = startCoords;
        }
        return turnIndexIntoField(moveTo);
    }
}
