package kamil.chess1vs1.pieces;

public class Queen extends Piece implements MethodsForLongMovingNoJumpingPieces {

    private Bishop bishopMoves = new Bishop(Color.WHITE, "b");
    private Rook rookMoves = new Rook(Color.WHITE, "r");
    public Queen(Color color, String name) {
        super(color, name);
    }

    @Override
    public int[][][] getTableOfMoves(Piece piece, String destCoord) {
        int[] queenField = piece.getField();
        rookMoves.setField(queenField);
        bishopMoves.setField(queenField);
        int[][][] tableOfWays = new int[8][][];
        int[][][] rook = rookMoves.getTableOfMoves(rookMoves, destCoord);
        int[][][] bishop = bishopMoves.getTableOfMoves(bishopMoves, destCoord);
        System.arraycopy(rook, 0, tableOfWays, 0, rook.length);
        System.arraycopy(bishop, 0, tableOfWays, 4, bishop.length);
        return tableOfWays;
    }

    @Override
    public String possibleMoves(Piece piece, String destCoordsString) {
        int[] destCoords = turnFieldIntoIndex(destCoordsString);
        int[] startCoords = piece.field;
        int[] moveTo = null;
        int[][][] possibleAttacks = getTableOfMoves(piece, destCoordsString);
        for (int[][] possibleAttack : possibleAttacks) {
            moveTo = setDestFieldIfExist(startCoords, destCoords, possibleAttack, moveTo);
        }
        return turnIndexIntoField(moveTo);
    }

}
