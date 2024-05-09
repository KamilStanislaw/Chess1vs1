package kamil.chess1vs1;

import kamil.chess1vs1.logic.ChessBoard;
import kamil.chess1vs1.pieces.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {
    private ChessBoard board = new ChessBoard();
    @Test
    public void canAddPawnAndKnight() {
        Pawn pawn = new Pawn(Color.WHITE, "white");
        board.add(pawn, "f6");
        Knight knight = new Knight(Color.BLACK, "black");
        board.add(knight, "c1");
        assertEquals(pawn, board.getPieceAtCoords("f6"));
        assertEquals(knight, board.getPieceAtCoords("c1"));
    }
    @Test
    public void checkFields() {
        ChessBoard nowa = new ChessBoard();
        Pawn pawn = new Pawn(Color.WHITE, "white");
        nowa.add(pawn, "f6");
        Knight knight = new Knight(Color.BLACK, "black");
        nowa.add(knight, "c1");
        nowa.move(knight,"d3");
        assertArrayEquals(nowa.turnFieldIntoIndex("f6"), pawn.getField());
        assertArrayEquals(nowa.turnFieldIntoIndex("d3"), knight.getField());
        nowa.cleanFieldAtCoords("f6");
        nowa.cleanFieldAtCoords("d3");
    }
    @Test
    public void canClearAField() {
        Pawn pawn = new Pawn(Color.BLACK, "black");
        board.add(pawn, "a7");
        Pawn removeBlackPawn = (Pawn) board.cleanFieldAtCoords("a7");
        Pawn removeWhitePawn = (Pawn) board.cleanFieldAtCoords("f6");
        Pawn removeBlackKnight = (Pawn) board.cleanFieldAtCoords("c1");
        assertNull(removeBlackPawn);
        assertNull(removeWhitePawn);
        assertNull(removeBlackKnight);
    }
    @Test
    public void canMoveWhitePawnForward() {
        Pawn pawn = new Pawn(Color.WHITE, "white");
        board.add(pawn, "d2");
        board.move(pawn, "d3");
        Pawn findPawn = (Pawn) board.getPieceAtCoords("d3");
        Pawn noFindPawn = (Pawn) board.getPieceAtCoords("d2");
        assertEquals(pawn, findPawn);
        assertNull(noFindPawn);
    }
    @Test
    public void canMoveBlackPawnForward() {
        Pawn pawn = new Pawn(Color.BLACK, "black");
        board.add(pawn, "f7");
        board.move(pawn, "f6");
        Pawn findPawn = (Pawn) board.getPieceAtCoords("f6");
        assertEquals(pawn, findPawn);
    }
    @Test
    public void canBeginMoveWhitePawnForwardPlus2() {
        Pawn pawn = new Pawn(Color.WHITE, "white");
        board.add(pawn, "d2");
        board.move(pawn, "d4");
        Pawn findPawn = (Pawn) board.getPieceAtCoords("d4");
        assertEquals(pawn, findPawn);
    }
    @Test
    public void canBeginMoveBlackPawnForwardPlus2() {
        Pawn pawn = new Pawn(Color.BLACK, "black");
        board.add(pawn, "f7");
        board.move(pawn, "f5");
        Pawn findPawn = (Pawn) board.getPieceAtCoords("f5");
        assertEquals(pawn, findPawn);
    }
    @Test
    public void cantMoveBlackPawnForwardIfPlus1NotEmpty() {
        Pawn pawnWhite = new Pawn(Color.WHITE, "white");
        Pawn pawnBlack = new Pawn(Color.BLACK, "black");
        board.add(pawnWhite, "f7");
        board.add(pawnBlack, "f6");
        board.move(pawnWhite, "f6");
        Pawn findPawn = (Pawn) board.getPieceAtCoords("f7");
        Pawn findBlack = (Pawn) board.getPieceAtCoords("f6");
        assertEquals(pawnWhite, findPawn);
        assertEquals(pawnBlack, findBlack);
    }
    @Test
    public void canAttackOtherPiece() {
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        board.add(whitePawn, "c6");
        Pawn blackPawn = new Pawn(Color.BLACK, "black");
        board.add(blackPawn, "d7");
        board.attack(whitePawn, blackPawn, "d7");
        Pawn pawn = (Pawn) board.getPieceAtCoords("c6");
        assertNull(pawn);
    }
    @Test
    public void whenAttackOtherPieceIfColorIsCorrect() {
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        board.add(whitePawn, "c6");
        Pawn blackPawn = new Pawn(Color.BLACK, "black");
        board.add(blackPawn, "d7");
        board.attack(whitePawn, blackPawn, "d7");
        Pawn pawn = (Pawn) board.getPieceAtCoords("d7");
        assertEquals(whitePawn, pawn);
    }
    @Test
    public void cantAttackSamePieceColor() {
        ChessBoard board = new ChessBoard();
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        Knight otherWhite = new Knight(Color.WHITE, "white");
        board.add(whitePawn, "c6");
        board.add(otherWhite, "d7");
        board.attack(whitePawn, otherWhite, "d7");
        Pawn pawn = (Pawn) board.getPieceAtCoords("c6");
        Knight knight = (Knight) board.getPieceAtCoords("d7") ;
        assertEquals(whitePawn, pawn);
        assertEquals(otherWhite, knight);
    }
    @Test
    public void coloVSColor() {
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        board.add(whitePawn, "c6");
        Pawn blackPawn = new Pawn(Color.BLACK, "black");
        board.add(blackPawn, "d7");
        Pawn whitePawn2 = new Pawn(Color.WHITE, "white");
        board.add(whitePawn, "a1");
        Pawn blackPawn2 = new Pawn(Color.BLACK, "black");
        board.add(blackPawn, "b1");
        assertNotEquals(whitePawn, blackPawn);
        assertNotEquals(whitePawn2, blackPawn2);
    }
    @Test
    public void whenAttackIsSecondReplaced() {
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        board.add(whitePawn, "c6");
        Pawn blackPawn = new Pawn(Color.BLACK, "black");
        board.add(blackPawn, "d7");
        board.attack(whitePawn, blackPawn, "d7");
        Pawn pawn = (Pawn) board.getPieceAtCoords("d7");
        Pawn pawn2 = (Pawn) board.getPieceAtCoords("c6");
        assertNotEquals(blackPawn, pawn);
        assertNull(pawn2);
    }
    @Test
    public void knightCorrectMoveAndClean() {
        ChessBoard board = new ChessBoard();
        Knight whiteKnight = new Knight(Color.WHITE, "white");
        board.add(whiteKnight, "f3");
        board.move(whiteKnight, "e5");
        Knight knightMoved = (Knight) board.getPieceAtCoords("e5");
        Knight knightCleaned = (Knight) board.getPieceAtCoords("f3");
        assertEquals(whiteKnight, knightMoved);
        assertNull(knightCleaned);
    }
    @Test
    public void knightCorrectMoveNorthRightAndClean() {
        ChessBoard board = new ChessBoard();
        Knight whiteKnight = new Knight(Color.WHITE, "white");
        board.add(whiteKnight, "f3");
        board.move(whiteKnight, "g5");
        Knight knightMoved = (Knight) board.getPieceAtCoords("g5");
        Knight knightCleaned = (Knight) board.getPieceAtCoords("f3");
        assertEquals(whiteKnight, knightMoved);
        assertNull(knightCleaned);
    }
    @Test
    public void knightCorrectMove2() {
        ChessBoard board2 = new ChessBoard();
        Knight whiteKnight = new Knight(Color.WHITE, "white");
        board2.add(whiteKnight, "f3");
        board2.move(whiteKnight, "d2");
        Knight knightMoved = (Knight) board2.getPieceAtCoords("d2");
        Knight knightCleaned = (Knight) board2.getPieceAtCoords("f3");
        assertEquals(whiteKnight, knightMoved);
        assertNull(knightCleaned);
    }
    @Test
    public void knightCorrectMove3() {
        ChessBoard board2 = new ChessBoard();
        Knight whiteKnight = new Knight(Color.WHITE, "white");
        board2.add(whiteKnight, "d5");
        board2.move(whiteKnight, "c7");
        Knight knightMoved = (Knight) board2.getPieceAtCoords("c7");
        Knight knightCleaned = (Knight) board2.getPieceAtCoords("d5");
        assertEquals(whiteKnight, knightMoved);
        assertNull(knightCleaned);
    }
    @Test
    public void knightBlackCorrectMove3() {
        ChessBoard board2 = new ChessBoard();
        Knight blackKnight = new Knight(Color.BLACK, "black");
        board2.add(blackKnight, "b8");
        board2.move(blackKnight, "a6");
        Knight knightMoved = (Knight) board2.getPieceAtCoords("a6");
        Knight knightCleaned = (Knight) board2.getPieceAtCoords("b8");
        assertEquals(blackKnight, knightMoved);
        assertNull(knightCleaned);
    }
    @Test
    public void canKnightAttackOtherPieceAndLeftField() {
        ChessBoard board2 = new ChessBoard();
        Knight whiteKnight = new Knight(Color.WHITE, "white");
        board2.add(whiteKnight, "g1");
        Pawn blackPawn = new Pawn(Color.BLACK, "black");
        board2.add(blackPawn, "f3");
        board2.attack(whiteKnight, blackPawn, "f3");
        Knight knight = (Knight) board2.getPieceAtCoords("g1");
        Knight knightNewPlace = (Knight) board2.getPieceAtCoords("f3");
        assertNull(knight);
        assertEquals(whiteKnight, knightNewPlace);
    }
    @Test
    public void cantKnightAttackSameColor() {
        ChessBoard board2 = new ChessBoard();
        Knight whiteKnight = new Knight(Color.WHITE, "white");
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        board2.add(whiteKnight, "g1");
        board2.add(whitePawn, "f3");
        board2.attack(whiteKnight, whitePawn, "f3");
        Knight knight = (Knight) board2.getPieceAtCoords("g1");
        Pawn pawn = (Pawn) board2.getPieceAtCoords("f3");
        assertEquals(whiteKnight, knight);
        assertEquals(whitePawn, pawn);
    }
    @Test
    public void kingCorrectMove() {
        ChessBoard board2 = new ChessBoard();
        King blackKing = new King(Color.WHITE, "white");
        board2.add(blackKing, "f5");
        board2.move(blackKing, "f6");
        King kingMoved = (King) board2.getPieceAtCoords("f6");
        King kingCleaned = (King) board2.getPieceAtCoords("f5");
        assertEquals(blackKing, kingMoved);
        assertNull(kingCleaned);
    }
    @Test
    public void kingCorrectMove2() {
        ChessBoard board2 = new ChessBoard();
        King blackKing = new King(Color.WHITE, "white");
        board2.add(blackKing, "f5");
        board2.move(blackKing, "e4");
        King kingMoved = (King) board2.getPieceAtCoords("e4");
        King kingCleaned = (King) board2.getPieceAtCoords("f5");
        assertEquals(blackKing, kingMoved);
        assertNull(kingCleaned);
    }
    @Test
    public void kingCorrectMove3() {
        ChessBoard board2 = new ChessBoard();
        King blackKing = new King(Color.WHITE, "white");
        board2.add(blackKing, "f5");
        board2.move(blackKing, "f4");
        King kingMoved = (King) board2.getPieceAtCoords("f4");
        King kingCleaned = (King) board2.getPieceAtCoords("f5");
        assertEquals(blackKing, kingMoved);
        assertNull(kingCleaned);
    }
    @Test
    public void kingCorrectMove4() {
        ChessBoard board2 = new ChessBoard();
        King blackKing = new King(Color.WHITE, "white");
        board2.add(blackKing, "f5");
        board2.move(blackKing, "g4");
        King kingMoved = (King) board2.getPieceAtCoords("g4");
        King kingCleaned = (King) board2.getPieceAtCoords("f5");
        assertEquals(blackKing, kingMoved);
        assertNull(kingCleaned);
    }
    @Test
    public void canKingAttackOtherPieceAndLeftField() {
        ChessBoard board2 = new ChessBoard();
        King whiteKing = new King(Color.WHITE, "white");
        board2.add(whiteKing, "f5");
        Pawn blackPawn = new Pawn(Color.BLACK, "black");
        board2.add(blackPawn, "f4");
        board2.attack(whiteKing, blackPawn, "f4");
        King king = (King) board2.getPieceAtCoords("f5");
        King kingNewPlace = (King) board2.getPieceAtCoords("f4");
        assertNull(king);
        assertEquals(whiteKing, kingNewPlace);
    }
    @Test
    public void cantKingAttackSameColor() {
        ChessBoard board2 = new ChessBoard();
        King whiteKing = new King(Color.WHITE, "white");
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        board2.add(whiteKing, "f5");
        board2.add(whitePawn, "f4");
        board2.attack(whiteKing, whitePawn, "f4");
        King king = (King) board2.getPieceAtCoords("f5");
        Pawn pawn = (Pawn) board2.getPieceAtCoords("f4");
        assertEquals(whiteKing, king);
        assertEquals(whitePawn, pawn);
    }
    @Test
    public void kingIncorrectMoveDontMove() {
        ChessBoard board2 = new ChessBoard();
        King blackKing = new King(Color.WHITE, "white");
        board2.add(blackKing, "f5");
        board2.move(blackKing, "a8");
        King kingNotMoved = (King) board2.getPieceAtCoords("a8");
        King kingStayed = (King) board2.getPieceAtCoords("f5");
        assertNull(kingNotMoved);
        assertEquals(blackKing, kingStayed);
    }
    @Test
    public void cantKingAttackIfWrongCoords() {
        ChessBoard board2 = new ChessBoard();
        King whiteKing = new King(Color.WHITE, "white");
        board2.add(whiteKing, "f5");
        Pawn blackPawn = new Pawn(Color.BLACK, "black");
        board2.add(blackPawn, "h8");
        board2.attack(whiteKing, blackPawn, "h8");
        King king = (King) board2.getPieceAtCoords("f5");
        Pawn pawn = (Pawn) board2.getPieceAtCoords("h8");
        assertEquals(whiteKing, king);
        assertEquals(blackPawn, pawn);
    }
    @Test
    public void rooktCorrectMoveAndClean() {
        ChessBoard board = new ChessBoard();
        Rook whiteRook = new Rook(Color.WHITE, "white");
        board.add(whiteRook, "d5");
        board.move(whiteRook, "h5");
        Rook rookMoved = (Rook) board.getPieceAtCoords("h5");
        Rook rookCleaned = (Rook) board.getPieceAtCoords("d5");
        assertEquals(whiteRook, rookMoved);
        assertNull(rookCleaned);
    }
    @Test
    public void rooktCorrectMoveAndClean2() {
        ChessBoard board = new ChessBoard();
        Rook whiteRook = new Rook(Color.WHITE, "white");
        board.add(whiteRook, "d5");
        board.move(whiteRook, "d1");
        Rook rookMoved = (Rook) board.getPieceAtCoords("d1");
        Rook rookCleaned = (Rook) board.getPieceAtCoords("d5");
        assertEquals(whiteRook, rookMoved);
        assertNull(rookCleaned);
    }
    @Test
    public void rooktCorrectMoveAndClean3() {
        ChessBoard board = new ChessBoard();
        Rook whiteRook = new Rook(Color.WHITE, "white");
        board.add(whiteRook, "a1");
        board.move(whiteRook, "a8");
        Rook rookMoved = (Rook) board.getPieceAtCoords("a8");
        Rook rookCleaned = (Rook) board.getPieceAtCoords("a1");
        assertEquals(whiteRook, rookMoved);
        assertNull(rookCleaned);
    }
    @Test
    public void rooktCorrectMoveAndClean4() {
        ChessBoard board = new ChessBoard();
        Rook whiteRook = new Rook(Color.WHITE, "white");
        board.add(whiteRook, "a1");
        board.move(whiteRook, "h1");
        Rook rookMoved = (Rook) board.getPieceAtCoords("h1");
        Rook rookCleaned = (Rook) board.getPieceAtCoords("a1");
        assertEquals(whiteRook, rookMoved);
        assertNull(rookCleaned);
    }
    @Test
    public void rooktCorrectMoveAndClean5() {
        ChessBoard board = new ChessBoard();
        Rook whiteRook = new Rook(Color.WHITE, "white");
        board.add(whiteRook, "a1");
        Pawn blackPawn = new Pawn(Color.BLACK, "black");
        board.add(blackPawn, "g2");
        board.move(whiteRook, "h1");
        Rook rookMoved = (Rook) board.getPieceAtCoords("h1");
        Rook rookCleaned = (Rook) board.getPieceAtCoords("a1");
        assertEquals(whiteRook, rookMoved);
        assertNull(rookCleaned);
    }
    @Test
    public void rooktCorrectMoveAndClean6() {
        ChessBoard board = new ChessBoard();
        Rook whiteRook = new Rook(Color.WHITE, "white");
        board.add(whiteRook, "a1");
        Pawn blackPawn = new Pawn(Color.BLACK, "black");
        board.add(blackPawn, "h1");
        board.move(whiteRook, "e1");
        Rook rookMoved = (Rook) board.getPieceAtCoords("e1");
        Rook rookCleaned = (Rook) board.getPieceAtCoords("a1");
        assertEquals(whiteRook, rookMoved);
        assertNull(rookCleaned);
    }
    @Test
    public void rooktCorrectMoveAndClean7() {
        ChessBoard board = new ChessBoard();
        Rook whiteRook = new Rook(Color.WHITE, "white");
        board.add(whiteRook, "f5");
        Pawn blackPawn = new Pawn(Color.BLACK, "black");
        board.add(blackPawn, "f8");
        board.move(whiteRook, "f1");
        Rook rookMoved = (Rook) board.getPieceAtCoords("f1");
        Rook rookCleaned = (Rook) board.getPieceAtCoords("f5");
        assertEquals(whiteRook, rookMoved);
        assertNull(rookCleaned);
    }
    @Test
    public void rookIncorrectMoveCanceled() {
        ChessBoard board = new ChessBoard();
        Rook blackRook = new Rook(Color.BLACK, "black");
        board.add(blackRook, "g3");
        board.move(blackRook, "e4");
        Rook rookNotAllowed = (Rook) board.getPieceAtCoords("e4");
        Rook rookStayed = (Rook) board.getPieceAtCoords("g3");
        assertEquals(blackRook, rookStayed);
        assertNull(rookNotAllowed);
    }
    @Test
    public void rookIncorrectMoveCanceled2() {
        ChessBoard board = new ChessBoard();
        Rook blackRook = new Rook(Color.BLACK, "black");
        board.add(blackRook, "h8");
        board.move(blackRook, "b1");
        Rook rookNotAllowed = (Rook) board.getPieceAtCoords("b1");
        Rook rookStayed = (Rook) board.getPieceAtCoords("h8");
        assertEquals(blackRook, rookStayed);
        assertNull(rookNotAllowed);
    }
    @Test
    public void rookWayIsNotFree() {
        ChessBoard board = new ChessBoard();
        Rook blackRook = new Rook(Color.BLACK, "black");
        board.add(blackRook, "d5");
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        board.add(whitePawn, "f5");
        board.move(blackRook, "h5");
        Rook rookNotAllowed = (Rook) board.getPieceAtCoords("h5");
        Rook rookStayed = (Rook) board.getPieceAtCoords("d5");
        Pawn pawnSafe = (Pawn) board.getPieceAtCoords("f5");
        assertEquals(whitePawn, pawnSafe);
        assertEquals(blackRook, rookStayed);
        assertNull(rookNotAllowed);
    }
    @Test
    public void rookWayIsNotFree2() {
        ChessBoard board = new ChessBoard();
        Rook blackRook = new Rook(Color.BLACK, "black");
        board.add(blackRook, "d8");
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        board.add(whitePawn, "d6");
        board.move(blackRook, "d2");
        Rook rookNotAllowed = (Rook) board.getPieceAtCoords("d2");
        Rook rookStayed = (Rook) board.getPieceAtCoords("d8");
        Pawn pawnSafe = (Pawn) board.getPieceAtCoords("d6");
        assertEquals(whitePawn, pawnSafe);
        assertEquals(blackRook, rookStayed);
        assertNull(rookNotAllowed);
    }
    @Test
    public void rookAttackAndCleared() {
        ChessBoard board = new ChessBoard();
        Rook blackRook = new Rook(Color.BLACK, "black");
        board.add(blackRook, "d5");
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        board.add(whitePawn, "g5");
        board.attack(blackRook, whitePawn, "g5");
        Rook movedRook = (Rook) board.getPieceAtCoords("g5");
        Rook cleared = (Rook) board.getPieceAtCoords("d5");
        assertNull(cleared);
        assertEquals(blackRook, movedRook);
    }
    @Test
    public void rookAttackAndCleared2() {
        ChessBoard board = new ChessBoard();
        Rook blackRook = new Rook(Color.BLACK, "black");
        board.add(blackRook, "d7");
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        board.add(whitePawn, "d3");
        board.attack(blackRook, whitePawn, "d3");
        Rook movedRook = (Rook) board.getPieceAtCoords("d3");
        Rook cleared = (Rook) board.getPieceAtCoords("d7");
        assertNull(cleared);
        assertEquals(blackRook, movedRook);
    }
    @Test
    public void rookCantAttackSameColor() {
        ChessBoard board = new ChessBoard();
        Rook blackRook = new Rook(Color.BLACK, "black");
        board.add(blackRook, "d7");
        Pawn blackPawn = new Pawn(Color.BLACK, "black");
        board.add(blackPawn, "d3");
        board.attack(blackRook, blackPawn, "d3");
        Rook stayedRook = (Rook) board.getPieceAtCoords("d7");
        Pawn stayedPawn = (Pawn) board.getPieceAtCoords("d3");
        assertEquals(blackRook, stayedRook);
        assertEquals(blackPawn, stayedPawn);
    }
    @Test
    public void rookCantAttackSameColor2() {
        ChessBoard board = new ChessBoard();
        Rook blackRook = new Rook(Color.BLACK, "black");
        board.add(blackRook, "a5");
        Pawn blackPawn = new Pawn(Color.BLACK, "black");
        board.add(blackPawn, "f5");
        board.attack(blackRook, blackPawn, "f5");
        Rook stayedRook = (Rook) board.getPieceAtCoords("a5");
        Pawn stayedPawn = (Pawn) board.getPieceAtCoords("f5");
        assertEquals(blackRook, stayedRook);
        assertEquals(blackPawn, stayedPawn);
    }
    @Test
    public void rookCantJumpToAttack() {
        ChessBoard board = new ChessBoard();
        Rook blackRook = new Rook(Color.BLACK, "black");
        board.add(blackRook, "a5");
        Pawn blackPawn = new Pawn(Color.BLACK, "black");
        board.add(blackPawn, "f5");
        Knight whiteKnight = new Knight(Color.WHITE, "white");
        board.add(whiteKnight, "h5");
        board.attack(blackRook, whiteKnight, "h5");
        Rook stayedRook = (Rook) board.getPieceAtCoords("a5");
        Pawn stayedPawn = (Pawn) board.getPieceAtCoords("f5");
        Knight stayedKnight = (Knight) board.getPieceAtCoords("h5");
        assertEquals(blackRook, stayedRook);
        assertEquals(blackPawn, stayedPawn);
        assertEquals(whiteKnight, stayedKnight);
    }
    @Test
    public void bishopCanMoveAndClean() {
        ChessBoard board = new ChessBoard();
        Bishop whiteBishop = new Bishop(Color.WHITE, "white");
        board.add(whiteBishop, "d4");
        board.move(whiteBishop, "h8");
        Bishop moved = (Bishop) board.getPieceAtCoords("h8");
        Bishop cleared = (Bishop) board.getPieceAtCoords("d4");
        assertEquals(whiteBishop, moved);
        assertNull(cleared);
    }
    @Test
    public void bishopCanMoveAndClean2() {
        ChessBoard board = new ChessBoard();
        Bishop whiteBishop = new Bishop(Color.WHITE, "white");
        board.add(whiteBishop, "g7");
        board.move(whiteBishop, "a1");
        Bishop moved = (Bishop) board.getPieceAtCoords("a1");
        Bishop cleared = (Bishop) board.getPieceAtCoords("g7");
        assertEquals(whiteBishop, moved);
        assertNull(cleared);
    }
    @Test
    public void bishopCanMoveAndClean3() {
        ChessBoard board = new ChessBoard();
        Bishop blackBishop = new Bishop(Color.BLACK, "black");
        board.add(blackBishop, "b7");
        board.move(blackBishop, "f3");
        Bishop moved = (Bishop) board.getPieceAtCoords("f3");
        Bishop cleared = (Bishop) board.getPieceAtCoords("b7");
        assertEquals(blackBishop, moved);
        assertNull(cleared);
    }
    @Test
    public void bishopCanMoveAndClean4() {
        ChessBoard board = new ChessBoard();
        Bishop blackBishop = new Bishop(Color.BLACK, "black");
        board.add(blackBishop, "f5");
        board.move(blackBishop, "e6");
        Bishop moved = (Bishop) board.getPieceAtCoords("e6");
        Bishop cleared = (Bishop) board.getPieceAtCoords("f5");
        assertEquals(blackBishop, moved);
        assertNull(cleared);
    }
    @Test
    public void bishopCantMove() {
        ChessBoard board = new ChessBoard();
        Bishop blackBishop = new Bishop(Color.BLACK, "black");
        board.add(blackBishop, "f5");
        Pawn whitePawn2 = new Pawn(Color.WHITE, "white");
        board.add(whitePawn2, "d7");
        board.move(blackBishop, "c8");
        Bishop canceled = (Bishop) board.getPieceAtCoords("c8");
        Bishop stayed = (Bishop) board.getPieceAtCoords("f5");
        Pawn stayedPawn = (Pawn) board.getPieceAtCoords("d7");
        assertEquals(blackBishop, stayed);
        assertEquals(whitePawn2, stayedPawn);
        assertNull(canceled);
    }
    @Test
    public void bishopCantMove2() {
        ChessBoard board = new ChessBoard();
        Bishop blackBishop = new Bishop(Color.BLACK, "black");
        board.add(blackBishop, "d1");
        Pawn whitePawn2 = new Pawn(Color.WHITE, "white");
        board.add(whitePawn2, "f3");
        board.move(blackBishop, "h5");
        Bishop canceled = (Bishop) board.getPieceAtCoords("h5");
        Bishop stayed = (Bishop) board.getPieceAtCoords("d1");
        Pawn stayedPawn = (Pawn) board.getPieceAtCoords("f3");
        assertEquals(blackBishop, stayed);
        assertEquals(whitePawn2, stayedPawn);
        assertNull(canceled);
    }
    @Test
    public void bishopInvalidMove() {
        ChessBoard board = new ChessBoard();
        Bishop blackBishop = new Bishop(Color.BLACK, "black");
        board.add(blackBishop, "d1");
        board.move(blackBishop, "d5");
        Bishop canceled = (Bishop) board.getPieceAtCoords("d5");
        Bishop stayed = (Bishop) board.getPieceAtCoords("d1");
        assertEquals(blackBishop, stayed);
        assertNull(canceled);
    }
    @Test
    public void bishopInvalidMove2() {
        ChessBoard board = new ChessBoard();
        Bishop blackBishop = new Bishop(Color.BLACK, "black");
        board.add(blackBishop, "d4");
        board.move(blackBishop, "a5");
        Bishop canceled = (Bishop) board.getPieceAtCoords("a5");
        Bishop stayed = (Bishop) board.getPieceAtCoords("d4");
        assertEquals(blackBishop, stayed);
        assertNull(canceled);
    }
    @Test
    public void bishopCorrectAttack() {
        ChessBoard board = new ChessBoard();
        Bishop blackBishop = new Bishop(Color.BLACK, "black");
        board.add(blackBishop, "f5");
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        board.add(whitePawn, "c8");
        board.move(blackBishop, "c8");
        Bishop moved = (Bishop) board.getPieceAtCoords("c8");
        Bishop cleared = (Bishop) board.getPieceAtCoords("f5");
        assertEquals(blackBishop, moved);
        assertNull(cleared);
    }
    @Test
    public void bishopCorrectAttack2() {
        ChessBoard board = new ChessBoard();
        Bishop blackBishop = new Bishop(Color.BLACK, "black");
        board.add(blackBishop, "h8");
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        board.add(whitePawn, "c3");
        board.move(blackBishop, "c3");
        Bishop moved = (Bishop) board.getPieceAtCoords("c3");
        Bishop cleared = (Bishop) board.getPieceAtCoords("h8");
        assertEquals(blackBishop, moved);
        assertNull(cleared);
    }
    @Test
    public void bishopCorrectAttack3() {
        ChessBoard board = new ChessBoard();
        Bishop blackBishop = new Bishop(Color.BLACK, "black");
        board.add(blackBishop, "d4");
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        board.add(whitePawn, "a7");
        board.move(blackBishop, "a7");
        Bishop moved = (Bishop) board.getPieceAtCoords("a7");
        Bishop cleared = (Bishop) board.getPieceAtCoords("d4");
        assertEquals(blackBishop, moved);
        assertNull(cleared);
    }
    @Test
    public void bishopCantAttack() {
        ChessBoard board = new ChessBoard();
        Bishop blackBishop = new Bishop(Color.BLACK, "black");
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        King king = new King(Color.WHITE, "white");
        board.add(blackBishop, "f5");
        board.add(whitePawn, "c8");
        board.add(king, "e6");
        board.attack(blackBishop, whitePawn, "c8");
        Bishop stayed = (Bishop) board.getPieceAtCoords("f5");
        Pawn stayedPawn = (Pawn) board.getPieceAtCoords("c8");
        King kingStayed = (King) board.getPieceAtCoords("e6");
        assertEquals(blackBishop, stayed);
        assertEquals(whitePawn, stayedPawn);
        assertEquals(king, kingStayed);
    }
    @Test
    public void bishopCantAttack2() {
        ChessBoard board = new ChessBoard();
        Bishop blackBishop = new Bishop(Color.BLACK, "black");
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        King king = new King(Color.WHITE, "white");
        board.add(blackBishop, "a1");
        board.add(whitePawn, "h8");
        board.add(king, "d4");
        board.attack(blackBishop, whitePawn, "h8");
        Bishop stayed = (Bishop) board.getPieceAtCoords("a1");
        Pawn stayedPawn = (Pawn) board.getPieceAtCoords("h8");
        King kingStayed = (King) board.getPieceAtCoords("d4");
        assertEquals(blackBishop, stayed);
        assertEquals(whitePawn, stayedPawn);
        assertEquals(king, kingStayed);
    }
    @Test
    public void bishopInvalidAttack() {
        ChessBoard board = new ChessBoard();
        Bishop blackBishop = new Bishop(Color.BLACK, "black");
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        King king = new King(Color.WHITE, "white");
        board.add(blackBishop, "a1");
        board.add(whitePawn, "c7");
        board.add(king, "d4");
        board.attack(blackBishop, whitePawn, "c7");
        Bishop stayed = (Bishop) board.getPieceAtCoords("a1");
        Pawn stayedPawn = (Pawn) board.getPieceAtCoords("c7");
        King kingStayed = (King) board.getPieceAtCoords("d4");
        assertEquals(blackBishop, stayed);
        assertEquals(whitePawn, stayedPawn);
        assertEquals(king, kingStayed);
    }
    @Test
    public void addQueen() {
        ChessBoard board = new ChessBoard();
        Queen blackQueen = new Queen(Color.BLACK, "black");
        board.add(blackQueen, "d4");
        Queen notNullQueen = (Queen) board.getPieceAtCoords("d4");
        assertEquals(blackQueen, notNullQueen);
    }
    @Test
    public void movedQueenAnClean() {
        ChessBoard board = new ChessBoard();
        Queen blackQueen = new Queen(Color.BLACK, "black");
        board.add(blackQueen, "d4");
        board.move(blackQueen, "d8");
        Queen movedQueen = (Queen) board.getPieceAtCoords("d8");
        Queen nullQueen = (Queen) board.getPieceAtCoords("d4");
        assertEquals(blackQueen, movedQueen);
        assertNull(nullQueen);
    }
    @Test
    public void movedQueenAnClean2() {
        ChessBoard board = new ChessBoard();
        Queen blackQueen = new Queen(Color.BLACK, "black");
        board.add(blackQueen, "d4");
        board.move(blackQueen, "d1");
        Queen movedQueen = (Queen) board.getPieceAtCoords("d1");
        Queen nullQueen = (Queen) board.getPieceAtCoords("d4");
        assertEquals(blackQueen, movedQueen);
        assertNull(nullQueen);
    }
    @Test
    public void movedQueenAnClean3() {
        ChessBoard board = new ChessBoard();
        Queen blackQueen = new Queen(Color.BLACK, "black");
        board.add(blackQueen, "d4");
        board.move(blackQueen, "a4");
        Queen movedQueen = (Queen) board.getPieceAtCoords("a4");
        Queen nullQueen = (Queen) board.getPieceAtCoords("d4");
        assertEquals(blackQueen, movedQueen);
        assertNull(nullQueen);
    }
    @Test
    public void movedQueenAnClean4() {
        ChessBoard board = new ChessBoard();
        Queen whiteQueen = new Queen(Color.WHITE, "white");
        board.add(whiteQueen, "d4");
        board.move(whiteQueen, "g4");
        Queen movedQueen = (Queen) board.getPieceAtCoords("g4");
        Queen nullQueen = (Queen) board.getPieceAtCoords("d4");
        assertEquals(whiteQueen, movedQueen);
        assertNull(nullQueen);
    }
    @Test
    public void movedQueenAnClean5() {
        ChessBoard board = new ChessBoard();
        Queen blackQueen = new Queen(Color.BLACK, "black");
        board.add(blackQueen, "d4");
        board.move(blackQueen, "a1");
        Queen movedQueen = (Queen) board.getPieceAtCoords("a1");
        Queen nullQueen = (Queen) board.getPieceAtCoords("d4");
        assertEquals(blackQueen, movedQueen);
        assertNull(nullQueen);
    }
    @Test
    public void movedQueenAnClean6() {
        ChessBoard board = new ChessBoard();
        Queen blackQueen = new Queen(Color.BLACK, "black");
        board.add(blackQueen, "d4");
        board.move(blackQueen, "g7");
        Queen movedQueen = (Queen) board.getPieceAtCoords("g7");
        Queen nullQueen = (Queen) board.getPieceAtCoords("d4");
        assertEquals(blackQueen, movedQueen);
        assertNull(nullQueen);
    }
    @Test
    public void movedQueenAnClean7() {
        ChessBoard board = new ChessBoard();
        Queen blackQueen = new Queen(Color.BLACK, "black");
        board.add(blackQueen, "d4");
        board.move(blackQueen, "a7");
        Queen movedQueen = (Queen) board.getPieceAtCoords("a7");
        Queen nullQueen = (Queen) board.getPieceAtCoords("d4");
        assertEquals(blackQueen, movedQueen);
        assertNull(nullQueen);
    }
    @Test
    public void movedQueenAnClean8() {
        ChessBoard board = new ChessBoard();
        Queen blackQueen = new Queen(Color.BLACK, "black");
        board.add(blackQueen, "d4");
        board.move(blackQueen, "g1");
        Queen movedQueen = (Queen) board.getPieceAtCoords("g1");
        Queen nullQueen = (Queen) board.getPieceAtCoords("d4");
        assertEquals(blackQueen, movedQueen);
        assertNull(nullQueen);
    }
    @Test
    public void movedQueenIncorrectMove() {
        ChessBoard board = new ChessBoard();
        Queen blackQueen = new Queen(Color.BLACK, "black");
        board.add(blackQueen, "d4");
        board.move(blackQueen, "h5");
        Queen stayedQueen = (Queen) board.getPieceAtCoords("d4");
        Queen nullQueen = (Queen) board.getPieceAtCoords("h5");
        assertEquals(blackQueen, stayedQueen);
        assertNull(nullQueen);
    }
    @Test
    public void queenBlockedMove() {
        ChessBoard board = new ChessBoard();
        Queen blackQueen = new Queen(Color.BLACK, "black");
        board.add(blackQueen, "d4");
        Pawn pawn = new Pawn(Color.BLACK, "black");
        board.add(pawn, "f6");
        board.move(blackQueen, "h8");
        Queen stayedQueen = (Queen) board.getPieceAtCoords("d4");
        Queen nullQueen = (Queen) board.getPieceAtCoords("h8");
        Pawn stayedPawn = (Pawn) board.getPieceAtCoords("f6");
        assertEquals(pawn, stayedPawn);
        assertEquals(blackQueen, stayedQueen);
        assertNull(nullQueen);
    }

    @Test
    public void queenCorrectAttack() {
        ChessBoard board = new ChessBoard();
        Queen blackQueen = new Queen(Color.BLACK, "black");
        board.add(blackQueen, "d4");
        Pawn pawn = new Pawn(Color.WHITE, "white");
        board.add(pawn, "f6");
        board.attack(blackQueen, pawn, "f6");
        Queen movedQueen = (Queen) board.getPieceAtCoords("f6");
        Queen nullQueen = (Queen) board.getPieceAtCoords("d4");
        assertEquals(blackQueen, movedQueen);
        assertNull(nullQueen);
    }
    @Test
    public void queenCorrectAttack2() {
        ChessBoard board = new ChessBoard();
        Queen blackQueen = new Queen(Color.BLACK, "black");
        board.add(blackQueen, "d4");
        Pawn pawn = new Pawn(Color.WHITE, "white");
        board.add(pawn, "a4");
        board.attack(blackQueen, pawn, "a4");
        Queen movedQueen = (Queen) board.getPieceAtCoords("a4");
        Queen nullQueen = (Queen) board.getPieceAtCoords("d4");
        assertEquals(blackQueen, movedQueen);
        assertNull(nullQueen);
    }
    @Test
    public void queenCantAttackSameColor() {
        ChessBoard board = new ChessBoard();
        Queen blackQueen = new Queen(Color.BLACK, "black");
        board.add(blackQueen, "d4");
        Pawn pawn = new Pawn(Color.BLACK, "black");
        board.add(pawn, "a4");
        board.attack(blackQueen, pawn, "a4");
        Pawn stayedPawn = (Pawn) board.getPieceAtCoords("a4");
        Queen stayedQueen = (Queen) board.getPieceAtCoords("d4");
        assertEquals(blackQueen, stayedQueen);
        assertEquals(pawn, stayedPawn);
    }
    @Test
    public void queenCantAttackSameColor2() {
        ChessBoard board = new ChessBoard();
        Queen blackQueen = new Queen(Color.BLACK, "black");
        board.add(blackQueen, "d4");
        Pawn pawn = new Pawn(Color.BLACK, "black");
        board.add(pawn, "e3");
        board.attack(blackQueen, pawn, "e3");
        Pawn stayedPawn = (Pawn) board.getPieceAtCoords("e3");
        Queen stayedQueen = (Queen) board.getPieceAtCoords("d4");
        assertEquals(blackQueen, stayedQueen);
        assertEquals(pawn, stayedPawn);
    }
    @Test
    public void queenBlockedAttack() {
        ChessBoard board = new ChessBoard();
        Queen blackQueen = new Queen(Color.BLACK, "black");
        board.add(blackQueen, "d4");
        Pawn pawn = new Pawn(Color.WHITE, "white");
        board.add(pawn, "h8");
        King king = new King(Color.WHITE, "white");
        board.add(king, "f6");
        board.attack(blackQueen, pawn, "h8");
        Queen stayedQueen = (Queen) board.getPieceAtCoords("d4");
        Pawn stayedPawn = (Pawn) board.getPieceAtCoords("h8");
        King stayedKing = (King) board.getPieceAtCoords("f6");
        assertEquals(pawn, stayedPawn);
        assertEquals(blackQueen, stayedQueen);
        assertEquals(king, stayedKing);
    }
    @Test
    public void queenBlockedAttack2() {
        ChessBoard board = new ChessBoard();
        Queen blackQueen = new Queen(Color.BLACK, "black");
        board.add(blackQueen, "d4");
        Pawn pawn = new Pawn(Color.WHITE, "white");
        board.add(pawn, "d7");
        King king = new King(Color.WHITE, "white");
        board.add(king, "d6");
        board.attack(blackQueen, pawn, "d7");
        Queen stayedQueen = (Queen) board.getPieceAtCoords("d4");
        Pawn stayedPawn = (Pawn) board.getPieceAtCoords("d7");
        King stayedKing = (King) board.getPieceAtCoords("d6");
        assertEquals(pawn, stayedPawn);
        assertEquals(blackQueen, stayedQueen);
        assertEquals(king, stayedKing);
    }
    @Test
    public void queenIncorrectFieldAttack() {
        ChessBoard board = new ChessBoard();
        Queen blackQueen = new Queen(Color.BLACK, "black");
        board.add(blackQueen, "d4");
        Pawn pawn = new Pawn(Color.WHITE, "white");
        board.add(pawn, "f2");
        board.attack(blackQueen, pawn, "h6");
        Queen stayedQueen = (Queen) board.getPieceAtCoords("d4");
        Pawn stayedPawn = (Pawn) board.getPieceAtCoords("f2");
        assertEquals(blackQueen, stayedQueen);
        assertEquals(pawn, stayedPawn);
    }
    @Test
    public void castlingWhiteRight() {
        ChessBoard board = new ChessBoard();
        King king = new King(Color.WHITE, "white");
        board.add(king, "e1");
        Rook rook = new Rook(Color.WHITE, "white");
        board.add(rook, "h1");
        board.castling(king, rook);
        King kingMoved = (King) board.getPieceAtCoords("g1");
        Rook rookMoved = (Rook) board.getPieceAtCoords("f1");
        King nullKing = (King) board.getPieceAtCoords("e1");
        Rook nullRook = (Rook) board.getPieceAtCoords("h1");
        assertNull(nullRook);
        assertNull(nullKing);
        assertEquals(king, kingMoved);
        assertEquals(rook, rookMoved);
    }
    @Test
    public void castlingWhiteLeft() {
        ChessBoard board = new ChessBoard();
        King king = new King(Color.WHITE, "white");
        board.add(king, "e1");
        Rook rook = new Rook(Color.WHITE, "white");
        board.add(rook, "a1");
        board.castling(king, rook);
        King kingMoved = (King) board.getPieceAtCoords("c1");
        Rook rookMoved = (Rook) board.getPieceAtCoords("d1");
        King nullKing = (King) board.getPieceAtCoords("e1");
        Rook nullRook = (Rook) board.getPieceAtCoords("a1");
        assertNull(nullRook);
        assertNull(nullKing);
        assertEquals(king, kingMoved);
        assertEquals(rook, rookMoved);
    }
    @Test
    public void castlingBlackRight() {
        ChessBoard board = new ChessBoard();
        King king = new King(Color.BLACK, "black");
        board.add(king, "e8");
        Rook rook = new Rook(Color.BLACK, "black");
        board.add(rook, "h8");
        board.castling(king, rook);
        King kingMoved = (King) board.getPieceAtCoords("g8");
        Rook rookMoved = (Rook) board.getPieceAtCoords("f8");
        King nullKing = (King) board.getPieceAtCoords("e8");
        Rook nullRook = (Rook) board.getPieceAtCoords("h8");
        assertNull(nullRook);
        assertNull(nullKing);
        assertEquals(king, kingMoved);
        assertEquals(rook, rookMoved);
    }
    @Test
    public void castlingBlackLeft() {
        ChessBoard board = new ChessBoard();
        King king = new King(Color.BLACK, "black");
        board.add(king, "e8");
        Rook rook = new Rook(Color.BLACK, "black");
        board.add(rook, "a8");
        board.castling(king, rook);
        King kingMoved = (King) board.getPieceAtCoords("c8");
        Rook rookMoved = (Rook) board.getPieceAtCoords("d8");
        King nullKing = (King) board.getPieceAtCoords("e8");
        Rook nullRook = (Rook) board.getPieceAtCoords("a8");
        assertNull(nullRook);
        assertNull(nullKing);
        assertEquals(king, kingMoved);
        assertEquals(rook, rookMoved);
    }
    @Test
    public void castlingWhiteRightBlocked() {
        ChessBoard board = new ChessBoard();
        King king = new King(Color.WHITE, "white");
        board.add(king, "e1");
        Rook rook = new Rook(Color.WHITE, "white");
        board.add(rook, "h1");
        Knight knight = new Knight(Color.WHITE, "white");
        board.add(knight, "g1");
        board.castling(king, rook);
        King kingStayed = (King) board.getPieceAtCoords("e1");
        Rook rookStayed = (Rook) board.getPieceAtCoords("h1");
        Knight knightStayed = (Knight) board.getPieceAtCoords("g1");
        assertEquals(king, kingStayed);
        assertEquals(rook, rookStayed);
        assertEquals(knight, knightStayed);
    }
    @Test
    public void castlingWhiteLeftBlocked() {
        ChessBoard board = new ChessBoard();
        King king = new King(Color.WHITE, "white");
        board.add(king, "e1");
        Rook rook = new Rook(Color.WHITE, "white");
        board.add(rook, "a1");
        Knight knight = new Knight(Color.WHITE, "white");
        board.add(knight, "b1");
        board.castling(king, rook);
        King kingStayed = (King) board.getPieceAtCoords("e1");
        Rook rookStayed = (Rook) board.getPieceAtCoords("a1");
        Knight knightStayed = (Knight) board.getPieceAtCoords("b1");
        assertEquals(king, kingStayed);
        assertEquals(rook, rookStayed);
        assertEquals(knight, knightStayed);
    }
    @Test
    public void castlingBlackRightBlocked() {
        ChessBoard board = new ChessBoard();
        King king = new King(Color.BLACK, "black");
        board.add(king, "e8");
        Rook rook = new Rook(Color.BLACK, "black");
        board.add(rook, "h8");
        Knight knight = new Knight(Color.BLACK, "black");
        board.add(knight, "g8");
        board.castling(king, rook);
        King kingStayed = (King) board.getPieceAtCoords("e8");
        Rook rookStayed = (Rook) board.getPieceAtCoords("h8");
        Knight knightStayed = (Knight) board.getPieceAtCoords("g8");
        assertEquals(king, kingStayed);
        assertEquals(rook, rookStayed);
        assertEquals(knight, knightStayed);
    }
    @Test
    public void castlingBlackLeftBlocked() {
        ChessBoard board = new ChessBoard();
        King king = new King(Color.BLACK, "black");
        board.add(king, "e8");
        Rook rook = new Rook(Color.BLACK, "black");
        board.add(rook, "a8");
        Knight knight = new Knight(Color.BLACK, "black");
        board.add(knight, "b8");
        board.castling(king, rook);
        King kingStayed = (King) board.getPieceAtCoords("e8");
        Rook rookStayed = (Rook) board.getPieceAtCoords("a8");
        Knight knightStayed = (Knight) board.getPieceAtCoords("b8");
        assertEquals(king, kingStayed);
        assertEquals(rook, rookStayed);
        assertEquals(knight, knightStayed);
    }
    @Test
    public void castlingBlackLeftWrongColor() {
        ChessBoard board = new ChessBoard();
        King king = new King(Color.BLACK, "black");
        board.add(king, "e8");
        Rook rook = new Rook(Color.WHITE, "white");
        board.add(rook, "a8");
        board.castling(king, rook);
        King kingStayed = (King) board.getPieceAtCoords("e8");
        Rook rookStayed = (Rook) board.getPieceAtCoords("a8");
        assertEquals(king, kingStayed);
        assertEquals(rook, rookStayed);
    }

    @Test
    public void correctCoordsToPromoteWhite() {
        ChessBoard board = new ChessBoard();
        int[] emptyString = null;
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        board.add(whitePawn, "d7");
        board.move(whitePawn, "d8");
        int[] coordsToPromote = board.getCoordsToPromote(whitePawn, emptyString);
        String field = board.turnIndexIntoField(coordsToPromote);
        assertEquals("d8", field);
    }

    @Test
    public void wrongCoordsToPromote1() {
        ChessBoard board = new ChessBoard();
        int[] emptyCoords = null;
        Pawn blackPawn = new Pawn(Color.BLACK, "black");
        board.add(blackPawn, "d7");
        board.move(blackPawn, "d8");
        assertNotEquals("d8", board.getCoordsToPromote(blackPawn, emptyCoords));
        assertEquals(emptyCoords, board.getCoordsToPromote(blackPawn, emptyCoords));
    }

    @Test
    public void correctCoordsToPromoteBlack() {
        ChessBoard board = new ChessBoard();
        int[] emptyCoords = null;
        Pawn blackPawn = new Pawn(Color.BLACK, "black");
        board.add(blackPawn, "d2");
        board.move(blackPawn, "d1");
        int[] coordsToPromote = board.getCoordsToPromote(blackPawn, emptyCoords);
        String field = board.turnIndexIntoField(coordsToPromote);
        assertEquals("d1", field);
    }

    @Test
    public void wrongCoordsToPromote2() {
        ChessBoard board = new ChessBoard();
        int[] emptyCoords = null;
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        board.add(whitePawn, "d2");
        board.move(whitePawn, "d1");
        assertNotEquals("d1", board.getCoordsToPromote(whitePawn, emptyCoords));
        assertEquals(emptyCoords, board.getCoordsToPromote(whitePawn, emptyCoords));
    }

    @Test
    public void correctCoordsToPromoteWhiteAndDelete() {
        ChessBoard board = new ChessBoard();
        int[] emptyCoords = null;
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        board.add(whitePawn, "d7");
        board.move(whitePawn, "d8");
        int[] coordsToPromote = board.getCoordsToPromote(whitePawn, emptyCoords);
        String field = board.turnIndexIntoField(coordsToPromote);
        if ("d8".equals(field)) {
            board.cleanFieldAtCoords("d8");
        }
        assertNull(board.getPieceAtCoords("d8"));
    }

    @Test
    public void wrongCoordsToPromoteWhiteAndNotDelete() {
        ChessBoard board = new ChessBoard();
        int[] emptyCoords = null;
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        board.add(whitePawn, "d6");
        board.move(whitePawn, "d7");
        int[] fieldToPromote = board.turnFieldIntoIndex("d8");
        if (Arrays.equals(fieldToPromote, board.getCoordsToPromote(whitePawn, emptyCoords))) {
            board.cleanFieldAtCoords("d7");
        }
        assertNotNull(board.getPieceAtCoords("d7"));
        assertEquals(whitePawn, board.getPieceAtCoords("d7"));
    }

    @Test
    public void correctCoordsToPromoteBlackAndDelete() {
        ChessBoard board = new ChessBoard();
        int[] emptyCoords = null;
        Pawn blackPawn = new Pawn(Color.BLACK, "black");
        board.add(blackPawn, "d2");
        board.move(blackPawn, "d1");
        int[] coordsToPromote = board.getCoordsToPromote(blackPawn, emptyCoords);
        String field = board.turnIndexIntoField(coordsToPromote);
        if ("d1".equals(field)) {
            board.cleanFieldAtCoords("d1");
        }
        assertNull(board.getPieceAtCoords("d1"));
    }

    @Test
    public void wrongCoordsToPromoteBlackAndNotDelete() {
        ChessBoard board = new ChessBoard();
        int[] emptyCoords = null;
        Pawn blackPawn = new Pawn(Color.BLACK, "black");
        board.add(blackPawn, "d3");
        board.move(blackPawn, "d2");
        int[] fieldToPromote = board.turnFieldIntoIndex("d2");
        if (Arrays.equals(fieldToPromote, board.getCoordsToPromote(blackPawn, emptyCoords))) {
            board.cleanFieldAtCoords("d2");
        }
        assertNotNull(board.getPieceAtCoords("d2"));
        assertEquals(blackPawn, board.getPieceAtCoords("d2"));
    }

    @Test
    public void correctCoordsAndPromoteWhiteToQueen() {
        ChessBoard board = new ChessBoard();
        int[] emptyCoords = null;
        Pawn whitePawn = new Pawn(Color.WHITE, "white");
        Queen whiteQueen = new Queen(Color.WHITE, "wQ");
        board.add(whitePawn, "d7");
        board.move(whitePawn, "d8");
        int[] coordsToPromote = board.getCoordsToPromote(whitePawn, emptyCoords);
        String field = board.turnIndexIntoField(coordsToPromote);
        if ("d8".equals(field)) {
            board.cleanFieldAtCoords("d8");
            board.add(whiteQueen, "d8");
        }
        assertEquals(whiteQueen, board.getPieceAtCoords("d8"));
    }

    @Test
    public void correctCoordsAndPromoteBlackToRook() {
        ChessBoard board = new ChessBoard();
        int[] emptyCoords = null;
        Pawn blackPawn = new Pawn(Color.BLACK, "black");
        Rook blackRook = new Rook(Color.BLACK, "bR");
        board.add(blackPawn, "d2");
        board.move(blackPawn, "d1");
        int[] coordsToPromote = board.getCoordsToPromote(blackPawn, emptyCoords);
        String field = board.turnIndexIntoField(coordsToPromote);
        if ("d1".equals(field)) {
            board.cleanFieldAtCoords("d1");
            board.add(blackRook, "d1");
        }
        assertEquals(blackRook, board.getPieceAtCoords("d1"));
    }
}