package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position  p = new Position(0, 0);
		
		//Above
		p.setValues(position.getRow()-1, position.getColumn());
		if (canMove(p)) mat[p.getRow()][p.getColumn()] = true;
		
		//Below
		p.setValues(position.getRow()+1, position.getColumn());
		if (canMove(p)) mat[p.getRow()][p.getColumn()] = true;
		
		//Left
		p.setValues(position.getRow(), position.getColumn()-1);
		if (canMove(p)) mat[p.getRow()][p.getColumn()] = true;

		//Right
		p.setValues(position.getRow(), position.getColumn()+1);
		if (canMove(p)) mat[p.getRow()][p.getColumn()] = true;

		//Above-Right
		p.setValues(position.getRow()-1, position.getColumn()+1);
		if (canMove(p)) mat[p.getRow()][p.getColumn()] = true;
		
		//Above-Left
		p.setValues(position.getRow()-1, position.getColumn()-1);
		if (canMove(p)) mat[p.getRow()][p.getColumn()] = true;
		
		//Below-Right
		p.setValues(position.getRow()+1, position.getColumn()+1);
		if (canMove(p)) mat[p.getRow()][p.getColumn()] = true;

		//Below-Left
		p.setValues(position.getRow()+1, position.getColumn()-1);
		if (canMove(p)) mat[p.getRow()][p.getColumn()] = true;
		
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
}
