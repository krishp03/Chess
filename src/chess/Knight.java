package chess;

/**
 * The {@code Knight} class represents a Knight piece in the game of chess.
 * It extends the {@code Piece} class and inherits its attributes and methods.
 *
 * @author Krish Patel
 * @author Roshan Varadhan
 * @see Piece
 */
public class Knight extends Piece {

    /**
     * Constructs a Knight piece with the given color.
     *
     * @param white true if the piece is white, false if it is black
     */
    public Knight(boolean white) {
        super(white);
    }

    @Override
    public boolean isLegalMove(int x0, int x1, int y0, int y1) {
        // TODO Auto-generated method stub
        if (Chess.board[x1][y1] != null) {
            if (isWhite() == Chess.board[x1][y1].isWhite()) return false;
        }
        boolean valid = Math.abs(x1 - x0) * Math.abs(y1 - y0) == 2;
        if (valid) {
            Chess.board[x1][y1] = Chess.board[x0][y0];
            Chess.board[x0][y0] = null;
        }
        return valid;
    }

    public String toString() {
        return (isWhite()) ? "wN" : "bN";
    }

}
