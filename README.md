# Chess Game Simulation using ASCII Board Representation

This code is a Java implementation of a Chess game simulator that uses an ASCII board representation. The game can be played between two human players.

## Requirements

There are no additional dependencies or external libraries needed.

## How to Play

1. Clone or download the code from this repository.
2. Open a terminal or command prompt window and navigate to the folder where the code is saved.
3. Compile the code using the command `javac Chess.java`.
4. Run the game using the command `java Chess`.
5. The game starts with the white player moving first.
6. To make a move, enter the starting and ending positions of the piece you want to move (e.g. e2 e4 to move the pawn from e2 to e4).
7. A player may resign by entering "resign".
8. A player may offer a draw by appending "draw?" to the end of an otherwise regular move. If a draw is offered, the other player is obligated to accept, and the game ends, whatever the actual situation may be. No justification is needed. The other player will simply submit "draw" as the entirety of their next move.
9. A castling move is indicated by specifying where the king begins and ends. For example, white castling king's side would be "e1 g1".
10. A pawn promotion is indicated by putting the piece to be promoted to after the move. For example, promoting a pawn to a knight might be "g7 g8 N". If no promotion piece is indicated, it is assumed to be a queen.
11. The game ends when one player is checkmated, resigns, or if a draw is declared.

## ASCII Board Representation

The board is represented using ASCII characters, with each square represented by two characters. The first character represents the color of the piece on the square ('w' for white, 'b' for black), and the second character represents the type of piece on the square ('K' for king, 'Q' for queen, 'R' for rook, 'B' for bishop, 'N' for knight, 'p' for pawn).

## Authors

This project was created by Krish Patel and Roshan Varadhan.
