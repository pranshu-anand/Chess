package src.piece;

import java.util.ArrayList;
import java.util.List;

import src.game.Color;
import src.game.PieceType;
import src.game.board.Position;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color, PieceType.BISHOP);
    }

    protected List<Position> getPath(Position fromPosition, Position toPosition) {
        List<Position> path = new ArrayList<Position>();

        int xDelta = fromPosition.getRow() - toPosition.getRow();
        int yDelta = fromPosition.getColumn() - toPosition.getColumn();

        if (Math.abs(xDelta) == Math.abs(yDelta)) {
            if (xDelta > 0 && yDelta > 0) {
                for (int i = 1; i <= xDelta; i++)
                    path.add(new Position(fromPosition.getRow() + i, fromPosition.getColumn() + i));
            } else if (xDelta > 0 && yDelta < 0) {
                for (int i = 1; i <= Math.abs(xDelta); i++)
                    path.add(new Position(fromPosition.getRow() + i, fromPosition.getColumn() - i));
            } else if (xDelta < 0 && yDelta < 0) {
                for (int i = 1; i <= xDelta; i++)
                    path.add(new Position(fromPosition.getRow() - i, fromPosition.getColumn() - i));
            } else if (xDelta < 0 && yDelta > 0) {
                for (int i = 1; i <= Math.abs(xDelta); i++)
                    path.add(new Position(fromPosition.getRow() - i, fromPosition.getColumn() + i));
            }
        }

        return path;
    }

    protected boolean isValidDirection(Position fromPosition, Position toPosition) {
        int xDelta = fromPosition.getRow() - toPosition.getRow();
        int yDelta = fromPosition.getColumn() - toPosition.getColumn();

        if ((Math.abs(xDelta) == Math.abs(yDelta)))
            return true;

        return false;
    }

}
