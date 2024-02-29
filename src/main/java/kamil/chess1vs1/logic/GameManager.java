package kamil.chess1vs1.logic;

import kamil.chess1vs1.pieces.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

public class GameManager {

    public void run() {
        ChessBoard chessBoard = new ChessBoard();
        addAllPieces(chessBoard);

        System.out.println("White pieces starts.");
        System.out.println("Insert Piece Symbol and coordinates with ',' ('bk, e7' or 'wp1, a4' \n(black King, white Pawn 1)) to move or attack.");
        System.out.println("Insert 'skip' after your turn.");
        System.out.println("Insert 'save' to save your game.");
        System.out.println("Insert 'exit' to end game.");
        System.out.println();

        printChessBoard(chessBoard.chessBoard);

        String turn = "w";
        while (true) {
            if ("w".equals(turn)) {
                System.out.println("Turn: w");
            } else {
                System.out.println("Turn: b");
            }
            Scanner scanner = new Scanner(System.in);
            String coords = scanner.nextLine().toLowerCase();
            if (coords.equals("skip")) {
                turn = changeTurn(turn);
                continue;
            } else if (coords.equals("exit")) {
                break;
            } else if (coords.equals("save")) {

            } else if (coords.contains(",")) {
                String[] input = coords.split(",\\s*");
                String pieceNameInput = input[0];
                chessBoard.compareInputWithPieceAndMove(pieceNameInput, input[1], turn);
            } else {
                System.out.println("Wrong input.\n");
            }

            printChessBoard(chessBoard.chessBoard);

            List<Color> colorsOfPiecesInBoard = Arrays.stream(chessBoard.chessBoard)
                    .flatMap(Stream::of)
                    .filter(Objects::nonNull)
                    .map(Piece::getColor)
                    .toList();
            if (!colorsOfPiecesInBoard.contains(Color.BLACK)) {
                System.out.println("WHITE player WINS!");
                break;
            } else if (!colorsOfPiecesInBoard.contains(Color.WHITE)) {
                System.out.println("BLACK player WINS!");
                break;
            }
        }

    }

    private static String changeTurn(String turn) {
        if ("w".equals(turn)) {
            turn = "b";
        } else if ("b".equals(turn)){
            turn = "w";
        }
        return turn;
    }

    private static void printChessBoard(Piece[][] piecesPosition) {
        System.out.println("    A    B    C    D    E    F    G    H");
        int i = 8;
        for (Piece[] pieces : piecesPosition) {
            System.out.print(i + " ");
            for (Piece onePiece : pieces) {
                if (onePiece == null) {
                    System.out.print("|   |");
                } else {
                    System.out.printf(" %s " ,onePiece.getName());
                }
            }
            i--;
            System.out.println();
            System.out.println();
        }
        System.out.println();
    }

    private static void addAllPieces(ChessBoard chessBoard) {
        Pawn whitePawn1 = new Pawn(Color.WHITE, "wP1");
        Pawn whitePawn2 = new Pawn(Color.WHITE, "wP2");
        Pawn whitePawn3 = new Pawn(Color.WHITE, "wP3");
        Pawn whitePawn4 = new Pawn(Color.WHITE, "wP4");
        Pawn whitePawn5 = new Pawn(Color.WHITE, "wP5");
        Pawn whitePawn6 = new Pawn(Color.WHITE, "wP6");
        Pawn whitePawn7 = new Pawn(Color.WHITE, "wP7");
        Pawn whitePawn8 = new Pawn(Color.WHITE, "wP8");
        Pawn blackPawn1 = new Pawn(Color.BLACK, "bP1");
        Pawn blackPawn2 = new Pawn(Color.BLACK, "bP2");
        Pawn blackPawn3 = new Pawn(Color.BLACK, "bP3");
        Pawn blackPawn4 = new Pawn(Color.BLACK, "bP4");
        Pawn blackPawn5 = new Pawn(Color.BLACK, "bP5");
        Pawn blackPawn6 = new Pawn(Color.BLACK, "bP6");
        Pawn blackPawn7 = new Pawn(Color.BLACK, "bP7");
        Pawn blackPawn8 = new Pawn(Color.BLACK, "bP8");
        Rook whiteRook1 = new Rook(Color.WHITE, "wR1");
        Rook whiteRook2 = new Rook(Color.WHITE, "wR2");
        Rook blackRook1 = new Rook(Color.BLACK, "bR1");
        Rook blackRook2 = new Rook(Color.BLACK, "bR2");
        Knight whiteKnight1 = new Knight(Color.WHITE, "wH1");
        Knight whiteKnight2 = new Knight(Color.WHITE, "wH2");
        Knight blackKnight1 = new Knight(Color.BLACK, "bH1");
        Knight blackKnight2 = new Knight(Color.BLACK, "bH2");
        Bishop whiteBishop1 = new Bishop(Color.WHITE, "wB1");
        Bishop whiteBishop2 = new Bishop(Color.WHITE, "wB2");
        Bishop blackBishop1 = new Bishop(Color.BLACK, "bB1");
        Bishop blackBishop2 = new Bishop(Color.BLACK, "bB2");
        Queen whiteQueen = new Queen(Color.WHITE, "wQn");
        Queen blackQueen = new Queen(Color.BLACK, "bQn");
        King whiteKing = new King(Color.WHITE, "wKi");
        King blackKing = new King(Color.BLACK, "bKi");

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
