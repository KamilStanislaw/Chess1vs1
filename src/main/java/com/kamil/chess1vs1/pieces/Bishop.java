package com.kamil.chess1vs1.pieces;

public class Bishop extends Piece implements MethodsForLongMovingNoJumpingPieces {
    public Bishop(Color color) {
        super(color);
    }
    public int[][][] getTableOfMoves(Piece piece, String destCoord) {
        int[][][] tableOfWays = new int[4][][];
        tableOfWays[0] = ((Bishop) piece).checkWayToSouthEast(piece, destCoord);
        tableOfWays[1] = ((Bishop) piece).checkWayToNorthEast(piece, destCoord);
        tableOfWays[2] = ((Bishop) piece).checkWayToNorthWest(piece, destCoord);
        tableOfWays[3] = ((Bishop) piece).checkWayToSouthWest(piece, destCoord);
        return tableOfWays;
    }

    int[][] checkWayToSouthEast(Piece piece, String destCoordsString) {
        int[] startCoords = piece.field;
        int[] destCoords = turnFieldIntoIndex(destCoordsString);
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

    int[][] checkWayToNorthEast(Piece piece, String destCoordsString) {
        int[] startCoords = piece.field;
        int[] destCoords = turnFieldIntoIndex(destCoordsString);
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

    int[][] checkWayToNorthWest(Piece piece, String destCoordsString) {
        int[] startCoords = piece.field;
        int[] destCoords = turnFieldIntoIndex(destCoordsString);
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

    int[][] checkWayToSouthWest(Piece piece, String destCoordsString) {
        int[] startCoords = piece.field;
        int[] destCoords = turnFieldIntoIndex(destCoordsString);
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
    public String possibleMoves(Piece piece, String destCoordsString) {
        int[] destCoords = turnFieldIntoIndex(destCoordsString);
        int[] startCoords = piece.field;
        int[] moveTo = null;
        int[][] possibleAttacksSouth = checkWayToSouthEast(piece, destCoordsString);
        int[][] possibleAttacksNorth = checkWayToNorthEast(piece, destCoordsString);
        int[][] possibleAttacksWest = checkWayToNorthWest(piece, destCoordsString);
        int[][] possibleAttacksEast = checkWayToSouthWest(piece, destCoordsString);
        moveTo = setDestFieldIfExist(startCoords, destCoords, possibleAttacksSouth, moveTo);
        moveTo = setDestFieldIfExist(startCoords, destCoords, possibleAttacksNorth, moveTo);
        moveTo = setDestFieldIfExist(startCoords, destCoords, possibleAttacksWest, moveTo);
        moveTo = setDestFieldIfExist(startCoords, destCoords, possibleAttacksEast, moveTo);

        return turnIndexIntoField(moveTo);
    }
}
