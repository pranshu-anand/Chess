package src.piece;

import java.util.ArrayList;
import java.util.List;

import src.game.Color;
import src.game.PieceType;
import src.game.board.Position;

public class King extends Piece {

    public King(Color color) {
        super(color, PieceType.KING);
    }

    @Override
    protected List<Position> getPath(Position fromPosition, Position toPosition) {
        List<Position> path = new ArrayList<Position>();
        path.add(toPosition);
        return path;
    }

    @Override
    protected boolean isValidDirection(Position fromPosition, Position toPosition) {
        int xDelta = Math.abs(toPosition.getRow() - fromPosition.getRow());
        int yDelta = Math.abs(toPosition.getColumn() - fromPosition.getColumn());

        // Valid step is 1-step in any direction.
        if ((xDelta == 0 || xDelta == 1) && (yDelta == 0 || yDelta == 1))
            return true;

        return false;
    }
}
