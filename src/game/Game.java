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
    List<Piece> whitePieces, blackPieces;
    Player whitePlayer, blackPlayer;

    public void initialize() {
        board = new Board(size);

        whitePieces = setupPiecesOnBoard(Color.WHITE);
        whitePlayer = new Player(Color.WHITE, whitePieces);

        blackPieces = setupPiecesOnBoard(Color.BLACK);
        blackPlayer = new Player(Color.BLACK, blackPieces);

        board.displayBoard();
    }

    private List<Piece> setupPiecesOnBoard(Color color) {
        List<Piece> pieces = new ArrayList<Piece>();
        PieceFactory factory = new PieceFactory();

        int x;
        if (color == Color.WHITE)
            x = 1;
        else
            x = this.size - 2;

        for (int i = 0; i < size; i++) {
            Piece piece = factory.createPawn(color);
            pieces.add(piece);
            board.setSpot(new Position(x, i), piece);
        }

        if (color == Color.WHITE)
            x = 0;
        else
            x = this.size - 1;

        Piece piece = factory.createRook(color);
        pieces.add(piece);
        board.setSpot(new Position(x, 0), piece);

        piece = factory.createKnight(color);
        pieces.add(piece);
        board.setSpot(new Position(x, 1), piece);

        piece = factory.createBishop(color);
        pieces.add(piece);
        board.setSpot(new Position(x, 2), piece);

        piece = factory.createQueen(color);
        pieces.add(piece);
        board.setSpot(new Position(x, 3), piece);

        piece = factory.createKing(color);
        pieces.add(piece);
        board.setSpot(new Position(x, 4), piece);

        piece = factory.createBishop(color);
        pieces.add(piece);
        board.setSpot(new Position(x, 5), piece);

        piece = factory.createKnight(color);
        pieces.add(piece);
        board.setSpot(new Position(x, 6), piece);

        piece = factory.createRook(color);
        pieces.add(piece);
        board.setSpot(new Position(x, 7), piece);

        return pieces;
    }

    public void play() {
        Scanner sc = new Scanner(System.in);

        int playerNumber = 0;
        while (true) {
            Player currentPlayer, nextPlayer;
            if (playerNumber == 0) {
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

                Boolean isValid = currentPlayer.move(fromPosition, toPosition, board);
                if (isValid) {
                    board.clearSpot(toPosition);
                    board.setSpot(toPosition, board.getPieceAtSpot(fromPosition));
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

            playerNumber = (playerNumber + 1) % 2;
        }
    }

    private boolean isCheckMate(Player nextPlayer, Player currentPlayer) {
        if (nextPlayer == null || currentPlayer == null)
            return false;
        List<Spot> currentPlayerSpots = currentPlayer.getOccupiedSpots();
        Spot kingSpot = nextPlayer.getKingSpot();
        Position nextPlayerKingPosition = kingSpot.getPosition();

        Map<Position, Boolean> nextPlayerKingPossiblePosition = new HashMap<Position, Boolean>();
        nextPlayerKingPossiblePosition.put(nextPlayerKingPosition, false);
        nextPlayerKingPossiblePosition
                .put(new Position(nextPlayerKingPosition.getRow() + 1, nextPlayerKingPosition.getColumn() + 1), false);
        nextPlayerKingPossiblePosition
                .put(new Position(nextPlayerKingPosition.getRow() + 1, nextPlayerKingPosition.getColumn()), false);
        nextPlayerKingPossiblePosition
                .put(new Position(nextPlayerKingPosition.getRow() + 1, nextPlayerKingPosition.getColumn() - 1), false);
        nextPlayerKingPossiblePosition
                .put(new Position(nextPlayerKingPosition.getRow(), nextPlayerKingPosition.getColumn() - 1), false);
        nextPlayerKingPossiblePosition
                .put(new Position(nextPlayerKingPosition.getRow() - 1, nextPlayerKingPosition.getColumn() - 1), false);
        nextPlayerKingPossiblePosition
                .put(new Position(nextPlayerKingPosition.getRow() - 1, nextPlayerKingPosition.getColumn()), false);
        nextPlayerKingPossiblePosition
                .put(new Position(nextPlayerKingPosition.getRow() - 1, nextPlayerKingPosition.getColumn() + 1), false);
        nextPlayerKingPossiblePosition
                .put(new Position(nextPlayerKingPosition.getRow(), nextPlayerKingPosition.getColumn() + 1), false);

        for (Spot spot : currentPlayerSpots) {
            Position currentPlayerPosition = spot.getPosition();
            for (Position toPosition : nextPlayerKingPossiblePosition.keySet()) {
                if (toPosition == spot.getPosition())
                    continue;
                if (nextPlayerKingPossiblePosition.get(toPosition))
                    continue;
                if (currentPlayer.move(currentPlayerPosition, toPosition, board))
                    nextPlayerKingPossiblePosition.put(toPosition, true);
            }
        }

        Collection<Boolean> isCheckValues = nextPlayerKingPossiblePosition.values();
        if (isCheckValues.contains(false))
            return false;

        return true;
    }

    private boolean isCheck(Player nextPlayer, Player currentPlayer) {
        if (nextPlayer == null || currentPlayer == null)
            return false;
        List<Spot> currentPlayerSpots = currentPlayer.getOccupiedSpots();
        Spot kingSpot = nextPlayer.getKingSpot();
        Position nextPlayerKingPosition = kingSpot.getPosition();
        for (Spot spot : currentPlayerSpots) {
            if (currentPlayer.move(spot.getPosition(), nextPlayerKingPosition, board))
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


    /**
     * Show Result.
     *
     * @param player - player who is in check-mate.
     */
    public void showResult() {

    }
}