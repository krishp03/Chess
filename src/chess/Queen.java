package chess;

public class Queen extends Piece{

    public Queen(boolean white){
        super(white);
    }

    @Override
    public boolean isLegalMove(Piece[][] board, int x0, int x1, int y0, int y1) {
        if (board[x1][y1]!=null){
            if (this.white == board[x1][y1].white) return false;
        }
        boolean validNonDiag = ((y1==y0 && x1!=x0) || (x1==x0 && y1!=y0));
        boolean validDiag = Math.abs(x1-x0) == Math.abs(y1-y0) && x0!=x1;
        if (validNonDiag){
            if (y1<y0){
                for (int i=y0-1; i>y1; i--){
                    if (board[x1][i]!=null){
                        validNonDiag=false;
                        break;
                    }
                }
            }
            else if(y1>y0){
                for (int i=y0+1; i<y1; i++){
                    if (board[x1][i]!=null){
                        validNonDiag=false;
                        break;
                    }
                }
            }
            else if (x1<x0){
                for (int i=x0-1; i>x1; i--){
                    if (board[i][y1]!=null){
                        validNonDiag=false;
                        break;
                    }
                }
            }
            else{
                for (int i=x0+1; i<x1; i++){
                    if (board[i][y1]!=null){
                        validNonDiag=false;
                        break;
                    }
                }
            }
        }
        else if(validDiag){
            int i=x0;
            int j=y0;
            if (x1>x0){
                i++;
                if (y1>y0){
                    j++;
                    while (i<x1 && j<y1){
                        if (board[i][j]!=null) {
                            validDiag=false;
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
                            validDiag=false;
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
                            validDiag=false;
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
                            validDiag=false;
                            break;
                        }
                        i--;
                        j--;
                    }
                }
            }
        }
        return validNonDiag || validDiag;
    }

    public String toString(){
        return (white) ? "wQ":"bQ";
    }

}