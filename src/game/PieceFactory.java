package src.game;
import src.piece.Bishop;
import src.piece.King;
import src.piece.Knight;
import src.piece.Pawn;
import src.piece.Piece;
import src.piece.Queen;
import src.piece.Rook;

public class PieceFactory {
	
	Piece createPiece(Color color, PieceType pieceType) {
		if(pieceType == PieceType.KING)
			return createKing(color);
		if(pieceType == PieceType.QUEEN)
			return createQueen(color);
		if(pieceType == PieceType.BISHOP)
			return createBishop(color);
		if(pieceType == PieceType.ROOK)
			return createRook(color);
		if(pieceType == PieceType.KNIGHT)
			return createKnight(color);
		if(pieceType == PieceType.KNIGHT)
			return createPawn(color);
		else
			return null;
	}
	
	public Piece createKing(Color color) {
		return new King(color);
	}

	public Piece createQueen(Color color) {
		return new Queen(color);
	}

	public Piece createBishop(Color color) {
		return new Bishop(color);
	}

	public Piece createKnight(Color color) {
		return new Knight(color);
	}

	public Piece createRook(Color color) {
		return new Rook(color);
	}

	public Piece createPawn(Color color) {
		return new Pawn(color);
	}

}
