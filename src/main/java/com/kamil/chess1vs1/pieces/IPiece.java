package com.kamil.chess1vs1.pieces;

public interface IPiece {
    String possibleMoves(Piece piece, String destCoords);
    //zmiana koordynat�w -> najpierw g�ra/d�, potem prawo/lewo
}
