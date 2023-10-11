package com.kamil.chess1vs1.pieces;

public class Bishop extends Piece implements MethodsForLongMovingNoJumpingPieces {
    public Bishop(Color color) {
        super(color);
    }
    public int[][][] getTableOfMoves(Piece piece, int[] oldCoords, int[] destCoord) {
        int[][][] tableOfWays = new int[4][][];
        tableOfWays[0] = ((Bishop) piece).checkWayToSouthEast(piece, oldCoords, destCoord);
        tableOfWays[1] = ((Bishop) piece).checkWayToNorthEast(piece, oldCoords, destCoord);
        tableOfWays[2] = ((Bishop) piece).checkWayToNorthWest(piece, oldCoords, destCoord);
        tableOfWays[3] = ((Bishop) piece).checkWayToSouthWest(piece, oldCoords, destCoord);
        return tableOfWays;
    }

    int[][] checkWayToSouthEast(Piece piece, int[] startCoords, int[] destCoords) {
        int[][] possibleAttacks = new int[7][2];
        if (piece.color.equals(Color.BLACK) || piece.getColor().equals(Color.WHITE)) {
            possibleAttacks[0] = new int[]{startCoords[0]+1, startCoords[1]+1};
            possibleAttacks[1] = new int[] {startCoords[0]+2, startCoords[1]+2};
            possibleAttacks[2] = new int[] {startCoords[0]+3, startCoords[1]+3};
            possibleAttacks[3] = new int[] {startCoords[0]+4, startCoords[1]+4};
            possibleAttacks[4] = new int[] {startCoords[0]+5, startCoords[1]+5};
            possibleAttacks[5] = new int[] {startCoords[0]+6, startCoords[1]+6};
            possibleAttacks[6] = new int[] {startCoords[0]+7, startCoords[1]+7};
        }
        return pathFieldsCoordsReturn(destCoords, possibleAttacks);
    }

    int[][] checkWayToNorthEast(Piece piece, int[] startCoords, int[] destCoords) {
        int[][] possibleAttacks = new int[7][2];
        if (piece.color.equals(Color.BLACK) || piece.getColor().equals(Color.WHITE)) {
            possibleAttacks[0] = new int[]{startCoords[0]-1, startCoords[1]+1};
            possibleAttacks[1] = new int[] {startCoords[0]-2, startCoords[1]+2};
            possibleAttacks[2] = new int[] {startCoords[0]-3, startCoords[1]+3};
            possibleAttacks[3] = new int[] {startCoords[0]-4, startCoords[1]+4};
            possibleAttacks[4] = new int[] {startCoords[0]-5, startCoords[1]+5};
            possibleAttacks[5] = new int[] {startCoords[0]-6, startCoords[1]+6};
            possibleAttacks[6] = new int[] {startCoords[0]-7, startCoords[1]+7};
        }
        return pathFieldsCoordsReturn(destCoords, possibleAttacks);
    }

    int[][] checkWayToNorthWest(Piece piece, int[] startCoords, int[] destCoords) {
        int[][] possibleAttacks = new int[7][2];
        if (piece.color.equals(Color.BLACK) || piece.getColor().equals(Color.WHITE)) {
            possibleAttacks[0] = new int[]{startCoords[0]-1, startCoords[1]-1};
            possibleAttacks[1] = new int[] {startCoords[0]-2, startCoords[1]-2};
            possibleAttacks[2] = new int[] {startCoords[0]-3, startCoords[1]-3};
            possibleAttacks[3] = new int[] {startCoords[0]-4, startCoords[1]-4};
            possibleAttacks[4] = new int[] {startCoords[0]-5, startCoords[1]-5};
            possibleAttacks[5] = new int[] {startCoords[0]-6, startCoords[1]-6};
            possibleAttacks[6] = new int[] {startCoords[0]-7, startCoords[1]-7};
        }
        return pathFieldsCoordsReturn(destCoords, possibleAttacks);
    }

    int[][] checkWayToSouthWest(Piece piece, int[] startCoords, int[] destCoords) {
        int[][] possibleAttacks = new int[7][2];
        if (piece.color.equals(Color.BLACK) || piece.getColor().equals(Color.WHITE)) {
            possibleAttacks[0] = new int[]{startCoords[0]+1, startCoords[1]-1};
            possibleAttacks[1] = new int[] {startCoords[0]+2, startCoords[1]-2};
            possibleAttacks[2] = new int[] {startCoords[0]+3, startCoords[1]-3};
            possibleAttacks[3] = new int[] {startCoords[0]+4, startCoords[1]-4};
            possibleAttacks[4] = new int[] {startCoords[0]+5, startCoords[1]-5};
            possibleAttacks[5] = new int[] {startCoords[0]+6, startCoords[1]-6};
            possibleAttacks[6] = new int[] {startCoords[0]+7, startCoords[1]-7};
        }
        return pathFieldsCoordsReturn(destCoords, possibleAttacks);
    }

    @Override
    public int[] possibleMoves(Piece piece, int[] startCoords, int[] destCoords) {
        int[] moveTo = null;
        int[][] possibleAttacksSouth = checkWayToSouthEast(piece, startCoords, destCoords);
        int[][] possibleAttacksNorth = checkWayToNorthEast(piece, startCoords, destCoords);
        int[][] possibleAttacksWest = checkWayToNorthWest(piece, startCoords, destCoords);
        int[][] possibleAttacksEast = checkWayToSouthWest(piece, startCoords, destCoords);
        moveTo = setDestFieldIfNotEmpty(startCoords, destCoords, possibleAttacksSouth, moveTo);
        moveTo = setDestFieldIfNotEmpty(startCoords, destCoords, possibleAttacksNorth, moveTo);
        moveTo = setDestFieldIfNotEmpty(startCoords, destCoords, possibleAttacksWest, moveTo);
        moveTo = setDestFieldIfNotEmpty(startCoords, destCoords, possibleAttacksEast, moveTo);

        return moveTo;
    }
}
