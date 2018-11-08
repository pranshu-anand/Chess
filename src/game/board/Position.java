package src.game.board;

/**
 * Simple POJO class.
 *
 * @author Pranshu
 *
 */
public class Position {
    int row;
    int column;

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

    public Position convertStringToPosition(String string) {
        String[] strings = string.split("0-9*", 2);
        int row = Integer.valueOf(strings[0]);
        char column = strings[1].charAt(0);
        return new Position(row, column);
    }

    @Override
    public String toString() {
        String string = String.valueOf(this.getRow());
        string = string + "_" + String.valueOf(this.getColumn());
        return string;
    }

    public Boolean equals(Position position) {
        return this.getRow() == position.getRow() && this.getColumn() == position.getColumn();
    }
}
