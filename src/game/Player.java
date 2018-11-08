package src.game;

import java.util.ArrayList;
import java.util.List;

import src.game.board.Board;
import src.game.board.Position;
import src.game.board.Spot;
import src.piece.Piece;

public class Player {

    final Color color;
    List<Piece> piecesNotOnBoard;
    List<Spot> occupiedSpots;
    Spot kingSpot;

    public Player(Color color, List<Spot> occupiedSpots) {
        this.color = color;
        this.piecesNotOnBoard = new ArrayList<Piece>();
        this.occupiedSpots = occupiedSpots;
        for (Spot spot : occupiedSpots) {
            if (spot.getPiece().getPieceType() == PieceType.KING) {
                kingSpot = spot;
                return;
            }
        }
    }

    public boolean isCheckMate(Board board) {
        // TODO Auto-generated method stub
        return false;
    }

    public Boolean isValidMove(Position fromPosition, Position toPosition, Board board) {
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
        return false;
    }

    public List<Spot> getOccupiedSpots() {
        return this.occupiedSpots;
    }

    public Spot getKingSpot() {
        return kingSpot;
    }

    public void setKingSpot(Spot kingSpot) {
        this.kingSpot = kingSpot;
    }

    public void removeFromOccupiedSpots(Spot spot) {
        if (spot != null) {
            occupiedSpots.remove(spot);
            piecesNotOnBoard.add(spot.getPiece());
        }
    }

    public void addToOccupiedSpots(Position position, Piece piece) {
        if (piecesNotOnBoard.remove(piece))
            occupiedSpots.add(new Spot(position, piece));
    }

    public void moveOccupiedSpots(Position position, Piece piece) {
        if (piecesNotOnBoard.remove(piece))
            occupiedSpots.add(new Spot(position, piece));
    }

}
