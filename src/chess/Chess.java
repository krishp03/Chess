package chess;

import java.util.Scanner;

/**
 * Chess game implementation that allows for en passant capture, castling, and detects checkmates
 * @author Krish Patel
 * @author Roshan Varadhan
 */
public class Chess {

    /**
     * The board of the game, represented by a 2D array of Pieces
     */
    static Piece[][] board;
    
    /**
     * Boolean to keep track of the current turn, true for white, false for black
     */
    private static boolean whiteTurn;
    
    /**
     * String to keep track of the end result of the game, null if not over
     */
    private static String end;
    
    /**
     * Scanner to read user input for making moves
     */
    private static Scanner in;
    
    /**
     * Boolean to keep track of whether white is currently in check
     */
    private static boolean whiteInCheck;
    
    /**
     * Boolean to keep track of whether black is currently in check
     */
    private static boolean blackInCheck;

    /**
     * Initializes a new game of Chess by creating a new board, setting the first turn to white
     * and creating new instances of all the pieces in their starting positions.
     */
    public static void initGame() {
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

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(false);
            board[2][i] = null;
            board[3][i] = null;
            board[4][i] = null;
            board[5][i] = null;
            board[6][i] = new Pawn(true);

        }
    }

    /**
     * Prints the current state of the game board to the console. 
     * Shows the positions of all the pieces and the letters representing the columns and the numbers representing the rows.
     */
    public static void printGame() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (board[i][j] != null) {
                    System.out.print(board[i][j] + " ");
                } else {
                    if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
                        System.out.print("## ");
                    } else {
                        System.out.print("   ");
                    }
                }

            }
            System.out.println(8 - i);
        }
        for (int i = 0; i < 8; i++) {
            System.out.print(" " + (char) ('a' + i) + " ");
        }
        System.out.println('\n');

    }

    /**
     * Plays one turn of chess.
     * If a player is in check, checks for checkmate.
     * Promotes a pawn if it reaches the end of the board.
     * Continues to prompt for a legal move until a valid one is entered.
     * 
     */
    public static void playTurn() {
        int[] kingPos = getKingPos(whiteTurn);
        Piece tempKing = board[kingPos[0]][kingPos[1]];
        boolean currCheck = false;
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                if (board[i][j]==null) continue;
                if (board[i][j].isLegalMove(i, kingPos[0], j, kingPos[1])){
                    board[i][j]=board[kingPos[0]][kingPos[1]];
                    board[kingPos[0]][kingPos[1]]=tempKing;
                    System.out.println("Check");
                    if (tempKing.isWhite()) whiteInCheck=true;
                    else blackInCheck=true;
                    currCheck=true;
                    break;
                }
            }
        }
        if (!currCheck){
            if (tempKing.isWhite()) whiteInCheck=false;
            else blackInCheck=false;
        }
        if (whiteTurn && whiteInCheck){
            boolean mate = noValidMoves();
            if (mate){
                System.out.println("Checkmate");
                end="Black Wins";
                return;
            }
        }
        if (!whiteTurn && blackInCheck){
            boolean mate = noValidMoves();
            if (mate){
                System.out.println("Checkmate");
                end="White Wins";
                return;
            }
        }

        if (noValidMoves()){
            end = "Stalemate";
            return;
        }
        
        boolean legalMove = false;
        while (!legalMove) {
            if (whiteTurn) {
                System.out.print("White's Move: ");
            } else {
                System.out.print("Black's Move: ");
            }
            String move = in.nextLine();
            if (move.contains("resign")) {
                end = whiteTurn ? "Black Wins" : "White wins";
                break;
            } else if (move.contains("draw?")) {
                end = "draw";
                break;
            }
            // If no draw
            else {
                int[] src = getIndices(move.substring(0, 2));
                if (board[src[0]][src[1]] != null && board[src[0]][src[1]].isWhite() == whiteTurn) {
                    int[] dest = getIndices(move.substring(3, 5));
                    Piece endPiece = board[dest[0]][dest[1]];
                    Piece startPiece = board[src[0]][src[1]];
                    String promoteTo = null;

                    if (canBePromoted(src, dest[0])){
                        promoteTo = move.substring(move.length() - 1);
                        legalMove = board[src[0]][src[1]].isLegalMove(src[0], dest[0], src[1], dest[1]);
                    }
                    else if (move.length() > 5) legalMove = false;
                    else legalMove = board[src[0]][src[1]].isLegalMove(src[0], dest[0], src[1], dest[1]);
                    
                    if (legalMove){
                        kingPos = getKingPos(whiteTurn);
                        tempKing = board[kingPos[0]][kingPos[1]];
                        for (int i=0; i<8; i++){
                            for (int j=0; j<8; j++){
                                if (board[i][j]==null) continue;
                                if (board[i][j].isLegalMove(i, kingPos[0], j, kingPos[1])){
                                    board[i][j]=board[kingPos[0]][kingPos[1]];
                                    board[kingPos[0]][kingPos[1]]=tempKing;
                                    board[src[0]][src[1]] = startPiece;
                                    board[dest[0]][dest[1]] = endPiece;
                                    legalMove=false;
                                    break;
                                }
                            }
                        }
                    }

                    if (legalMove && promoteTo != null) promote(dest, promoteTo);
                }
            }
            if(!legalMove) System.out.println("Illegal move, try again");
            else whiteTurn = !whiteTurn;
        }
    }

    private static boolean noValidMoves() {
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                if (board[i][j]==null) continue;
                if (board[i][j].isWhite()!=whiteTurn) continue;
                Piece aPiece=board[i][j];
                    for (int x=0; x<8; x++){
                        for (int y=0; y<8;y++){
                            Piece bPiece = board[x][y];
                            boolean canMove = aPiece.isLegalMove(i, x, j, y);
                            if (canMove){
                                int[] kingPos = getKingPos(whiteTurn);
                                Piece tempKing = board[kingPos[0]][kingPos[1]];
                                for (int w=0; w<8; w++){
                                    for (int z=0; z<8; z++){
                                        if (board[w][z]==null) continue;
                                        if (board[w][z].isLegalMove(w, kingPos[0], z, kingPos[1])){
                                            board[w][z]=board[kingPos[0]][kingPos[1]];
                                            board[kingPos[0]][kingPos[1]]=tempKing;
                                            board[i][j] = aPiece;
                                            board[x][y] = bPiece;
                                            canMove=false;
                                            // break;
                                        }
                                    }
                                }
                            }
                            if (canMove){
                                board[i][j] = aPiece;
                                board[x][y]=bPiece;
                                return false;
                            }
                    }
                }
            }
        }
        return true;
    }

    private static int[] getKingPos(boolean w) {
        int[] pos = new int[2];
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                if (board[i][j] instanceof King){
                    if (board[i][j].isWhite()==w){
                        pos[0]=i;
                        pos[1]=j;
                    }
                }
            }
        }
        return pos;
    }

    private static int[] getIndices(String spot) {
        char file = spot.charAt(0);
        int rank = spot.charAt(1) - '0';
        return new int[] { 8 - rank, file - 'a' };
    }

    private static boolean canBePromoted(int[] src, int destRow) {
        Piece p = board[src[0]][src[1]];
        if(p instanceof Pawn){
            if(p.isWhite() && destRow == 0 || !p.isWhite() && destRow == 7){
                return true;
            }
        }
        return false;
    }

    private static void promote(int[] dest, String promoteTo) {
        switch (promoteTo) {
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

    /**
     * Returns the ending text for the chess game.
     * This method is called after the game has ended and
     * the end state has been determined (checkmate, draw, or resignation).
     * @return A string representing the end state of the game.
     */
    public static String getEndText() {
        return end;
    }

    public static void main(String[] args) {
        initGame();
        while (Chess.getEndText() == null) {
            printGame();
            playTurn();
        }
        System.out.println(Chess.getEndText());
    }
}