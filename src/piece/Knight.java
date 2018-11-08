package src.piece;

import java.util.ArrayList;
import java.util.List;

import src.game.Color;
import src.game.PieceType;
import src.game.board.Position;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    protected List<Position> getPath(Position fromPosition, Position toPosition) {
        List<Position> path = new ArrayList<Position>();

        path.add(toPosition);
        return path;
    }

    @Override
    protected boolean isValidDirection(Position fromPosition, Position toPosition) {
        int xDelta = toPosition.getRow() - fromPosition.getRow();
        int yDelta = toPosition.getColumn() - fromPosition.getColumn();

        // Valid direction is L-shaped move: with 2 steps horizontally(vertically) and 1
        // step vertically (horizontally).
        if ((xDelta * xDelta + yDelta * yDelta) == 5)
            return true;

        return false;
    }
}
