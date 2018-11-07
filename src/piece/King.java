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

    protected List<Position> getPath(Position fromPosition, Position toPosition) {
        List<Position> path = new ArrayList<Position>();
        path.add(toPosition);
        return path;
    }

    protected boolean isValidDirection(Position fromPosition, Position toPosition) {
        int xDelta = Math.abs(fromPosition.getRow() - toPosition.getRow());
        int yDelta = Math.abs(fromPosition.getColumn() - toPosition.getColumn());

        if ((xDelta == 0 || xDelta == 1) && (yDelta == 0 || yDelta == 1))
            return true;

        return false;
    }
}
