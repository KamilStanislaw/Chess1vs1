package kamil.chess1vs1;

import kamil.chess1vs1.logic.GameManager;
import kamil.chess1vs1.pieces.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class GameLogicTest {
    @Test
    public void canAddAllPieces1() {
        GameManager gameManager = new GameManager();
        gameManager.run();
        Piece c8 = gameManager.getChessBoard().getPieceAtCoords("c8");
        Bishop blackBishop = new Bishop(Color.BLACK);
        assertThat(blackBishop.getColor()).isEqualTo(c8.getColor());
        assertThat(c8.getField()).isEqualTo(gameManager.getChessBoard().turnFieldIntoIndex("c8"));
        assertThat(c8 instanceof Bishop);
    }

    @Test
    public void canAddAllPieces2() {
        GameManager gameManager = new GameManager();
        gameManager.run();
        Piece f7 = gameManager.getChessBoard().getPieceAtCoords("f7");
        Pawn blackPawn = new Pawn(Color.BLACK);
        assertThat(blackPawn.getColor()).isEqualTo(f7.getColor());
        assertThat(f7.getField()).isEqualTo(gameManager.getChessBoard().turnFieldIntoIndex("f7"));
        assertThat(f7 instanceof Pawn);
    }

    @Test
    public void canAddAllPieces3() {
        GameManager gameManager = new GameManager();
        gameManager.run();
        Piece a1 = gameManager.getChessBoard().getPieceAtCoords("a1");
        Rook whiteRook = new Rook(Color.WHITE);
        assertThat(whiteRook.getColor()).isEqualTo(a1.getColor());
        assertThat(a1.getField()).isEqualTo(gameManager.getChessBoard().turnFieldIntoIndex("a1"));
        assertThat(a1 instanceof Rook);
    }
}
