package com.kamil.chess1vs1.pieces;

public interface IPiece {
    String possibleMoves(Piece piece, String destCoords);
    //zmiana koordynatów -> najpierw góra/dó³, potem prawo/lewo
}
