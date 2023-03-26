/**
 * This project is an implementation of chess game in Java.
 * @author Roshan Varadhan
 * @author Krish Patel
*/
package chess;

import java.util.Scanner;

public class Chess {

    private static Piece[][] board;
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

    public void promote(){

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
        if(whiteTurn){
            System.out.print("White's Move: ");
        } else {
            System.out.print("Black's Move: ");
        }
        String move = in.nextLine();
        if(move.contains("resign")){
            end = whiteTurn ? "Black Wins" : "White wins";
        } else if(move.contains("draw?")){
            end = "draw";
        }
    }

    private int[] getIndicies(String spot){
        char file = spot.charAt(0);
        int rank = spot.charAt(1) - '0';
        return new int[] {file-'a', 8-rank};
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