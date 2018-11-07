package src.piece;

import java.util.ArrayList;
import java.util.List;

import src.game.Color;
import src.game.PieceType;
import src.game.board.Position;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color, PieceType.QUEEN);
    }

    protected List<Position> getPath(Position fromPosition, Position toPosition) {
        List<Position> path = new ArrayList<Position>();

        int xDelta = fromPosition.getRow() - toPosition.getRow();
        int yDelta = fromPosition.getColumn() - toPosition.getColumn();

        if (xDelta == 0) {
            if (yDelta > 0) {
                for (int i = 1; i <= yDelta; i++)
                    path.add(new Position(fromPosition.getRow(), fromPosition.getColumn() + i));
            } else {
                for (int i = 1; i <= Math.abs(yDelta); i++)
                    path.add(new Position(fromPosition.getRow(), fromPosition.getColumn() - i));
            }
        }

        if (yDelta == 0) {
            if (xDelta > 0) {
                for (int i = 1; i <= xDelta; i++)
                    path.add(new Position(fromPosition.getRow() + i, fromPosition.getColumn()));
            } else {
                for (int i = 1; i <= Math.abs(xDelta); i++)
                    path.add(new Position(fromPosition.getRow() - i, fromPosition.getColumn()));
            }
        }

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

        if ((xDelta == 0) || (yDelta == 0) || (Math.abs(xDelta) == Math.abs(yDelta)))
            return true;

        return false;
    }

}
