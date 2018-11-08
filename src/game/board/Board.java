package src.game.board;

import src.piece.Piece;

public class Board {

    private Spot[][] board;
    private int size = 0;

    public Board(int size) {
        this.size = size;
        board = new Spot[size][size];
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                board[i - 1][j - 1] = new Spot(new Position(i, j), null);
            }
        }
    }

    public Board(Board board2) {
        this(board2.getSize());
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                Position position = new Position(i, j);
                setSpot(position, board2.getPieceAtSpot(position));
            }
        }
    }

    public int getSize() {
        return this.size;
    }

    public Spot getSpot(Position position) {
        return this.board[position.getRow() - 1][position.getColumn() - 1];
    }

    public void setSpot(Position position, Piece piece) {
        board[position.getRow() - 1][position.getColumn() - 1].setPiece(piece);
    }

    public Piece getPieceAtSpot(Position position) {
        if (isValidPosition(position))
            return board[position.getRow() - 1][position.getColumn() - 1].getPiece();
        else
            return null;
    }

    public Piece clearSpot(Position position) {
        Piece piece = getPieceAtSpot(position);
        setSpot(position, null);
        return piece;
    }

    /**
     * Checks if the Position is within the ranges of the board.
     *
     * @param position
     * @return
     */
    public boolean isValidPosition(Position position) {
        if (position == null)
            return false;
        int x = position.getRow();
        int y = position.getColumn();

        return (x > 0 && y > 0 && x <= this.getSize() && y <= this.getSize());
    }

    /**
     * Displays the board.
     */
    public void displayBoard() {
        System.out.println("\nCurrent Board State:");

        for (int i = this.getSize(); i > 0; i--) {
            System.out.print(" " + i + " | ");
            for (int j = 1; j <= this.getSize(); j++) {
                String display = this.board[i - 1][j - 1].getDisplayIdentifier();
                if (display != null)
                    System.out.print("  " + display + " ");
                else
                    System.out.print("       ");
            }
            System.out.println();
        }

        System.out.print("    ");
        for (int i = 0; i < this.getSize(); i++) {
            char ch = (char) ('a' + i);
            System.out.print("    " + ch + "  ");
        }
    }
}
