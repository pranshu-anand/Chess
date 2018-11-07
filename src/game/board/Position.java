package src.game.board;

public class Position {
    int  row;
    int  column;

    public Position(int row, int column) {
        super();
        this.row = row;
        this.column = column;
    }

    public Position(int row, char column) {
        int columnNumber = column - 'a' + 1;
        new Position(row, columnNumber);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
