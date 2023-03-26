package chess;

public class Bishop extends Piece{

    public Bishop(boolean white){
        super(white);
    }

    @Override
    public boolean isLegalMove(Piece[][] board, int x0, int x1, int y0, int y1) {
        boolean valid = Math.abs(x1-x0) == Math.abs(y1-y0) && x0!=x1;
        int i=x0;
        int j=y0;
        if (valid){
            if (x1>x0){
                i++;
                if (y1>y0){
                    j++;
                    while (i<x1 && j<y1){
                        if (board[i][j]!=null) {
                            valid=false;
                            break;
                        }
                        i++;
                        j++;
                    }
                }
                else{
                    j--;
                    while (i<x1 && j>y1){
                        if (board[i][j]!=null) {
                            valid=false;
                            break;
                        }
                        i++;
                        j--;
                    }
                }
            }
            else{
                i--;
                if (y1>y0){
                    j++;
                    while (i>x1 && j<y1){
                        if (board[i][j]!=null) {
                            valid=false;
                            break;
                        }
                        i--;
                        j++;
                    }
                }
                else{
                    j--;
                    while (i>x1 && j>y1){
                        if (board[i][j]!=null) {
                            valid=false;
                            break;
                        }
                        i--;
                        j--;
                    }
                }
            }
        }
        if (valid){
            if (board[x1][y1]!=null){
                if (this.white==board[x1][y1].white) valid=false;
            }
        }
        return valid;
    }

    public String toString(){
        return (white) ? "wB":"bB";
    }

}