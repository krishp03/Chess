package chess;

public class Knight extends Piece{

    public Knight(boolean white){
        super(white);
    }

    @Override
    public boolean isLegalMove(int x0, int x1, int y0, int y1) {
        // TODO Auto-generated method stub
        if (Chess.board[x1][y1]!=null){
            if (isWhite()==Chess.board[x1][y1].isWhite()) return false;
        }
        boolean valid = Math.abs(x1-x0) * Math.abs(y1-y0) == 2;
        if (valid) {
            Chess.board[x1][y1]=Chess.board[x0][y0];
            Chess.board[x0][y0]=null;
        }
        return valid;
    }

    public String toString(){
        return (isWhite()) ? "wN":"bN";
    }

}
