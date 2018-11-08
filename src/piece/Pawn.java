package src.piece;

import java.util.ArrayList;
import java.util.List;

import src.game.Color;
import src.game.PieceType;
import src.game.board.Board;
import src.game.board.Position;

public class Pawn extends Piece {

    Boolean isFirstMove;

    public Pawn(Color color) {
        super(color, PieceType.PAWN);
        isFirstMove = true;
    }

    @Override
    public Boolean isValidMove(Position fromPosition, Position toPosition, Board board) {
        this.isFirstMove = false;

        if (this.getColor() == Color.WHITE && fromPosition.getRow() == 2)
            isFirstMove = true;

        if (this.getColor() == Color.BLACK && fromPosition.getRow() == board.getSize() - 1)
            isFirstMove = true;

        if (!isValidDirection(fromPosition, toPosition)) {
            return false;
        }

        List<Position> path = getPath(fromPosition, toPosition);
        if (isPathObstructed(path, board)) {
            return false;
        }

        int xDelta = toPosition.getRow() - fromPosition.getRow();
        int yDelta = toPosition.getColumn() - fromPosition.getColumn();

        Piece pieceAtToPosition = board.getPieceAtSpot(toPosition);

        if (yDelta == 0) {
            if (pieceAtToPosition != null)
                return false;
        }

        if (this.color == Color.WHITE) {
            if (xDelta == 1 && (yDelta == -1 || yDelta == 1)) {
                if (pieceAtToPosition == null || pieceAtToPosition.getColor() == this.color)
                    return false;
            }
        }

        if (this.color == Color.BLACK) {
            if (xDelta == -1 && (yDelta == -1 || yDelta == 1)) {
                if (pieceAtToPosition == null || pieceAtToPosition.getColor() == this.color)
                    return false;
            }
        }
        return true;
    }

    @Override
    protected List<Position> getPath(Position fromPosition, Position toPosition) {
        List<Position> path = new ArrayList<Position>();

        int xDelta = toPosition.getRow() - fromPosition.getRow();
        int yDelta = toPosition.getColumn() - fromPosition.getColumn();

        if (yDelta == 0 && this.getIsFirstMove() && Math.abs(xDelta) == 2)
            path.add(new Position((fromPosition.getRow() + toPosition.getRow()) / 2, fromPosition.getColumn()));

        path.add(toPosition);
        return path;
    }

    @Override
    protected boolean isValidDirection(Position fromPosition, Position toPosition) {
        int xDelta = toPosition.getRow() - fromPosition.getRow();
        int yDelta = toPosition.getColumn() - fromPosition.getColumn();

        if (this.color == Color.WHITE) {
            if (yDelta == 0) {
                if (xDelta == 1 || (this.getIsFirstMove() && xDelta == 2))
                    return true;
            }
            if (xDelta == 1 && (yDelta == -1 || yDelta == 1)) {
                return true;
            }
        }

        if (this.color == Color.BLACK) {
            if (yDelta == 0) {
                if (xDelta == -1 || (this.getIsFirstMove() && xDelta == -2))
                    return true;
            }
            if (xDelta == -1 && (yDelta == -1 || yDelta == 1)) {
                return true;
            }
        }

        return false;
    }

    private boolean getIsFirstMove() {
        return this.isFirstMove;
    }
}
