package chess;

/**
 * The {@code Queen} class represents a Queen piece in the game of chess.
 * It extends the {@code Piece} class and inherits its attributes and methods.
 *
 * @author Krish Patel
 * @author Roshan Varadhan
 * @see Piece
 */
public class Queen extends Piece {

    /**
     * Constructs a Queen piece with the given color.
     *
     * @param white true if the piece is white, false if it is black
     */
    public Queen(boolean white) {
        super(white);
    }

    @Override
    public boolean isLegalMove(int x0, int x1, int y0, int y1) {
        if (Chess.board[x1][y1] != null) {
            if (isWhite() == Chess.board[x1][y1].isWhite()) return false;
        }
        boolean validNonDiag = ((y1 == y0 && x1 != x0) || (x1 == x0 && y1 != y0));
        boolean validDiag = Math.abs(x1 - x0) == Math.abs(y1 - y0) && x0 != x1;
        if (validNonDiag) {
            if (y1 < y0) {
                for (int i = y0 - 1; i > y1; i--) {
                    if (Chess.board[x1][i] != null) {
                        validNonDiag = false;
                        break;
                    }
                }
            } else if (y1 > y0) {
                for (int i = y0 + 1; i < y1; i++) {
                    if (Chess.board[x1][i] != null) {
                        validNonDiag = false;
                        break;
                    }
                }
            } else if (x1 < x0) {
                for (int i = x0 - 1; i > x1; i--) {
                    if (Chess.board[i][y1] != null) {
                        validNonDiag = false;
                        break;
                    }
                }
            } else {
                for (int i = x0 + 1; i < x1; i++) {
                    if (Chess.board[i][y1] != null) {
                        validNonDiag = false;
                        break;
                    }
                }
            }
        } else if (validDiag) {
            int i = x0;
            int j = y0;
            if (x1 > x0) {
                i++;
                if (y1 > y0) {
                    j++;
                    while (i < x1 && j < y1) {
                        if (Chess.board[i][j] != null) {
                            validDiag = false;
                            break;
                        }
                        i++;
                        j++;
                    }
                } else {
                    j--;
                    while (i < x1 && j > y1) {
                        if (Chess.board[i][j] != null) {
                            validDiag = false;
                            break;
                        }
                        i++;
                        j--;
                    }
                }
            } else {
                i--;
                if (y1 > y0) {
                    j++;
                    while (i > x1 && j < y1) {
                        if (Chess.board[i][j] != null) {
                            validDiag = false;
                            break;
                        }
                        i--;
                        j++;
                    }
                } else {
                    j--;
                    while (i > x1 && j > y1) {
                        if (Chess.board[i][j] != null) {
                            validDiag = false;
                            break;
                        }
                        i--;
                        j--;
                    }
                }
            }
        }
        if (validNonDiag || validDiag) {
            Chess.board[x1][y1] = Chess.board[x0][y0];
            Chess.board[x0][y0] = null;
        }
        return validNonDiag || validDiag;
    }

    @Override
    public String toString() {
        return (isWhite()) ? "wQ" : "bQ";
    }

}
