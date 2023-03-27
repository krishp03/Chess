package chess;

/**
 * The {@code Piece} abstract class represents a chess piece. Subclasses of this class should implement
 * the specific behavior and legal moves of each type of piece (e.g. pawn, rook, bishop, etc.).
 *
 * @author Krish Patel
 * @author Roshan Varadhan
 */
public abstract class Piece {

    private boolean white;
    private boolean hasMoved = false;
    private boolean enPassantable = false;

    /**
     * Constructor for a Piece object. Sets the color of the piece.
     *
     * @param white true if the piece is white, false if the piece is black
     */
    public Piece(boolean white) {
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

    /**
     * Sets the enPassantable field to the respective boolean, showing if the pawn can be captured en passant
     *
     * @param x the boolean value indicating whether the pawn can be captured en passant
     */
    public void setEnPassant(boolean x) {
        enPassantable = x;
    }

    /**
     * Returns whether the piece can be captured en passant.
     *
     * @return true if the piece can be captured en passant, false otherwise
     */
    public boolean enPassantable() {
        return enPassantable;
    }

    /**
     * Returns true if the inputted move is legal and false if not.
     * If the move is legal, it moves the piece.
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
