package chess;

/**
 * The {@code King} class represents a King piece in the game of chess.
 * It extends the {@code Piece} class and inherits its attributes and methods.
 *
 * @author Krish Patel
 * @author Roshan Varadhan
 * @see Piece
 */
public class King extends Piece {

    private boolean canCastle = true;

    /**
     * Constructs a King piece with the given color.
     *
     * @param white true if the piece is white, false if it is black
     */
    public King(boolean white) {
        super(white);
    }

    @Override
    public boolean isLegalMove(int x0, int x1, int y0, int y1) {
        // TODO Auto-generated method stub
        if (Chess.board[x1][y1] != null) {
            if (isWhite() == Chess.board[x1][y1].isWhite()) return false;
        }
        boolean valid = false;
        if (this.hasMoved()) canCastle = false;
        if (canCastle) {
            if (x1 == x0) {
                if (y1 == y0 + 2) {
                    if (!(Chess.board[x0][7] instanceof Rook)) canCastle = false;
                    else if (Chess.board[x0][7].hasMoved()) canCastle = false;
                    else if (Chess.board[x0][5] == null && Chess.board[x0][6] == null) valid = true;
                } else if (y1 == y0 - 2) {
                    if (!(Chess.board[x0][0] instanceof Rook)) canCastle = false;
                    else if (Chess.board[x0][0].hasMoved()) canCastle = false;
                    else if (Chess.board[x0][1] == null && Chess.board[x0][2] == null && Chess.board[x0][3] == null)
                        valid = true;
                }
            }
        }
        if (valid) {
            Chess.board[x1][y1] = Chess.board[x0][y0];
            Chess.board[x0][y0] = null;
            if (y1 == y0 + 2) {
                Chess.board[x0][5] = Chess.board[x0][7];
                Chess.board[x0][7] = null;
                System.out.println("Castled Short");
            } else {
                Chess.board[x0][3] = Chess.board[x0][0];
                Chess.board[x0][0] = null;
                System.out.println("Castled Long");
            }
            this.setMoved();
            return true;
        }
        if (x1 == x0 && y1 == y0) return false;
        if (Math.abs(x1 - x0) <= 1 && Math.abs(y1 - y0) <= 1) valid = true;
        if (valid) {
            if (!this.hasMoved()) this.setMoved();
            Chess.board[x1][y1] = Chess.board[x0][y0];
            Chess.board[x0][y0] = null;
        }
        return valid;
    }

    public String toString() {
        return (isWhite()) ? "wK" : "bK";
    }

}
