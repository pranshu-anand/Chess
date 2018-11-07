package src.game.board;

import src.piece.Piece;

public class Spot {

    Position position;
    Piece piece = null;
    Boolean isOccupied = false;

    Spot(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;
        if(this.piece != null) {
            isOccupied = true;
        }
    }

    Spot(Position position) {
        this.position = position;
        this.piece = null;
        this.isOccupied = false;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        if(this.piece != null) {
            isOccupied = true;
        } else {
            isOccupied = false;
        }
    }

    public void displaySpot() {
        if (this.isOccupied) {
            this.piece.displayPiece();
        } else {
            System.out.print("       ");
        }
    }

    public Position getPosition() {
        return this.position;
    }
}
