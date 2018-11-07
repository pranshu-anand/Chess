package src.piece;

import java.util.List;

import src.game.Color;
import src.game.PieceType;
import src.game.board.Board;
import src.game.board.Position;

public abstract class Piece {

    final Color color;
    final PieceType pieceType;
    final String displayIdentifier;

    Piece(Color color, PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
        displayIdentifier = color.getIdentifier() + "_" + pieceType.getIdentifier();
    }

    public void displayPiece() {
        System.out.print("   " + displayIdentifier);
    }

    public Color getColor() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public String getDisplayIdentifier() {
        return displayIdentifier;
    }

    public Boolean isValidMove(Position fromPosition, Position toPosition, Board board) {
        if (!isValidDirection(fromPosition, toPosition)) {
            return false;
        }
        List<Position> path = getPath(fromPosition, toPosition);
        if (isPathObstructed(path, board)) {
            return false;
        }
        return true;
    }

    protected abstract List<Position> getPath(Position fromPosition, Position toPosition);

    protected abstract boolean isValidDirection(Position fromPosition, Position toPosition);

    protected boolean isPathObstructed(List<Position> path, Board board) {
        if (path == null)
            return true;

        for (int i = 0; i < path.size() - 1; i++) {
            Piece pieceAtSpot = board.getPieceAtSpot(path.get(i));
            if (pieceAtSpot == null)
                continue;
            else
                return true;
        }

        Piece pieceAtEndOfPath = board.getPieceAtSpot(path.get(path.size() - 1));
        if (pieceAtEndOfPath != null && pieceAtEndOfPath.color == this.color)
            return true;

        return false;
    }

}
