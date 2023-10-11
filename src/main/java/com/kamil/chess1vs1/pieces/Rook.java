package com.kamil.chess1vs1.pieces;

public class Rook extends Piece implements MethodsForLongMovingNoJumpingPieces {
    public Rook(Color color) {
        super(color);
    }
    public int[][][] getTableOfMoves(Piece piece, int[] oldCoords, int[] destCoord) {
        int[][][] tableOfWays = new int[4][][];
        tableOfWays[0] = ((Rook) piece).checkWayToSouth(piece, oldCoords, destCoord);
        tableOfWays[1] = ((Rook) piece).checkWayToNorth(piece, oldCoords, destCoord);
        tableOfWays[2] = ((Rook) piece).checkWayToWest(piece, oldCoords, destCoord);
        tableOfWays[3] = ((Rook) piece).checkWayToEast(piece, oldCoords, destCoord);
        return tableOfWays;
    }

    int[][] checkWayToSouth(Piece piece, int[] startCoords, int[] destCoords) {
        int[][] possibleAttacks = new int[7][2];
        if (piece.color.equals(Color.BLACK) || piece.getColor().equals(Color.WHITE)) {
            possibleAttacks[0] = new int[]{startCoords[0]+1, startCoords[1]};
            possibleAttacks[1] = new int[] {startCoords[0]+2, startCoords[1]};
            possibleAttacks[2] = new int[] {startCoords[0]+3, startCoords[1]};
            possibleAttacks[3] = new int[] {startCoords[0]+4, startCoords[1]};
            possibleAttacks[4] = new int[] {startCoords[0]+5, startCoords[1]};
            possibleAttacks[5] = new int[] {startCoords[0]+6, startCoords[1]};
            possibleAttacks[6] = new int[] {startCoords[0]+7, startCoords[1]};
        }
        return pathFieldsCoordsReturn(destCoords, possibleAttacks);
    }

    int[][] checkWayToNorth(Piece piece, int[] startCoords, int[] destCoords) {
        int[][] possibleAttacks = new int[7][2];
        if (piece.color.equals(Color.BLACK) || piece.getColor().equals(Color.WHITE)) {
            possibleAttacks[0] = new int[]{startCoords[0]-1, startCoords[1]};
            possibleAttacks[1] = new int[] {startCoords[0]-2, startCoords[1]};
            possibleAttacks[2] = new int[] {startCoords[0]-3, startCoords[1]};
            possibleAttacks[3] = new int[] {startCoords[0]-4, startCoords[1]};
            possibleAttacks[4] = new int[] {startCoords[0]-5, startCoords[1]};
            possibleAttacks[5] = new int[] {startCoords[0]-6, startCoords[1]};
            possibleAttacks[6] = new int[] {startCoords[0]-7, startCoords[1]};
        }
        return pathFieldsCoordsReturn(destCoords, possibleAttacks);
    }

    int[][] checkWayToWest(Piece piece, int[] startCoords, int[] destCoords) {
        int[][] possibleAttacks = new int[7][2];
        if (piece.color.equals(Color.BLACK) || piece.getColor().equals(Color.WHITE)) {
            possibleAttacks[0] = new int[]{startCoords[0], startCoords[1]-1};
            possibleAttacks[1] = new int[] {startCoords[0], startCoords[1]-2};
            possibleAttacks[2] = new int[] {startCoords[0], startCoords[1]-3};
            possibleAttacks[3] = new int[] {startCoords[0], startCoords[1]-4};
            possibleAttacks[4] = new int[] {startCoords[0], startCoords[1]-5};
            possibleAttacks[5] = new int[] {startCoords[0], startCoords[1]-6};
            possibleAttacks[6] = new int[] {startCoords[0], startCoords[1]-7};
        }
        return pathFieldsCoordsReturn(destCoords, possibleAttacks);
    }

    int[][] checkWayToEast(Piece piece, int[] startCoords, int[] destCoords) {
        int[][] possibleAttacks = new int[7][2];
        if (piece.color.equals(Color.BLACK) || piece.getColor().equals(Color.WHITE)) {
            possibleAttacks[0] = new int[]{startCoords[0], startCoords[1]+1};
            possibleAttacks[1] = new int[] {startCoords[0], startCoords[1]+2};
            possibleAttacks[2] = new int[] {startCoords[0], startCoords[1]+3};
            possibleAttacks[3] = new int[] {startCoords[0], startCoords[1]+4};
            possibleAttacks[4] = new int[] {startCoords[0], startCoords[1]+5};
            possibleAttacks[5] = new int[] {startCoords[0], startCoords[1]+6};
            possibleAttacks[6] = new int[] {startCoords[0], startCoords[1]+7};
        }
        return pathFieldsCoordsReturn(destCoords, possibleAttacks);
    }


    @Override
    public int[] possibleMoves(Piece piece, int[] startCoords, int[] destCoords) {
        int[] moveTo = null;
        int[][] possibleAttacksSouth = checkWayToSouth(piece, startCoords, destCoords);
        int[][] possibleAttacksNorth = checkWayToNorth(piece, startCoords, destCoords);
        int[][] possibleAttacksWest = checkWayToWest(piece, startCoords, destCoords);
        int[][] possibleAttacksEast = checkWayToEast(piece, startCoords, destCoords);
        moveTo = setDestFieldIfNotEmpty(startCoords, destCoords, possibleAttacksSouth, moveTo);
        moveTo = setDestFieldIfNotEmpty(startCoords, destCoords, possibleAttacksNorth, moveTo);
        moveTo = setDestFieldIfNotEmpty(startCoords, destCoords, possibleAttacksWest, moveTo);
        moveTo = setDestFieldIfNotEmpty(startCoords, destCoords, possibleAttacksEast, moveTo);

        return moveTo;
    }
}
