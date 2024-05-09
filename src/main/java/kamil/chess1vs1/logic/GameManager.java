package kamil.chess1vs1.logic;

import kamil.chess1vs1.pieces.*;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class GameManager {
    private static final String SAVE_PATH = "src/main/resources/saved/";
    private static final String SAVED_GAMES_PATHS = "src/main/resources/saved/savedGamesList.txt";
    ChessBoard chessBoard = new ChessBoard();
    Set<String> savedGamesPaths;

    public GameManager(Set<String> savedGamesPaths) {
        this.savedGamesPaths = savedGamesPaths;
    }

    void run(int choice, String saveName) {
        addEmergencyPiecesForPromotion(chessBoard);
        if (choice == 1) {
            chessBoard.chessBoard = new Piece[8][8];
            addAllPieces(chessBoard);
        } else if (choice == 2) {
            loadGameFromFile(saveName);
        }

        System.out.println("White pieces starts.\n");
        System.out.println("Insert Piece Symbol and coordinates with ',' ('bk, e7' or 'wp1, a4' \n(black King, white Pawn 1)) to move or attack.\n");
        System.out.println("Insert 'skip' after your turn.");
        System.out.println("Insert 'save' to save your game.");
        System.out.println("Insert 'exit' to end game.");
        System.out.println();

        printChessBoard(chessBoard.chessBoard);

        String turn = "w";
        int[] optionalCoordsToPromote;
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
                System.out.println("Give name to your save game file: ");
                String inputName = scanner.nextLine();
                saveGame(inputName);
                break;
            } else if (coords.contains(",")) {
                String[] input = coords.split(",\\s*");
                String pieceNameInput = input[0];
                if (input[1].matches("[a-hA-h][12345678]")){
                    optionalCoordsToPromote = chessBoard.compareInputWithPieceAndMove(pieceNameInput, input[1], turn);

                    if (optionalCoordsToPromote != null) { //choose piece to replace with pawn
                        System.out.println("Your Pawn has been promoted! Choose between " +
                                "Queen, Rook, Bishop or Knight (Horse): ");
                        String nameOfPromotedPiece = scanner.nextLine().toLowerCase();
                        int eightRow = 0;
                        int firstRow = 7;
                        String[] symbols = {"wQ", "bQ", "wR", "bR", "wB", "bB", "wH", "bH"};
                        switch (nameOfPromotedPiece) {
                            case "queen" -> {
                                promoteReplacePiece(chessBoard, optionalCoordsToPromote, eightRow, symbols[0]);
                                promoteReplacePiece(chessBoard, optionalCoordsToPromote, firstRow, symbols[1]);
                            }
                            case "rook" -> {
                                promoteReplacePiece(chessBoard, optionalCoordsToPromote, eightRow, symbols[2]);
                                promoteReplacePiece(chessBoard, optionalCoordsToPromote, firstRow, symbols[3]);
                            }
                            case "bishop" -> {
                                promoteReplacePiece(chessBoard, optionalCoordsToPromote, eightRow, symbols[4]);
                                promoteReplacePiece(chessBoard, optionalCoordsToPromote, firstRow, symbols[5]);
                            }
                            case "knight", "horse" -> {
                                promoteReplacePiece(chessBoard, optionalCoordsToPromote, eightRow, symbols[6]);
                                promoteReplacePiece(chessBoard, optionalCoordsToPromote, firstRow, symbols[7]);
                            }
                        }
                        printChessBoard(chessBoard.chessBoard);
                    }
                } else System.err.println("Wrong input.\n");
            } else {
                System.err.println("Wrong input.\n");
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

    public void promoteReplacePiece(ChessBoard chessBoard, int[] optionalCoordsToPromote, int row, String symbol) {
        if (optionalCoordsToPromote[0] == row) {
            for (Piece tempPiece : chessBoard.emergencyPiecesForPromotion) {
                if (tempPiece.getName().startsWith(symbol)) {
                    chessBoard.cleanFieldAtCoords(chessBoard.turnIndexIntoField(optionalCoordsToPromote));
                    chessBoard.add(tempPiece, chessBoard.turnIndexIntoField(optionalCoordsToPromote));
                    chessBoard.emergencyPiecesForPromotion.remove(tempPiece);
                    break;
                }
            }
        }
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

    private static String changeTurn(String turn) {
        if ("w".equals(turn)) {
            turn = "b";
        } else if ("b".equals(turn)){
            turn = "w";
        }
        return turn;
    }

    private void saveGame(String saveName) {
        String savePath = SAVE_PATH.concat(saveName).concat(".ser");
        realSaveGameToFile(savePath);
        savedGamesPaths.add(savePath);
        exportSavedGamesPaths(savedGamesPaths);
        System.out.println("Gra zapisana.");
    }

    private void realSaveGameToFile(String path) {
        try {
            ObjectOutputStream save = new ObjectOutputStream(
                    new FileOutputStream(path));
            save.writeObject(chessBoard.chessBoard);
            save.close();
        } catch (IOException e) {
            System.err.println("Saving game failed!");
            e.printStackTrace();
        }
    }

    private void exportSavedGamesPaths(Set<String> savedGamesPaths) {
        File sgFile = new File(SAVED_GAMES_PATHS);
        try (FileWriter fileWriter = new FileWriter(sgFile)) {
            String pathsOfSavedGamesFiles = String.join(System.lineSeparator(), savedGamesPaths);
            fileWriter.write(pathsOfSavedGamesFiles);
            System.out.println("Plik zapisany.");
        } catch (IOException e) {
            System.err.println("Creating list of saved games to display failed.");
            e.printStackTrace();
        }
    }

    private void loadGameFromFile(String saveName) {
        try {
            ObjectInputStream load = new ObjectInputStream(
                    new FileInputStream(SAVE_PATH.concat(saveName.concat(".ser"))));
            chessBoard.chessBoard = (Piece[][]) load.readObject();
            load.close();
            System.out.println("Game successfully loaded!\n");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Loading saved game failed!");
            System.out.println("There is a problem with saved game file... File is corrupted or doesn't exists.");
            System.out.println("Starting new game...\n");
        }
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

    public static void addEmergencyPiecesForPromotion(ChessBoard chessBoard) {

        Queen whiteQueen2 = new Queen(Color.WHITE, "wQ2");
        chessBoard.emergencyPiecesForPromotion.add(whiteQueen2);
        Queen whiteQueen3 = new Queen(Color.WHITE, "wQ3");
        chessBoard.emergencyPiecesForPromotion.add(whiteQueen3);
        Queen whiteQueen4 = new Queen(Color.WHITE, "wQ4");
        chessBoard.emergencyPiecesForPromotion.add(whiteQueen4);
        Queen whiteQueen5 = new Queen(Color.WHITE, "wQ5");
        chessBoard.emergencyPiecesForPromotion.add(whiteQueen5);
        Queen whiteQueen6 = new Queen(Color.WHITE, "wQ6");
        chessBoard.emergencyPiecesForPromotion.add(whiteQueen6);
        Queen whiteQueen7 = new Queen(Color.WHITE, "wQ7");
        chessBoard.emergencyPiecesForPromotion.add(whiteQueen7);
        Queen whiteQueen8 = new Queen(Color.WHITE, "wQ8");
        chessBoard.emergencyPiecesForPromotion.add(whiteQueen8);
        Queen whiteQueen9 = new Queen(Color.WHITE, "wQ9");
        chessBoard.emergencyPiecesForPromotion.add(whiteQueen9);

        Queen blackQueen2 = new Queen(Color.BLACK, "bQ2");
        chessBoard.emergencyPiecesForPromotion.add(blackQueen2);
        Queen blackQueen3 = new Queen(Color.BLACK, "bQ3");
        chessBoard.emergencyPiecesForPromotion.add(blackQueen3);
        Queen blackQueen4 = new Queen(Color.BLACK, "bQ4");
        chessBoard.emergencyPiecesForPromotion.add(blackQueen4);
        Queen blackQueen5 = new Queen(Color.BLACK, "bQ5");
        chessBoard.emergencyPiecesForPromotion.add(blackQueen5);
        Queen blackQueen6 = new Queen(Color.BLACK, "bQ6");
        chessBoard.emergencyPiecesForPromotion.add(blackQueen6);
        Queen blackQueen7 = new Queen(Color.BLACK, "bQ7");
        chessBoard.emergencyPiecesForPromotion.add(blackQueen7);
        Queen blackQueen8 = new Queen(Color.BLACK, "bQ8");
        chessBoard.emergencyPiecesForPromotion.add(blackQueen8);
        Queen blackQueen9 = new Queen(Color.BLACK, "bQ9");
        chessBoard.emergencyPiecesForPromotion.add(blackQueen9);

        Rook whiteRook3 = new Rook(Color.WHITE, "wR3");
        chessBoard.emergencyPiecesForPromotion.add(whiteRook3);
        Rook whiteRook4 = new Rook(Color.WHITE, "wR4");
        chessBoard.emergencyPiecesForPromotion.add(whiteRook4);
        Rook whiteRook5 = new Rook(Color.WHITE, "wR5");
        chessBoard.emergencyPiecesForPromotion.add(whiteRook5);
        Rook whiteRook6 = new Rook(Color.WHITE, "wR6");
        chessBoard.emergencyPiecesForPromotion.add(whiteRook6);
        Rook whiteRook7 = new Rook(Color.WHITE, "wR7");
        chessBoard.emergencyPiecesForPromotion.add(whiteRook7);
        Rook whiteRook8 = new Rook(Color.WHITE, "wR8");
        chessBoard.emergencyPiecesForPromotion.add(whiteRook8);
        Rook whiteRook9 = new Rook(Color.WHITE, "wR9");
        chessBoard.emergencyPiecesForPromotion.add(whiteRook9);
        Rook whiteRook10 = new Rook(Color.WHITE, "wR0");
        chessBoard.emergencyPiecesForPromotion.add(whiteRook10);

        Rook blackRook3 = new Rook(Color.BLACK, "bR3");
        chessBoard.emergencyPiecesForPromotion.add(blackRook3);
        Rook blackRook4 = new Rook(Color.BLACK, "bR4");
        chessBoard.emergencyPiecesForPromotion.add(blackRook4);
        Rook blackRook5 = new Rook(Color.BLACK, "bR5");
        chessBoard.emergencyPiecesForPromotion.add(blackRook5);
        Rook blackRook6 = new Rook(Color.BLACK, "bR6");
        chessBoard.emergencyPiecesForPromotion.add(blackRook6);
        Rook blackRook7 = new Rook(Color.BLACK, "bR7");
        chessBoard.emergencyPiecesForPromotion.add(blackRook7);
        Rook blackRook8 = new Rook(Color.BLACK, "bR8");
        chessBoard.emergencyPiecesForPromotion.add(blackRook8);
        Rook blackRook9 = new Rook(Color.BLACK, "bR9");
        chessBoard.emergencyPiecesForPromotion.add(blackRook9);
        Rook blackRook10 = new Rook(Color.BLACK, "bR0");
        chessBoard.emergencyPiecesForPromotion.add(blackRook10);

        Bishop whiteBishop3 = new Bishop(Color.WHITE, "wB3");
        chessBoard.emergencyPiecesForPromotion.add(whiteBishop3);
        Bishop whiteBishop4 = new Bishop(Color.WHITE, "wB4");
        chessBoard.emergencyPiecesForPromotion.add(whiteBishop4);
        Bishop whiteBishop5 = new Bishop(Color.WHITE, "wB5");
        chessBoard.emergencyPiecesForPromotion.add(whiteBishop5);
        Bishop whiteBishop6 = new Bishop(Color.WHITE, "wB6");
        chessBoard.emergencyPiecesForPromotion.add(whiteBishop6);
        Bishop whiteBishop7 = new Bishop(Color.WHITE, "wB7");
        chessBoard.emergencyPiecesForPromotion.add(whiteBishop7);
        Bishop whiteBishop8 = new Bishop(Color.WHITE, "wB8");
        chessBoard.emergencyPiecesForPromotion.add(whiteBishop8);
        Bishop whiteBishop9 = new Bishop(Color.WHITE, "wB9");
        chessBoard.emergencyPiecesForPromotion.add(whiteBishop9);
        Bishop whiteBishop10 = new Bishop(Color.WHITE, "wB0");
        chessBoard.emergencyPiecesForPromotion.add(whiteBishop10);

        Bishop blackBishop3 = new Bishop(Color.BLACK, "bB3");
        chessBoard.emergencyPiecesForPromotion.add(blackBishop3);
        Bishop blackBishop4 = new Bishop(Color.BLACK, "bB4");
        chessBoard.emergencyPiecesForPromotion.add(blackBishop4);
        Bishop blackBishop5 = new Bishop(Color.BLACK, "bB5");
        chessBoard.emergencyPiecesForPromotion.add(blackBishop5);
        Bishop blackBishop6 = new Bishop(Color.BLACK, "bB6");
        chessBoard.emergencyPiecesForPromotion.add(blackBishop6);
        Bishop blackBishop7 = new Bishop(Color.BLACK, "bB7");
        chessBoard.emergencyPiecesForPromotion.add(blackBishop7);
        Bishop blackBishop8 = new Bishop(Color.BLACK, "bB8");
        chessBoard.emergencyPiecesForPromotion.add(blackBishop8);
        Bishop blackBishop9 = new Bishop(Color.BLACK, "bB9");
        chessBoard.emergencyPiecesForPromotion.add(blackBishop9);
        Bishop blackBishop10 = new Bishop(Color.BLACK, "bB0");
        chessBoard.emergencyPiecesForPromotion.add(blackBishop10);

        Knight whiteKnight3 = new Knight(Color.WHITE, "wH3");
        chessBoard.emergencyPiecesForPromotion.add(whiteKnight3);
        Knight whiteKnight4 = new Knight(Color.WHITE, "wH4");
        chessBoard.emergencyPiecesForPromotion.add(whiteKnight4);
        Knight whiteKnight5 = new Knight(Color.WHITE, "wH5");
        chessBoard.emergencyPiecesForPromotion.add(whiteKnight5);
        Knight whiteKnight6 = new Knight(Color.WHITE, "wH6");
        chessBoard.emergencyPiecesForPromotion.add(whiteKnight6);
        Knight whiteKnight7 = new Knight(Color.WHITE, "wH7");
        chessBoard.emergencyPiecesForPromotion.add(whiteKnight7);
        Knight whiteKnight8 = new Knight(Color.WHITE, "wH8");
        chessBoard.emergencyPiecesForPromotion.add(whiteKnight8);
        Knight whiteKnight9 = new Knight(Color.WHITE, "wH9");
        chessBoard.emergencyPiecesForPromotion.add(whiteKnight9);
        Knight whiteKnight10 = new Knight(Color.WHITE, "wH0");
        chessBoard.emergencyPiecesForPromotion.add(whiteKnight10);

        Knight blackKnight3 = new Knight(Color.BLACK, "bH3");
        chessBoard.emergencyPiecesForPromotion.add(blackKnight3);
        Knight blackKnight4 = new Knight(Color.BLACK, "bH4");
        chessBoard.emergencyPiecesForPromotion.add(blackKnight4);
        Knight blackKnight5 = new Knight(Color.BLACK, "bH5");
        chessBoard.emergencyPiecesForPromotion.add(blackKnight5);
        Knight blackKnight6 = new Knight(Color.BLACK, "bH6");
        chessBoard.emergencyPiecesForPromotion.add(blackKnight6);
        Knight blackKnight7 = new Knight(Color.BLACK, "bH7");
        chessBoard.emergencyPiecesForPromotion.add(blackKnight7);
        Knight blackKnight8 = new Knight(Color.BLACK, "bH8");
        chessBoard.emergencyPiecesForPromotion.add(blackKnight8);
        Knight blackKnight9 = new Knight(Color.BLACK, "bH9");
        chessBoard.emergencyPiecesForPromotion.add(blackKnight9);
        Knight blackKnight10 = new Knight(Color.BLACK, "bH0");
        chessBoard.emergencyPiecesForPromotion.add(blackKnight10);
    }

}
