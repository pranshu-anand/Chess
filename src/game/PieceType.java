package src.game;

public enum PieceType {
    KING("Kq"), QUEEN("Qn"), ROOK("Rk"), BISHOP("Bp"), KNIGHT("Kt"), PAWN("Pn");

    private String identifier;

    PieceType(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return this.identifier;
    }
}
