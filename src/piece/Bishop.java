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

    @Override
    protected List<Position> getPath(Position fromPosition, Position toPosition) {
        List<Position> path = new ArrayList<Position>();

        int xDelta = toPosition.getRow() - fromPosition.getRow();
        int yDelta = toPosition.getColumn() - fromPosition.getColumn();

        if (Math.abs(xDelta) == Math.abs(yDelta)) {
            int signinDirectionOfRow = xDelta / Math.abs(xDelta);
            int signinDirectionOfColumn = yDelta / Math.abs(yDelta);

            for (int i = 1; i <= Math.abs(xDelta); i++)
                path.add(new Position(fromPosition.getRow() + i * signinDirectionOfRow,
                        fromPosition.getColumn() + i * signinDirectionOfColumn));
        }

        return path;
    }

    @Override
    protected boolean isValidDirection(Position fromPosition, Position toPosition) {
        int xDelta = toPosition.getRow() - fromPosition.getRow();
        int yDelta = toPosition.getColumn() - fromPosition.getColumn();

        // Valid direction is either Diagnol.
        if ((Math.abs(xDelta) == Math.abs(yDelta)))
            return true;

        return false;
    }
}
