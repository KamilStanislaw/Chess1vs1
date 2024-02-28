package kamil.chess1vs1.logic;

import kamil.chess1vs1.pieces.*;

public class ChessBoard implements TurningCoordinates {

    public Piece[][] chessBoard = new Piece[8][8];

    //po wprowadzeniu Grafiki:
    //po kliknięciu figura -> pole będzie sprawdzanie nulla i wtedy decyzja jaką funkcję uruchomić;
    //zczytywanie z pola lub figury DestCoords jako String np H4
    //wtedy w każdej Funkcji() przekazywać Stringa, nie zamieniać na int[] oraz
    //zamienić fielda w Piece z int[] na String. Klarowny kod.
    //wtedy zamiana Pole->index i Index->Pole do Interfejsu i wywołanie (zamiany) wewnątrz potrzebych funkcji w Klasie

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

    public void move(Piece piece, String destCoords) {
        if (piece instanceof Pawn) {
            simpleMove(piece, destCoords);
        } else if (piece instanceof Knight) {
            simpleMove(piece, destCoords);
        } else if (piece instanceof King) {
            simpleMove(piece, destCoords);
        } else if (piece instanceof Rook) {
            moveLongRangePiece(((Rook) piece).getTableOfMoves(piece, destCoords), piece, destCoords);
        } else if (piece instanceof Bishop) {
            moveLongRangePiece(((Bishop) piece).getTableOfMoves(piece, destCoords), piece, destCoords);
        } else if (piece instanceof Queen) {
            moveLongRangePiece(((Queen) piece).getTableOfMoves(piece, destCoords), piece, destCoords);
        }
    }

    public void attack(Piece attackingPiece, Piece defendingPiece, String secondPieceCoords) {
        if (!attackingPiece.getColor().equals(defendingPiece.getColor())) {
            if (attackingPiece instanceof Pawn) {
                simpleAttack(((Pawn) attackingPiece).pawnAttackSchema(attackingPiece, secondPieceCoords), secondPieceCoords, attackingPiece);
            } else if (attackingPiece instanceof Knight) {
                simpleAttack(attackingPiece.possibleMoves(attackingPiece, secondPieceCoords), secondPieceCoords, attackingPiece);
            } else if (attackingPiece instanceof King) {
                simpleAttack(attackingPiece.possibleMoves(attackingPiece, secondPieceCoords), secondPieceCoords, attackingPiece);
            } else if (attackingPiece instanceof Rook) {
                moveLongRangePiece(((Rook) attackingPiece).getTableOfMoves(attackingPiece, secondPieceCoords), attackingPiece, secondPieceCoords);
            } else if (attackingPiece instanceof Bishop) {
                moveLongRangePiece(((Bishop) attackingPiece).getTableOfMoves(attackingPiece, secondPieceCoords), attackingPiece, secondPieceCoords);
            } else if (attackingPiece instanceof Queen) {
                moveLongRangePiece(((Queen) attackingPiece).getTableOfMoves(attackingPiece, secondPieceCoords), attackingPiece, secondPieceCoords);
            }
        }
    }

    private void simpleMove(Piece piece, String destCoord) {
        String oldCoords = turnIndexIntoField(piece.getField());
        int[] destCoordInt = turnFieldIntoIndex(destCoord);
        if (chessBoard[destCoordInt[0]][destCoordInt[1]] == null) {
            String newCoords = piece.possibleMoves(piece, destCoord);
            cleanFieldAtCoords(oldCoords);
            add(piece, newCoords);
        }
    }

    private void simpleAttack(String getMoves, String destCoord, Piece attackingPiece) {
        String oldCoords = turnIndexIntoField(attackingPiece.getField());
            if (getMoves.equals(destCoord)) {
                cleanFieldAtCoords(oldCoords);
                add(attackingPiece, destCoord);
            }
    }

    private void moveLongRangePiece(int[][][] tableOfMoves, Piece piece, String destCoord) {
        for (int[][] clearWay : tableOfMoves) {
            if (clearWay != null) {
                checkRoadAndMove(piece, clearWay, destCoord);
            }
        }
    }

    private void checkRoadAndMove(Piece piece, int[][] clearWayDirection, String destCoord) {
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
            simpleMove(piece, destCoord);
        } else if (iter == clearWayDirection.length - 1) {
            simpleAttack(piece.possibleMoves(piece, destCoord), destCoord, piece);
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
