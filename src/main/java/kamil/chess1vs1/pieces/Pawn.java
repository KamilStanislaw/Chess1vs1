package kamil.chess1vs1.pieces;

public class Pawn extends Piece {

    public Pawn(Color color, String name) {
        super(color, name);
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
            return destCoordsString; //por�wnuj�c koordynaty w tablicach, nie por�wnuj� warto�ci, tylko ca�e tablice;
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
