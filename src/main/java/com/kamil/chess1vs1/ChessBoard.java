package com.kamil.chess1vs1;

import com.kamil.chess1vs1.pieces.Bishop;
import com.kamil.chess1vs1.pieces.Queen;
import com.kamil.chess1vs1.pieces.Rook;
import com.kamil.chess1vs1.pieces.Color;
import com.kamil.chess1vs1.pieces.Piece;
import com.kamil.chess1vs1.pieces.King;
import com.kamil.chess1vs1.pieces.Knight;
import com.kamil.chess1vs1.pieces.Pawn;

public class ChessBoard {

    public Piece[][] chessBoard = new Piece[8][8];
    //mo¿na napisaæ metodê która tworzy pionki i automatycnzie rozstawia po planszy;

    //po wprowadzeniu Grafiki:
    //po klikniêciu figur / pól bêdzie sprawdzanie nulla i wtedy decyzja jak¹ funkcjê uruchomiæ;
    //zczytywanie z pola lub figury DestCoords jako String np H4
    //wtedy w ka¿dej Funkcji() przekazywaæ Stringa, nie zamieniaæ na int[] oraz
    //zamieniæ fielda w Piece z int[] na String. Klarowny kod.
    //wtedy zamiana Pole->index i Index->Pole do Interfejsu i wywo³anie (zamiany) wewn¹trz potrzebych funkcji w Klasie
    public Piece[][] getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(Piece[][] chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void add(Piece figura, String field) {
        int[] indexes = turnFieldIntoIndex(field);
        figura.setField(indexes);
        chessBoard[indexes[0]][indexes[1]] = figura;
    }

    public Piece cleanFieldAtCoords(String coords) {
        int[] indexes = turnFieldIntoIndex(coords);
        return chessBoard[indexes[0]][indexes[1]] = null;
    }

    public Piece getPieceAtCoords(String coords) {
        int[] indexes = turnFieldIntoIndex(coords);
        return chessBoard[indexes[0]][indexes[1]];
    }

    public int[] turnFieldIntoIndex(String coords) {
        String lower = coords.toLowerCase();
        char[] field = lower.toCharArray();
        char letter = field[0];
        int number = Integer.parseInt(String.valueOf(field[1]));
        int coordOfLetter = letter - 97;
        int coordOfNumber = 8 - number;
        return new int[]{coordOfNumber, coordOfLetter};
    }

    public static String turnIndexIntoField(int[] field) {
        int numb = (- 8 + field[0]) * -1;
        char letter = (char) (field[1] + 97);
        return String.join("", String.valueOf(letter), String.valueOf(numb));
    }

    public void move(Piece piece, String destCoords) {
        int[] destCoord = turnFieldIntoIndex(destCoords);
        if (piece instanceof Pawn) {
            simpleMove(piece, destCoord, piece.getField());
        } else if (piece instanceof Knight) {
            simpleMove(piece, destCoord, piece.getField());
        } else if (piece instanceof King) {
            simpleMove(piece, destCoord, piece.getField());
        } else if (piece instanceof Rook) {
            moveLongRangePiece(((Rook) piece).getTableOfMoves(piece, piece.getField(), destCoord), piece, destCoord);
        } else if (piece instanceof Bishop) {
            moveLongRangePiece(((Bishop) piece).getTableOfMoves(piece, piece.getField(), destCoord), piece, destCoord);
        } else if (piece instanceof Queen) {
            moveLongRangePiece(((Queen) piece).getTableOfMoves(piece, piece.getField(), destCoord), piece, destCoord);
        }
    }

    public void attack(Piece attackingPiece, Piece defendingPiece, String secondPieceCoords) {
        int[] oldCoords = attackingPiece.getField();
        int[] destCoord = turnFieldIntoIndex(secondPieceCoords);
        if (!attackingPiece.getColor().equals(defendingPiece.getColor())) {
            if (attackingPiece instanceof Pawn) {
                simpleAttack(((Pawn) attackingPiece).pawnAttackSchema(attackingPiece, oldCoords, destCoord), destCoord, oldCoords, attackingPiece);
            } else if (attackingPiece instanceof Knight) {
                simpleAttack(attackingPiece.possibleMoves(attackingPiece, oldCoords, destCoord), destCoord, oldCoords, attackingPiece);
            } else if (attackingPiece instanceof King) {
                simpleAttack(attackingPiece.possibleMoves(attackingPiece, oldCoords, destCoord), destCoord, oldCoords, attackingPiece);
            } else if (attackingPiece instanceof Rook) {
                moveLongRangePiece(((Rook) attackingPiece).getTableOfMoves(attackingPiece, oldCoords, destCoord), attackingPiece, destCoord);
            } else if (attackingPiece instanceof Bishop) {
                moveLongRangePiece(((Bishop) attackingPiece).getTableOfMoves(attackingPiece, oldCoords, destCoord), attackingPiece, destCoord);
            } else if (attackingPiece instanceof Queen) {
                moveLongRangePiece(((Queen) attackingPiece).getTableOfMoves(attackingPiece, oldCoords, destCoord), attackingPiece, destCoord);
            }
        }
    }

    private void simpleMove(Piece piece, int[] destCoord, int[] oldCoords) {
        if (chessBoard[destCoord[0]][destCoord[1]] == null) {
            int[] newCoords = piece.possibleMoves(piece, piece.getField(), destCoord);
            cleanFieldAtCoords(turnIndexIntoField(oldCoords));
            add(piece, turnIndexIntoField(newCoords));
        }
    }

    private void simpleAttack(int[] getMoves, int[] destCoord, int[] oldCoords, Piece attackingPiece) {
            if (getMoves == destCoord) {
                cleanFieldAtCoords(turnIndexIntoField(oldCoords));
                add(attackingPiece, turnIndexIntoField(destCoord));
            }
    }

    private void moveLongRangePiece(int[][][] tableOfMoves, Piece piece, int[] destCoord) {
        for (int[][] clearWay : tableOfMoves) {
            if (clearWay != null) {
                checkRoadAndMove(piece, clearWay, destCoord);
            }
        }
    }

    private void checkRoadAndMove(Piece piece, int[][] clearWayDirection, int[] destCoord) {
        int[] chceckWay;
        int iter = 0;
        for (int i = 0; i < clearWayDirection.length;) {
            chceckWay = clearWayDirection[i];
            if (chessBoard[chceckWay[0]][chceckWay[1]] == null) {
                iter ++;
                i ++;
            } else if (chessBoard[chceckWay[0]][chceckWay[1]] != null) {
                break;
            }
        }
        if (iter == clearWayDirection.length) {
            simpleMove(piece, destCoord, piece.getField());
        } else if (iter == clearWayDirection.length - 1) {
            simpleAttack(piece.possibleMoves(piece, piece.getField(), destCoord), destCoord, piece.getField(), piece);
        }
    }

    public void castling(King king, Rook rook) {
        String kingCoords = turnIndexIntoField(king.getField());
        String rookCoords = turnIndexIntoField(rook.getField());
        if (king.getColor().equals(rook.getColor())){
            if (king.getColor().equals(Color.WHITE)) {
                String[] white = {"f1", "g1", "b1", "c1", "d1"};
                if (kingCoords.equals("e1") && rookCoords.equals("h1")) {
                    castlingRight(king, rook, white[0], white[1], king.getField(), rook.getField());
                } else if (kingCoords.equals("e1") && rookCoords.equals("a1")) {
                    castlingLeft(king, rook, white[2], white[3], white[4], king.getField(), rook.getField());
                }
            } else {
                String[] black = {"f8", "g8", "b8", "c8", "d8"};
                if (kingCoords.equals("e8") && rookCoords.equals("h8")) {
                    castlingRight(king, rook, black[0], black[1], king.getField(), rook.getField());
                } else if (kingCoords.equals("e8") && rookCoords.equals("a8")) {
                    castlingLeft(king, rook, black[2], black[3], black[4], king.getField(), rook.getField());
                }
            }
        }
    }

    private void castlingRight(King king, Rook rook, String f, String g, int[] kingStartCoords, int[] rookStartCoords) {
        int[] kingDest = turnFieldIntoIndex(g);
        int[] rookDest = turnFieldIntoIndex(f);
        if (chessBoard[kingDest[0]][kingDest[1]] == null && chessBoard[rookDest[0]][rookDest[1]] == null) {
            moveKingAndRook(king, rook, kingStartCoords, rookStartCoords, kingDest, rookDest);
        }
    }

    private void castlingLeft(King king, Rook rook, String b, String c, String d, int[] kingStartCoords, int[] rookStartCoords) {
        int[] emptyB = turnFieldIntoIndex(b);
        int[] kingDest = turnFieldIntoIndex(c);
        int[] rookDest = turnFieldIntoIndex(d);
        if (chessBoard[emptyB[0]][emptyB[1]] == null && chessBoard[kingDest[0]][kingDest[1]] == null && chessBoard[rookDest[0]][rookDest[1]] == null) {
            moveKingAndRook(king, rook, kingStartCoords, rookStartCoords, kingDest, rookDest);
        }
    }

    private void moveKingAndRook(King king, Rook rook, int[] kingStartCoords, int[] rookStartCoords, int[] kingDest, int[] rookDest) {
        rook.setField(rookDest);
        king.setField(kingDest);
        add(rook, turnIndexIntoField(rookDest));
        add(king, turnIndexIntoField(kingDest));
        cleanFieldAtCoords(turnIndexIntoField(kingStartCoords));
        cleanFieldAtCoords(turnIndexIntoField(rookStartCoords));
    }
}
