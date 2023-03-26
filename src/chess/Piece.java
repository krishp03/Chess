package chess;

/**
 * This abstract class represents a chess piece. Subclasses of this class should implement
 * the specific behavior and legal moves of each type of piece (e.g. pawn, rook, bishop, etc.).
 *
 * @author Krish Patel
 * @author Roshan Varadhan
 */
public abstract class Piece {

    private boolean white;
    private boolean hasMoved = true;

    /**
     * Constructor for a Piece object. Sets the color of the piece.
     *
     * @param white true if the piece is white, false if the piece is black
     */
    public Piece(boolean white){
        this.white = white;
    }

    /**
     * Returns the color of the piece.
     *
     * @return true if the piece is white, false if the piece is black
     */
    public boolean isWhite() {
        return white;
    }

    /**
     * Sets the hasMoved field to true, indicating that the piece has moved at least once.
     */
    public void setMoved() {
        hasMoved = true;
    }

    /**
     * Returns whether the piece has moved yet or not.
     *
     * @return true if the piece has moved, false otherwise
     */
    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean x){
      this.hasMoved=x;
    }

    /**
     * Returns a set of squares where the piece can legally move to.
     *
     * @param x0 the x-coordinate of the piece's starting position
     * @param x1 the x-coordinate of the piece's destination position
     * @param y0 the y-coordinate of the piece's starting position
     * @param y1 the y-coordinate of the piece's destination position
     * @return true if the move is legal, false otherwise
     */
    public abstract boolean isLegalMove(int x0, int x1, int y0, int y1);

    /**
     * Returns the string representation of the piece.
     *
     * @return the string representation of the piece
     */
    public abstract String toString();

}
