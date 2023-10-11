package com.kamil.chess1vs1.pieces;

public class Queen extends Piece implements MethodsForLongMovingNoJumpingPieces {

    private final Bishop bishopMoves = new Bishop(Color.WHITE);
    private final Rook rookMoves = new Rook(Color.WHITE);
    public Queen(Color color) {
        super(color);
    }

    @Override
    public int[][][] getTableOfMoves(Piece piece, int[] oldCoords, int[] destCoord) {
        int[][][] tableOfWays = new int[8][][];
        int[][][] rook = rookMoves.getTableOfMoves(rookMoves, oldCoords, destCoord);
        int[][][] bishop = bishopMoves.getTableOfMoves(bishopMoves, oldCoords, destCoord);
        System.arraycopy(rook, 0, tableOfWays, 0, rook.length);
        System.arraycopy(bishop, 0, tableOfWays, 4, bishop.length);
        return tableOfWays;
    }

    @Override
    public int[] possibleMoves(Piece piece, int[] startCoords, int[] destCoords) {
        int[] moveTo = null;
        int[][][] possibleAttacks = getTableOfMoves(piece, startCoords, destCoords);
        for (int[][] possibleAttack : possibleAttacks) {
            moveTo = setDestFieldIfNotEmpty(startCoords, destCoords, possibleAttack, moveTo);
        }
        return moveTo;
    }

}
