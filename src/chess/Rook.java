package chess;

public class Rook extends Piece{

    public Rook(boolean white){
        super(white);
    }

    @Override
    public boolean isLegalMove(Piece[][] board, int x0, int x1, int y0, int y1) {
        boolean valid = ((y1==y0 && x1!=x0) || (x1==x0 && y1!=y0));
        if (valid){
            if (y1<y0){
                for (int i=y0-1; i>y1; i--){
                    if (board[x1][i]!=null){
                        valid=false;
                        break;
                    }
                }
            }
            else if(y1>y0){
                for (int i=y0+1; i<y1; i++){
                    if (board[x1][i]!=null){
                        valid=false;
                        break;
                    }
                }
            }
            else if (x1<x0){
                for (int i=x0-1; i>x1; i--){
                    if (board[i][y1]!=null){
                        valid=false;
                        break;
                    }
                }
            }
            else{
                for (int i=x0+1; i<x1; i++){
                    if (board[i][y1]!=null){
                        valid=false;
                        break;
                    }
                }
            }
        }
        if (valid){
            if (board[x1][y1]!=null){
                if (this.white == board[x1][y1].white) valid = false;
            }
        }
        if (valid && !this.hasMoved) this.hasMoved=true;
        return valid;
    }

    public String toString(){
        return (white) ? "wR":"bR";
    }

}
