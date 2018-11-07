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
        if (!isValidDirection(fromPosition, toPosition)) {
            return false;
        }
System.out.println("111");
        List<Position> path = getPath(fromPosition, toPosition);
        if (isPathObstructed(path, board)) {
            return false;
        }
        System.out.println("222");

        int xDelta = fromPosition.getRow() - toPosition.getRow();
        int yDelta = fromPosition.getColumn() - toPosition.getColumn();

        Piece pieceAtToPosition = board.getPieceAtSpot(toPosition);

        if (yDelta == 0) {
            System.out.println("333");

            if (pieceAtToPosition != null)
                return false;
        }

        if (this.color == Color.WHITE) {
            System.out.println("444");

            if (xDelta == -1 && (yDelta == -1 || yDelta == 1)) {
                if (pieceAtToPosition == null || pieceAtToPosition.getColor() == this.color)
                    return false;
            }
        }

        if (this.color == Color.BLACK) {
            System.out.println("555");

            if (xDelta == 1 && (yDelta == -1 || yDelta == 1)) {
                if (pieceAtToPosition == null || pieceAtToPosition.getColor() == this.color)
                    return false;
            }
        }

        isFirstMove = false;
        return true;
    }

    protected List<Position> getPath(Position fromPosition, Position toPosition) {
        List<Position> path = new ArrayList<Position>();

        int xDelta = fromPosition.getRow() - toPosition.getRow();
        int yDelta = fromPosition.getColumn() - toPosition.getColumn();

        if (yDelta == 0 && this.getIsFirstMove() && xDelta == -2)
            path.add(new Position((fromPosition.getRow() + fromPosition.getColumn()) / 2, fromPosition.getColumn()));

        path.add(toPosition);

        return path;

    }

    protected boolean isValidDirection(Position fromPosition, Position toPosition) {
        int xDelta = fromPosition.getRow() - toPosition.getRow();
        int yDelta = fromPosition.getColumn() - toPosition.getColumn();

        if (this.color == Color.WHITE) {
            if (yDelta == 0) {
                if (xDelta == -1 || (this.getIsFirstMove() && xDelta == -2))
                    return true;
            }
            if (xDelta == -1 && (yDelta == -1 || yDelta == 1)) {
                return true;
            }
        }

        if (this.color == Color.BLACK) {
            if (yDelta == 0) {
                if (xDelta == 1 || (this.getIsFirstMove() && xDelta == 2))
                    return true;
            }
            if (xDelta == 1 && (yDelta == -1 || yDelta == 1)) {
                return true;
            }
        }

        return false;
    }

    private boolean getIsFirstMove() {
        return this.isFirstMove;
    }

}
