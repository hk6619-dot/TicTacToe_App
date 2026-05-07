/**
 * TicTacToe.java - A simple console-based Tic-Tac-Toe game.
 * * UC 1: Display Empty Tic-Tac-Toe Board
 * Goal: Display an empty 3x3 Tic-Tac-Toe board on the console.
 */
public class TicTacToe {

    // 2D Array: Represents the 3x3 board
    static char[][] board = new char[3][3];

    public static void main(String[] args) {
        // Flow: Initialize board -> Print empty board
        initializeBoard();
        printBoard();
    }

    /**
     * Initialization Logic:
     * Fills the 3x3 board array with '-' to indicate empty cells.
     */
    static void initializeBoard() {
        // Nested Loops: Iterate through rows and columns
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    /**
     * Console Output Formatting:
     * Prints the current state of the board to the console.
     */
    static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
}