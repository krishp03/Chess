package chess;

public class King extends Piece{

    private boolean canCastle = true;
    public King(boolean white){
        super(white);
    }

    @Override
    public boolean isLegalMove(int x0, int x1, int y0, int y1) {
        // TODO Auto-generated method stub
        if (Chess.board[y1][x1]!=null){
            if (isWhite() == Chess.board[y1][x1].isWhite()) return false;
        }
        boolean valid=false;
        if (this.hasMoved()) canCastle=false;
        if (canCastle){
            if (y1==y0){
                if (x1==x0+2){
                    if (!(Chess.board[y0][7] instanceof Rook)) canCastle=false;
                    else if(Chess.board[y0][7].hasMoved()) canCastle=false;
                    else if (Chess.board[y0][5]==null && Chess.board[y0][6]==null) valid=true;
                }
                else if (x1==x0-2){
                    if (!(Chess.board[y0][0] instanceof Rook)) canCastle=false;
                    else if(Chess.board[y0][0].hasMoved()) canCastle=false;
                    else if (Chess.board[y0][1]==null && Chess.board[y0][2]==null && Chess.board[y0][3]==null) valid=false;
                }
            }
        }
        if (valid) {
            Chess.board[y1][x1]=Chess.board[y0][x0];
            Chess.board[y0][x0]=null;
            if (x1==x0+2) {
                Chess.board[y0][5] = Chess.board[y0][7];
                Chess.board[y0][7]=null;
            }
            else{
                Chess.board[y0][3] = Chess.board[y0][0];
                Chess.board[y0][0]=null;

            }
            this.setMoved();
            return true;
        }
        if (x1==x0 && y1==y0) return false;
        if (Math.abs(x1-x0)<=1 && Math.abs(y1-y0)<=1) valid=true;
        if (valid){
            if (!this.hasMoved()) this.setMoved();
            Chess.board[y1][x1]=Chess.board[y0][x0];
            Chess.board[y0][x0]=null;
        }
        return valid;
    }

    public String toString(){
        return (isWhite()) ? "wK":"bK";
    }

}
