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

    @Override
    protected List<Position> getPath(Position fromPosition, Position toPosition) {
        List<Position> path = new ArrayList<Position>();

        int xDelta = toPosition.getRow() - fromPosition.getRow();
        int yDelta = toPosition.getColumn() - fromPosition.getColumn();

        int signinDirectionOfRow = 0;
        int signinDirectionOfColumn = 0;
        int delta = 0;

        if (xDelta == 0) {
            signinDirectionOfColumn = yDelta / Math.abs(yDelta);
            delta = Math.abs(yDelta);
        } else if (yDelta == 0) {
            signinDirectionOfRow = xDelta / Math.abs(xDelta);
            delta = Math.abs(xDelta);
        } else if (Math.abs(xDelta) == Math.abs(yDelta)) {
            signinDirectionOfRow = xDelta / Math.abs(xDelta);
            signinDirectionOfColumn = yDelta / Math.abs(yDelta);
            delta = Math.abs(yDelta);
        }

        for (int i = 1; i <= delta; i++)
            path.add(new Position(fromPosition.getRow() + i * signinDirectionOfRow,
                    fromPosition.getColumn() + i * signinDirectionOfColumn));

        return path;
    }

    @Override
    protected boolean isValidDirection(Position fromPosition, Position toPosition) {
        int xDelta = toPosition.getRow() - fromPosition.getRow();
        int yDelta = toPosition.getColumn() - fromPosition.getColumn();

        // Valid direction is either Horizontal(Left/Right) or Vertical(Up/Down).
        if ((xDelta == 0) || (yDelta == 0) && !(xDelta == 0 && yDelta == 0))
            return true;

        // Valid direction is either Diagonal.
        if ((Math.abs(xDelta) == Math.abs(yDelta)))
            return true;

        return false;
    }
}
