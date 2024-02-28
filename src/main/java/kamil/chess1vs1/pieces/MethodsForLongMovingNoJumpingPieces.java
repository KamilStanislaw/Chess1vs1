package kamil.chess1vs1.pieces;

import java.util.Arrays;

public interface MethodsForLongMovingNoJumpingPieces {
    int[][][] getTableOfMoves(Piece piece, String destCoord);

    default int[][] pathFieldsCoordsReturn(int[] destCoords, int[][] possibleAttacks) {
        int[][] pathCoords = new int[0][2];
        for (int[] possibleAttack : possibleAttacks) {
            int[][] tempPath = Arrays.copyOf(pathCoords, pathCoords.length + 1);
            tempPath[pathCoords.length] = possibleAttack;
            pathCoords = tempPath;
            if (Arrays.equals(possibleAttack, destCoords)) {
                return pathCoords;
            }
        }
        return null;
    }

    default int[] setDestFieldIfExist(int[] startCoords, int[] destCoords, int[][] possibleAttacks, int[] moveTo) {
        if (possibleAttacks != null) {
            for (int[] possibleAttack : possibleAttacks) {
                if (Arrays.equals(possibleAttack, destCoords)) {
                    moveTo = destCoords;
                    break;
                } else moveTo = startCoords;
            }
        }
        return moveTo;
    }
}
