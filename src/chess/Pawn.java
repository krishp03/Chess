package chess;

public class Pawn extends Piece{

    public Pawn(boolean white){
        super(white);
    }

    @Override
    public boolean isLegalMove(int x0, int x1, int y0, int y1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isLegalMove'");
    }

    public String toString(){
        return (isWhite()) ? "wp":"bp";
    }

}
