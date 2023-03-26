/**
 * This project is an implementation of chess game in Java.
 * @author Roshan Varadhan
 * @author Krish Patel
*/
package chess;

import java.util.Scanner;

public class Chess {

    static Piece[][] board;
    private static boolean whiteTurn;
    private static String end;
    private static Scanner in;

    public static void initGame(){
        board = new Piece[8][8];
        whiteTurn = true;
        end = null;
        in = new Scanner(System.in);

        board[0][0] = new Rook(false);
		board[0][1] = new Knight(false);
		board[0][2] = new Bishop(false);
		board[0][3] = new Queen(false);
		board[0][4] = new King(false);
		board[0][5] = new Bishop(false);
		board[0][6] = new Knight(false);
		board[0][7] = new Rook(false);

        board[7][0] = new Rook(true);
		board[7][1] = new Knight(true);
		board[7][2] = new Bishop(true);
		board[7][3] = new Queen(true);
		board[7][4] = new King(true);
		board[7][5] = new Bishop(true);
		board[7][6] = new Knight(true);
		board[7][7] = new Rook(true);

        for(int i = 0; i < 8; i++){
            board[1][i] = new Pawn(false);
            board[2][i] = null;
			board[3][i] = null;
			board[4][i] = null;
			board[5][i] = null;
            board[6][i] = new Pawn(true);

        }
    }


    public static void endGame(){

    }

    public static void printGame(){

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++){

                if(board[i][j] != null) {
                    System.out.print(board[i][j]+ " ");
                }
                else {
                    if((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)){
                        System.out.print("## ");
                    } 
                    else {
                        System.out.print("   ");
                    }
                }

            }
            System.out.println(8-i);
        }
        for(int i = 0; i < 8; i ++){
            System.out.print(" "+(char)('a'+i)+" ");
        }
        System.out.println('\n');

    }

    public static void playTurn(){
        boolean legalMove = false;
        while(!legalMove) {
            if(whiteTurn){
                System.out.print("White's Move: ");
            } else {
                System.out.print("Black's Move: ");
            }
            String move = in.nextLine();
            if(move.contains("resign")){
                end = whiteTurn ? "Black Wins" : "White wins";
                break;
            } else if(move.contains("draw?")){
                end = "draw";
                break;
            } 
            // If no draw 
            else {
                int[] src = getIndicies(move.substring(0, 2));
                if(board[src[0]][src[1]] != null && board[src[0]][src[1]].isWhite() == whiteTurn){
                    int[] dest = getIndicies(move.substring(3, 5));
                    String promoteTo = null;
                    if(canBePromoted(src, dest[0])){
                        promoteTo = move.substring(move.length()-1);
                    } else if(move.length() > 5) {
                        legalMove = false;
                    } else {
                        legalMove = board[src[0]][src[1]].isLegalMove(src[0], src[1], dest[0], dest[1]);
                    }
                    if(legalMove && promoteTo != null){
                        promote(dest, promoteTo);
                    }
                }
            }
        }
    }

    private static int[] getIndicies(String spot){
        char file = spot.charAt(0);
        int rank = spot.charAt(1) - '0';
        return new int[] {8-rank, file - 'a'};
    }

    private static boolean canBePromoted(int[] src, int destRow) {
        Piece p = board[src[0]][src[1]];
        if(p instanceof Pawn){
            if(p.isWhite() && destRow == 0 || !p.isWhite() && destRow == 7)
        }
        return false;
    }

    private static void promote(int[] dest, String promoteTo){ 
        switch(promoteTo){
            case "R":
                board[dest[0]][dest[1]] = new Rook(whiteTurn);
                break;
            case "N":
                board[dest[0]][dest[1]] = new Knight(whiteTurn);
                break;
            case "B":
                board[dest[0]][dest[1]] = new Bishop(whiteTurn);
                break;
            default:
                board[dest[0]][dest[1]] = new Queen(whiteTurn);
                break;  
        }
    }

    
    public static String getEndText(){
        return end;
    }

    public static void main(String[] args) {
        Chess.initGame();
        while(Chess.getEndText() == null){
            Chess.printGame();
            playTurn();
        }
        System.out.println(Chess.getEndText());
    }
}