package src.game.board;

import src.piece.Piece;

public class Board {

    private Spot[][] board;
    private int size = 0;

    public Board(int size) {
        this.size = size;
        board = new Spot[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Spot(new Position(i, j), null);
            }
        }

        placePiecesInInitialPosition();
        //displayBoard();
    }

    private int getSize() {
        return this.size;
    }

    private void placePiecesInInitialPosition() {

    }

    public void setSpot(Position position, Piece piece) {
        board[position.getRow()][position.getColumn()].setPiece(piece);
    }

    public Piece getPieceAtSpot(Position position) {
        return board[position.getRow()][position.getColumn()].getPiece();
    }

    public void clearSpot(Position position) {
        setSpot(position, null);
    }

    public void displayBoard() {
        System.out.println("\nCurrent Board State");
        for (int i = this.getSize(); i > 0; i--) {
            System.out.print(" " + i + " |");
            for (int j = 0; j < this.getSize(); j++) {
                this.board[i-1][j].displaySpot();
            }
            System.out.println();
        }

        System.out.print("    ");
        for (int i = 0; i < this.getSize(); i++) {
            char ch = (char) ('a' + i);
            System.out.print("    " + ch + "  ");
        }
    }

    public boolean isValidPosition(Position position) {
        if (position == null)
            return false;
        int x = position.getRow();
        int y = position.getColumn();

        return x >= 0 && y >= 0 && x < this.getSize() && y < this.getSize();
    }
}
