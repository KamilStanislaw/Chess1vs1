package kamil.chess1vs1.logic;

import kamil.chess1vs1.pieces.*;

public class GameManager {
    Pawn whitePawn1 = new Pawn(Color.WHITE);
    Pawn whitePawn2 = new Pawn(Color.WHITE);
    Pawn whitePawn3 = new Pawn(Color.WHITE);
    Pawn whitePawn4 = new Pawn(Color.WHITE);
    Pawn whitePawn5 = new Pawn(Color.WHITE);
    Pawn whitePawn6 = new Pawn(Color.WHITE);
    Pawn whitePawn7 = new Pawn(Color.WHITE);
    Pawn whitePawn8 = new Pawn(Color.WHITE);
    Pawn blackPawn1 = new Pawn(Color.BLACK);
    Pawn blackPawn2 = new Pawn(Color.BLACK);
    Pawn blackPawn3 = new Pawn(Color.BLACK);
    Pawn blackPawn4 = new Pawn(Color.BLACK);
    Pawn blackPawn5 = new Pawn(Color.BLACK);
    Pawn blackPawn6 = new Pawn(Color.BLACK);
    Pawn blackPawn7 = new Pawn(Color.BLACK);
    Pawn blackPawn8 = new Pawn(Color.BLACK);
    Rook whiteRook1 = new Rook(Color.WHITE);
    Rook whiteRook2 = new Rook(Color.WHITE);
    Rook blackRook1 = new Rook(Color.BLACK);
    Rook blackRook2 = new Rook(Color.BLACK);
    Knight whiteKnight1 = new Knight(Color.WHITE);
    Knight whiteKnight2 = new Knight(Color.WHITE);
    Knight blackKnight1 = new Knight(Color.BLACK);
    Knight blackKnight2 = new Knight(Color.BLACK);
    Bishop whiteBishop1 = new Bishop(Color.WHITE);
    Bishop whiteBishop2 = new Bishop(Color.WHITE);
    Bishop blackBishop1 = new Bishop(Color.BLACK);
    Bishop blackBishop2 = new Bishop(Color.BLACK);
    Queen whiteQueen = new Queen(Color.WHITE);
    Queen blackQueen = new Queen(Color.BLACK);
    King whiteKing = new King(Color.WHITE);
    King blackKing = new King(Color.BLACK);

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    ChessBoard chessBoard = new ChessBoard();
    public void run() {
        addAllPieces(chessBoard);
    }

    public void addAllPieces(ChessBoard chessBoard) {
        chessBoard.add(whitePawn1, "a2");
        chessBoard.add(whitePawn2, "b2");
        chessBoard.add(whitePawn3, "c2");
        chessBoard.add(whitePawn4, "d2");
        chessBoard.add(whitePawn5, "e2");
        chessBoard.add(whitePawn6, "f2");
        chessBoard.add(whitePawn7, "g2");
        chessBoard.add(whitePawn8, "h2");

        chessBoard.add(blackPawn1, "a7");
        chessBoard.add(blackPawn2, "b7");
        chessBoard.add(blackPawn3, "c7");
        chessBoard.add(blackPawn4, "d7");
        chessBoard.add(blackPawn5, "e7");
        chessBoard.add(blackPawn6, "f7");
        chessBoard.add(blackPawn7, "g7");
        chessBoard.add(blackPawn8, "h7");

        chessBoard.add(whiteRook1, "a1");
        chessBoard.add(whiteRook2, "h1");

        chessBoard.add(blackRook1, "a8");
        chessBoard.add(blackRook2, "h8");

        chessBoard.add(whiteKnight1, "b1");
        chessBoard.add(whiteKnight2, "g1");

        chessBoard.add(blackKnight1, "b8");
        chessBoard.add(blackKnight2, "g8");

        chessBoard.add(whiteBishop1, "c1");
        chessBoard.add(whiteBishop2, "f1");

        chessBoard.add(blackBishop1, "c8");
        chessBoard.add(blackBishop2, "f8");

        chessBoard.add(whiteQueen, "d1");
        chessBoard.add(blackQueen, "d8");

        chessBoard.add(whiteKing,"e1");
        chessBoard.add(blackKing,"e8");
    }


}
