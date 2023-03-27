package chess;

public class Rook extends Piece{

    public Rook(boolean white){
        super(white);
    }

    @Override
    public boolean isLegalMove(int x0, int x1, int y0, int y1) {
        boolean valid = ((y1==y0 && x1!=x0) || (x1==x0 && y1!=y0));
        if (valid){
            if (y1<y0){
                for (int i=y0-1; i>y1; i--){
                    if (Chess.board[x1][i]!=null){
                        valid=false;
                        break;
                    }
                }
            }
            else if(y1>y0){
                for (int i=y0+1; i<y1; i++){
                    if (Chess.board[x1][i]!=null){
                        valid=false;
                        break;
                    }
                }
            }
            else if (x1<x0){
                for (int i=x0-1; i>x1; i--){
                    if (Chess.board[i][y1]!=null){
                        valid=false;
                        break;
                    }
                }
            }
            else{
                for (int i=x0+1; i<x1; i++){
                    if (Chess.board[i][y1]!=null){
                        valid=false;
                        break;
                    }
                }
            }
        }
        if (valid){
            if (Chess.board[x1][y1]!=null){
                if (isWhite() == Chess.board[x1][y1].isWhite()) valid = false;
            }
        }
        if (valid && !this.hasMoved()) this.setMoved();
        if (valid) {
            Chess.board[x1][y1]=Chess.board[x0][y0];
            Chess.board[x0][y0]=null;
        }
        return valid;
    }

    public String toString(){
        return (isWhite()) ? "wR":"bR";
    }

}
