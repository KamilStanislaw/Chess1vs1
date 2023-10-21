package com.kamil.chess1vs1.pieces;

public class Rook extends Piece implements MethodsForLongMovingNoJumpingPieces {
    public Rook(Color color) {
        super(color);
    }
    public int[][][] getTableOfMoves(Piece piece, String startCoords, String destCoord) {
        int[][][] tableOfWays = new int[4][][];
        tableOfWays[0] = ((Rook) piece).checkWayToSouth(piece, startCoords, destCoord);
        tableOfWays[1] = ((Rook) piece).checkWayToNorth(piece, startCoords, destCoord);
        tableOfWays[2] = ((Rook) piece).checkWayToWest(piece, startCoords, destCoord);
        tableOfWays[3] = ((Rook) piece).checkWayToEast(piece, startCoords, destCoord);
        return tableOfWays;
    }

    int[][] checkWayToSouth(Piece piece, String startCoordsString, String destCoordsString) {
        int[] startCoords = turnFieldIntoIndex(startCoordsString);
        int[] destCoords = turnFieldIntoIndex(destCoordsString);
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

    int[][] checkWayToNorth(Piece piece, String startCoordsString, String destCoordsString) {
        int[] startCoords = turnFieldIntoIndex(startCoordsString);
        int[] destCoords = turnFieldIntoIndex(destCoordsString);
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

    int[][] checkWayToWest(Piece piece, String startCoordsString, String destCoordsString) {
        int[] startCoords = turnFieldIntoIndex(startCoordsString);
        int[] destCoords = turnFieldIntoIndex(destCoordsString);
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

    int[][] checkWayToEast(Piece piece, String startCoordsString, String destCoordsString) {
        int[] startCoords = turnFieldIntoIndex(startCoordsString);
        int[] destCoords = turnFieldIntoIndex(destCoordsString);
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
    public String possibleMoves(Piece piece, String destCoordsString) {
        int[] destCoords = turnFieldIntoIndex(destCoordsString);
        int[] startCoords = piece.getField();
        int[] moveTo = null;
        int[][] possibleAttacksSouth = checkWayToSouth(piece, turnIndexIntoField(piece.getField()), destCoordsString);
        int[][] possibleAttacksNorth = checkWayToNorth(piece, turnIndexIntoField(piece.getField()), destCoordsString);
        int[][] possibleAttacksWest = checkWayToWest(piece, turnIndexIntoField(piece.getField()), destCoordsString);
        int[][] possibleAttacksEast = checkWayToEast(piece, turnIndexIntoField(piece.getField()), destCoordsString);
        moveTo = setDestFieldIfExist(startCoords, destCoords, possibleAttacksSouth, moveTo);
        moveTo = setDestFieldIfExist(startCoords, destCoords, possibleAttacksNorth, moveTo);
        moveTo = setDestFieldIfExist(startCoords, destCoords, possibleAttacksWest, moveTo);
        moveTo = setDestFieldIfExist(startCoords, destCoords, possibleAttacksEast, moveTo);

        return turnIndexIntoField(moveTo);
    }
}
