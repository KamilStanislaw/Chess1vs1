package com.kamil.chess1vs1.pieces;

public interface IPiece {
    int[] possibleMoves(Piece piece, int[] startCoords, int[] destCoords);
    //zmiana koordynat�w -> najpierw g�ra/d�, potem prawo/lewo
}
