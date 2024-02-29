package kamil.chess1vs1.pieces;

public interface IPiece {
    String possibleMoves(Piece piece, String destCoords);
}
