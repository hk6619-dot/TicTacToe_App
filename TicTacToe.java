import java.util.Random;

/**
 * TicTacToe.java
 * UC2: Toss to Decide First Player and Symbol
 * Goal: Randomly decide who plays first and assign symbols (X or O).
 */
public class TicTacToe {

    // 2D Array: Represents the 3x3 board
    static char[][] board = new char[3][3];
    
    // Game State Variables
    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;

    public static void main(String[] args) {
        // First we decide the players and symbols
        tossAndAssignSymbols();
        displayTossResult();
        
        // Then initialize and show the board
        initializeBoard();
        printBoard();
    }

    /**
     * Randomly decides who starts and assigns 'X' and 'O'.
     * The first player gets 'X', the second gets 'O'.
     */
    static void tossAndAssignSymbols() {
        Random random = new Random();
        
        // Generate 0 or 1. If 0, human starts. If 1, computer starts.
        int tossResult = random.nextInt(2);
        
        if (tossResult == 0) {
            isHumanTurn = true;
            humanSymbol = 'X';
            computerSymbol = 'O';
        } else {
            isHumanTurn = false;
            computerSymbol = 'X';
            humanSymbol = 'O';
        }
    }

    /**
     * Displays the result of the toss to the console.
     */
    static void displayTossResult() {
        if (isHumanTurn) {
            System.out.println("You won the toss! You play first.");
        } else {
            System.out.println("Computer won the toss. Computer plays first.");
        }
        System.out.println("Your symbol is: " + humanSymbol);
        System.out.println("Computer symbol is: " + computerSymbol);
        System.out.println();
    }

    // --- Methods from UC1 ---
    
    static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

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