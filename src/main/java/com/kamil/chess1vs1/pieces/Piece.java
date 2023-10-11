package com.kamil.chess1vs1.pieces;

public abstract class Piece implements IPiece {
    protected Color color;
    protected int[] field; //zamieniæ na String
    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int[] getField() {
        return field;
    }

    public void setField(int[] field) {
        this.field = field;
    }
}
