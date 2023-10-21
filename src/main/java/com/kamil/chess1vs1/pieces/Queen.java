package com.kamil.chess1vs1.pieces;

public class Queen extends Piece implements MethodsForLongMovingNoJumpingPieces {

    private final Bishop bishopMoves = new Bishop(Color.WHITE);
    private final Rook rookMoves = new Rook(Color.WHITE);
    public Queen(Color color) {
        super(color);
    }

    @Override
    public int[][][] getTableOfMoves(Piece piece, String startCoords, String destCoord) {
        int[][][] tableOfWays = new int[8][][];
        int[][][] rook = rookMoves.getTableOfMoves(rookMoves, turnIndexIntoField(piece.getField()), destCoord);
        int[][][] bishop = bishopMoves.getTableOfMoves(bishopMoves, turnIndexIntoField(piece.getField()), destCoord);
        System.arraycopy(rook, 0, tableOfWays, 0, rook.length);
        System.arraycopy(bishop, 0, tableOfWays, 4, bishop.length);
        return tableOfWays;
    }

    @Override
    public String possibleMoves(Piece piece, String destCoordsString) {
        int[] destCoords = turnFieldIntoIndex(destCoordsString);
        int[] startCoords = piece.field;
        int[] moveTo = null;
        int[][][] possibleAttacks = getTableOfMoves(piece, turnIndexIntoField(piece.getField()), destCoordsString);
        for (int[][] possibleAttack : possibleAttacks) {
            moveTo = setDestFieldIfExist(startCoords, destCoords, possibleAttack, moveTo);
        }
        return turnIndexIntoField(moveTo);
    }

}
