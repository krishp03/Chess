package chess;

public abstract class Piece {
  
  public boolean white;
  public boolean hasMoved = true;
  
  public Piece(boolean white){
    this.white = white;
  }
  /**
   * Returns a set of squares where the piece can legally move to.
   * 
   * @return a set of legal moves for the piece
   */
  public abstract boolean isLegalMove(int x0, int x1, int y0, int y1);
  
  /**
   * Returns the string representation of the piece.
   * 
   * @return the string representation of the piece
   */
  public abstract String toString();

  }