package kamil.chess1vs1.pieces;

import java.io.Serializable;

public abstract class Piece implements IPiece, TurningCoordinates, Serializable {
    protected Color color;
    protected String name;
    protected int[] field;
    public Piece(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public int[] getField() {
        return field;
    }

    public void setField(int[] field) {
        this.field = field;
    }
}
