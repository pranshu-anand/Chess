package src.piece;

import java.util.ArrayList;
import java.util.List;

import src.game.Color;
import src.game.PieceType;
import src.game.board.Position;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color, PieceType.ROOK);
    }

    protected List<Position> getPath(Position fromPosition, Position toPosition) {
        List<Position> path = new ArrayList<Position>();

        int rowDelta = toPosition.getRow() - fromPosition.getRow();
        int yDelta = toPosition.getColumn() - fromPosition.getColumn();

        if (rowDelta == 0) {
            if (yDelta > 0) {
                for (int i = 1; i <= yDelta; i++)
                    path.add(new Position(fromPosition.getRow(), fromPosition.getColumn() + i));
            } else {
                for (int i = 1; i <= Math.abs(yDelta); i++)
                    path.add(new Position(fromPosition.getRow(), fromPosition.getColumn() - i));
            }
        }

        if (yDelta == 0) {
            if (rowDelta > 0) {
                for (int i = 1; i <= rowDelta; i++)
                    path.add(new Position(fromPosition.getRow() + i, fromPosition.getColumn()));
            } else {
                for (int i = 1; i <= Math.abs(rowDelta); i++)
                    path.add(new Position(fromPosition.getRow() - i, fromPosition.getColumn()));
            }
        }

        return path;
    }

    protected boolean isValidDirection(Position fromPosition, Position toPosition) {
        int xDelta = fromPosition.getRow() - toPosition.getRow();
        int yDelta = fromPosition.getColumn() - toPosition.getColumn();

        if ((xDelta == 0) || (yDelta == 0))
            return true;

        return false;
    }

}
