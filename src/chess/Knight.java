package chess;

public class Knight extends Piece{

    public Knight(boolean white){
        super(white);
    }

    @Override
    public boolean isLegalMove(int x0, int x1, int y0, int y1) {
        // TODO Auto-generated method stub
        if (Chess.board[x1][y1]!=null){
            if (this.white==Chess.board[x1][y1].white) return false;
        }
        return Math.abs(x1-x0) * Math.abs(y1-y0) == 2;
    }

    public String toString(){
        return (white) ? "wN":"bN";
    }

}
