package chess;

public class Pawn extends Piece{
    public boolean firstMoved=false;
    public Pawn(boolean white){
        super(white);
    }

    @Override
    public boolean isLegalMove(int x0, int x1, int y0, int y1) {
        boolean valid;
        if (y1==y0) return false;
        else if (!this.hasMoved()){
            if (this.isWhite()) valid = y1-y0<=2;
            else valid = y0-y1<=2;
            if (valid){
                this.setHasMoved(true);
                firstMoved=true;
            }
        }
        if (this.isWhite()) valid = y1-y0==1;
        else valid = y0-y1==1;
        if (valid) firstMoved=false;
        return valid;
        
    }

    public String toString(){
        return (this.isWhite()) ? "wp":"bp";
    }

}
