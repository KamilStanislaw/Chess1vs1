package com.kamil.chess1vs1.pieces;

public interface IPiece {
    int[] possibleMoves(Piece piece, int[] startCoords, int[] destCoords);
    //zmiana koordynatów -> najpierw góra/dó³, potem prawo/lewo
}
