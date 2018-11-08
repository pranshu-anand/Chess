package src.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import src.game.board.Board;
import src.game.board.Position;
import src.game.board.Spot;
import src.piece.Piece;

public class Game {
    int size = 8;
    Board board;
    Player whitePlayer, blackPlayer;

    public void initialize() {
        board = new Board(size);

        List<Spot> spotsOfWhitePieces = setupPiecesOnBoard(Color.WHITE);
        whitePlayer = new Player(Color.WHITE, spotsOfWhitePieces);

        List<Spot> spotsOfBlackPieces = setupPiecesOnBoard(Color.BLACK);
        blackPlayer = new Player(Color.BLACK, spotsOfBlackPieces);

        board.displayBoard();
    }

    private List<Spot> setupPiecesOnBoard(Color color) {
        List<Spot> spots = new ArrayList<Spot>();
        PieceFactory factory = new PieceFactory();

        int x;
        if (color == Color.WHITE)
            x = 2;
        else
            x = this.size - 1;

        for (int i = 1; i <= size; i++) {
            Piece piece = factory.createPawn(color);
            Position position = new Position(x, i);
            board.setSpot(position, piece);
            spots.add(board.getSpot(position));
        }

        if (color == Color.WHITE)
            x = 1;
        else
            x = this.size;

        Piece piece = factory.createRook(color);
        Position position = new Position(x, 1);
        board.setSpot(position, piece);
        spots.add(board.getSpot(position));

        piece = factory.createKnight(color);
        position = new Position(x, 2);
        board.setSpot(position, piece);
        spots.add(board.getSpot(position));

        piece = factory.createBishop(color);
        position = new Position(x, 3);
        board.setSpot(position, piece);
        spots.add(board.getSpot(position));

        piece = factory.createQueen(color);
        position = new Position(x, 4);
        board.setSpot(position, piece);
        spots.add(board.getSpot(position));

        piece = factory.createKing(color);
        position = new Position(x, 5);
        board.setSpot(position, piece);
        spots.add(board.getSpot(position));

        piece = factory.createBishop(color);
        position = new Position(x, 6);
        board.setSpot(position, piece);
        spots.add(board.getSpot(position));

        piece = factory.createKnight(color);
        position = new Position(x, 7);
        board.setSpot(position, piece);
        spots.add(board.getSpot(position));

        piece = factory.createRook(color);
        position = new Position(x, 8);
        board.setSpot(position, piece);
        spots.add(board.getSpot(position));

        return spots;
    }

    /**
     * Play method responsible for the actual game play turn-by-turn.
     *
     */
    public void play() {
        Scanner sc = new Scanner(System.in);

        // PlayerNumber-0 is a proxy for WHITE.
        // PlayerNumber-1 is a proxy for BLACK.
        int currentPlayerNumber = 0;

        while (true) {
            Player currentPlayer, nextPlayer;

            if (currentPlayerNumber == 0) {
                currentPlayer = whitePlayer;
                nextPlayer = blackPlayer;
            } else {
                currentPlayer = blackPlayer;
                nextPlayer = whitePlayer;
            }

            while (true) {
                System.out.println("\nCurrent Player: " + currentPlayer.color);
                System.out.println("Enter fromX, fromY, toX, toY: ");

                int fromX = sc.nextInt();
                int fromY = sc.nextInt();
                int toX = sc.nextInt();
                int toY = sc.nextInt();

                Position fromPosition = new Position(fromX, fromY);
                Position toPosition = new Position(toX, toY);

                if (!board.isValidPosition(fromPosition)) {
                    System.out.println("Invalid Start-Position. Please Re-enter.");
                    continue;
                }

                if (!board.isValidPosition(toPosition)) {
                    System.out.println("Invalid To-Position. Please Re-enter.");
                    continue;
                }

                Boolean isValidMove = currentPlayer.isValidMove(fromPosition, toPosition, board);

                if (isValidMove) {
                    Spot fromSpot = board.getSpot(fromPosition);
                    Spot toSpot = board.getSpot(toPosition);

                    Piece pieceAtFromPosition = board.getPieceAtSpot(fromPosition);
                    Piece pieceAtToPosition = board.getPieceAtSpot(toPosition);

                    if (pieceAtToPosition != null) {
                        nextPlayer.removeFromOccupiedSpots(board.getSpot(toPosition));
                        board.clearSpot(toPosition);
                    }

                    board.setSpot(toPosition, pieceAtFromPosition);
                    currentPlayer.removeFromOccupiedSpots(fromSpot);
                    currentPlayer.addToOccupiedSpots(toPosition, board.getPieceAtSpot(fromPosition));
                    if (pieceAtFromPosition.getPieceType() == PieceType.KING)
                        currentPlayer.setKingSpot(board.getSpot(toPosition));

                    board.clearSpot(fromPosition);
                    board.displayBoard();

                    if (isCheck(nextPlayer, currentPlayer)) {
                        if (isCheckMate(nextPlayer, currentPlayer)) {
                            displayCheckMate(nextPlayer);
                            displayResult(currentPlayer);
                            sc.close();
                            return;
                        }
                        displayCheck(nextPlayer);
                    }

                    break;
                } else {
                    System.out.println("\nInvalid Move");
                }
            }

            currentPlayerNumber = (currentPlayerNumber + 1) % 2;
        }
    }

    private boolean isCheckMate(Player checkedPlayer, Player checkedByPlayer) {
        if (checkedPlayer == null || checkedByPlayer == null)
            return false;

        List<Spot> checkedByPlayerSpots = checkedByPlayer.getOccupiedSpots();
        Spot kingSpot = checkedPlayer.getKingSpot();
        Position checkedPlayerKingPosition = kingSpot.getPosition();

        Map<Position, Boolean> checkedPlayerKingPossiblePosition = new HashMap<Position, Boolean>();

        checkedPlayerKingPossiblePosition.put(checkedPlayerKingPosition, false);
        checkedPlayerKingPossiblePosition.put(
                new Position(checkedPlayerKingPosition.getRow() + 1, checkedPlayerKingPosition.getColumn() + 1), false);
        checkedPlayerKingPossiblePosition.put(
                new Position(checkedPlayerKingPosition.getRow() + 1, checkedPlayerKingPosition.getColumn()), false);
        checkedPlayerKingPossiblePosition.put(
                new Position(checkedPlayerKingPosition.getRow() + 1, checkedPlayerKingPosition.getColumn() - 1), false);
        checkedPlayerKingPossiblePosition.put(
                new Position(checkedPlayerKingPosition.getRow(), checkedPlayerKingPosition.getColumn() - 1), false);
        checkedPlayerKingPossiblePosition.put(
                new Position(checkedPlayerKingPosition.getRow() - 1, checkedPlayerKingPosition.getColumn() - 1), false);
        checkedPlayerKingPossiblePosition.put(
                new Position(checkedPlayerKingPosition.getRow() - 1, checkedPlayerKingPosition.getColumn()), false);
        checkedPlayerKingPossiblePosition.put(
                new Position(checkedPlayerKingPosition.getRow() - 1, checkedPlayerKingPosition.getColumn() + 1), false);
        checkedPlayerKingPossiblePosition.put(
                new Position(checkedPlayerKingPosition.getRow(), checkedPlayerKingPosition.getColumn() + 1), false);

        for (Position toPosition : checkedPlayerKingPossiblePosition.keySet()) {
            if (!board.isValidPosition(toPosition)) {
                checkedPlayerKingPossiblePosition.put(toPosition, true);
                continue;
            }
            for (Spot checkedByPlayerSpot : checkedByPlayerSpots) {
                Position checkedByPlayerPosition = checkedByPlayerSpot.getPosition();
                if (board.getPieceAtSpot(toPosition) != null) {
                    checkedPlayerKingPossiblePosition.put(toPosition, true);
                    break;
                }

                if (checkedByPlayer.isValidMove(checkedByPlayerPosition, toPosition, board)) {
                    checkedPlayerKingPossiblePosition.put(toPosition, true);
                    break;
                }
            }
        }

        Collection<Boolean> isCheckValues = checkedPlayerKingPossiblePosition.values();
        if (!isCheckValues.contains(false))
            return true;

        return false;
    }

    private boolean isCheck(Player checkedPlayer, Player checkedByPlayer) {
        if (checkedPlayer == null || checkedByPlayer == null)
            return false;
        List<Spot> checkedPlayerSpots = checkedByPlayer.getOccupiedSpots();
        Spot kingSpot = checkedPlayer.getKingSpot();
        System.out.println("\nKing position: " + kingSpot.getPosition().toString());
        Position checkedPlayerKingPosition = kingSpot.getPosition();
        for (Spot spot : checkedPlayerSpots) {
            if (checkedByPlayer.isValidMove(spot.getPosition(), checkedPlayerKingPosition, board))
                return true;
        }
        return false;
    }

    /**
     * Displays the situation of 'Check'.
     *
     * @param player - player who is in check.
     */
    private void displayCheck(Player player) {
        System.out.println("\n Player - " + player.color + " is in Check!");
    }

    /**
     * Displays the situation of 'CheckMate'.
     *
     * @param player - player who is in check-mate.
     */
    private void displayCheckMate(Player player) {
        System.out.println("\n Player - " + player.color + " has been check-mated!");
    }

    /**
     * Displays the result.
     *
     * @param player - player who is in check.
     */
    private void displayResult(Player player) {
        System.out.println("\n Player - " + player.color + " won!");
    }

}