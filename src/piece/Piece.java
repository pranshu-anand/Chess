package src.piece;

import java.util.List;

import src.game.Color;
import src.game.PieceType;
import src.game.board.Board;
import src.game.board.Position;

/**
 * Abstract class Piece defines the common attributes and functionalities
 * associated with all the pieces.
 *
 * @author Pranshu
 *
 */
public abstract class Piece {

    final Color color;
    final PieceType pieceType;
    final String displayIdentifier;

    /**
     * Constructor for Piece.
     *
     * @param color     - color from the Color enum.
     * @param pieceType - pieceType from the PieceType enum.
     */
    Piece(Color color, PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
        this.displayIdentifier = color.getIdentifier() + "_" + pieceType.getIdentifier();
    }

    /**
     * Returns the color of this piece instance.
     *
     * @return {@link Color}
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the pieceType of this piece instance.
     *
     * @return {@link PieceType}
     */
    public PieceType getPieceType() {
        return pieceType;
    }

    /**
     * Returns the display Identifier.
     *
     * @return
     */
    public String getDisplayIdentifier() {
        return displayIdentifier;
    }

    /**
     * Checks if the direction of traveling is valid.
     *
     * @param fromPosition
     * @param toPosition
     * @return
     */
    protected abstract boolean isValidDirection(Position fromPosition, Position toPosition);

    /**
     * Checks if the move is valid or not.
     *
     * @param fromPosition
     * @param toPosition
     * @param board
     * @return
     */
    public Boolean isValidMove(Position fromPosition, Position toPosition, Board board) {
        if (!board.isValidPosition(fromPosition) || !board.isValidPosition(toPosition))
            return false;

        if (!isValidDirection(fromPosition, toPosition)) {
            return false;
        }

        List<Position> path = getPath(fromPosition, toPosition);
        if (isPathObstructed(path, board)) {
            return false;
        }
        return true;
    }

    /**
     * Creates a path from fromPosition to toPosition.
     *
     * @param fromPosition
     * @param toPosition
     * @return
     */
    protected abstract List<Position> getPath(Position fromPosition, Position toPosition);

    /**
     * Checks if the path is obstructed or not.
     *
     * @param path
     * @param board
     * @return
     */
    protected boolean isPathObstructed(List<Position> path, Board board) {
        // Null path is vacuously obstructed.
        if (path == null)
            return true;

        // Checks for all spots except the last destination spot.
        for (int i = 0; i < path.size() - 1; i++) {
            Piece pieceAtSpot = board.getPieceAtSpot(path.get(i));
            if (pieceAtSpot == null)
                continue;
            else
                return true;
        }

        // Checks if the last destination spot is empty or has same color as the piece
        // which is to be moved.
        System.out.println(path.toString());
        Piece pieceAtEndOfPath = board.getPieceAtSpot(path.get(path.size() - 1));
        if (pieceAtEndOfPath != null && pieceAtEndOfPath.color == this.color)
            return true;

        return false;
    }
}
