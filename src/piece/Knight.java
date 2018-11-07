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

    protected List<Position> getPath(Position fromPosition, Position toPosition) {
        List<Position> path = new ArrayList<Position>();

        path.add(toPosition);
        return path;
    }

    protected boolean isValidDirection(Position fromPosition, Position toPosition) {
        int xDelta = fromPosition.getRow() - toPosition.getRow();
        int yDelta = fromPosition.getColumn() - toPosition.getColumn();

        if ((xDelta * xDelta + yDelta * yDelta) == 5)
            return true;

        return false;
    }

}
