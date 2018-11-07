package src.game;

import java.util.List;

import src.game.board.Board;
import src.game.board.Position;
import src.game.board.Spot;
import src.piece.Piece;

public class Player {

    final Color color;
    List<Piece> pieces;
    List<Spot> occupiedSlots;

    public Player(Color color, List<Piece> pieces) {
        this.color = color;
        this.pieces = pieces;
    }

    public boolean isCheckMate(Board board) {
        // TODO Auto-generated method stub
        return false;
    }

    public Boolean move(Position fromPosition, Position toPosition, Board board) {
        Piece piece = board.getPieceAtSpot(fromPosition);
        if (piece == null) {
            return false;
        }
        if (piece.getColor() != this.color) {
            return false;
        }

        Boolean isValidMove = piece.isValidMove(fromPosition, toPosition, board);

        if (isValidMove) {
            return true;
        }
        return false;
    }

    public boolean isCheck(Board board) {
        // TODO Auto-generated method stub
        return false;
    }

    public List<Spot> getOccupiedSpots() {
        return this.occupiedSlots;
    }

    public Spot getKingSpot() {
        // TODO Auto-generated method stub
        return null;
    }
}
