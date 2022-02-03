package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;
	
	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position  p = new Position(0, 0);
		
		//North (N)
		p.setValues(position.getRow() -1, position.getColumn());
		if (canMove(p)) mat[p.getRow()][p.getColumn()] = true;
		
		//Northeast (NE)
		p.setValues(position.getRow() -1, position.getColumn() +1);
		if (canMove(p)) mat[p.getRow()][p.getColumn()] = true;

		//East (E)
		p.setValues(position.getRow(), position.getColumn() +1);
		if (canMove(p)) mat[p.getRow()][p.getColumn()] = true;

		//Southeast (SE)
		p.setValues(position.getRow() +1, position.getColumn() +1);
		if (canMove(p)) mat[p.getRow()][p.getColumn()] = true;

		//South (S)
		p.setValues(position.getRow() +1, position.getColumn());
		if (canMove(p)) mat[p.getRow()][p.getColumn()] = true;
		
		//Southwest (SW)
		p.setValues(position.getRow() +1, position.getColumn() -1);
		if (canMove(p)) mat[p.getRow()][p.getColumn()] = true;

		//West (W)
		p.setValues(position.getRow(), position.getColumn() -1);
		if (canMove(p)) mat[p.getRow()][p.getColumn()] = true;
		
		//Northwest (NW)
		p.setValues(position.getRow() -1, position.getColumn() -1);
		if (canMove(p)) mat[p.getRow()][p.getColumn()] = true;
		
		//Castling - special move
		if(getMoveCount() == 0 && !chessMatch.getCheck()) {
			//Castling kingside rook
			Position posT1 = new Position(position.getRow(), position.getColumn() +3);
			if (testRookCastling(posT1)) {
				Position p1 = new Position(position.getRow(), position.getColumn() +1);
				Position p2 = new Position(position.getRow(), position.getColumn() +2);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getColumn() +2] = true;
				}
			}
			//Castling queenside rook
			Position posT2 = new Position(position.getRow(), position.getColumn() -4);
			if (testRookCastling(posT2)) {
				Position p1 = new Position(position.getRow(), position.getColumn() -1);
				Position p2 = new Position(position.getRow(), position.getColumn() -2);
				Position p3 = new Position(position.getRow(), position.getColumn() -3);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					mat[position.getRow()][position.getColumn() -2] = true;
				}
			}
		}
		return mat;
	}
	
	private boolean canMove(Position p) {
		if (getBoard().positionExists(p)) {
			if (!getBoard().thereIsAPiece(p) || isThereOpponentPiece(p)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0; 
	}
}
