import java.util.Random;
import java.util.Scanner;

/**
 * TicTacToe.java
 * UC5: Validate User Move
 * Goal: Ensure the move is within bounds and the cell is empty. [cite: 1086]
 */
public class TicTacToe {

    static char[][] board = new char[3][3];
    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;

    public static void main(String[] args) {
        // --- Setup ---
        tossAndAssignSymbols();
        displayTossResult();
        initializeBoard();
        printBoard();

        // --- UC3, UC4 & UC5: Get Input, Convert, and Validate ---
        if (isHumanTurn) {
             int slot = getUserSlot();
             int row = getRowFromSlot(slot);
             int col = getColFromSlot(slot);
             
             // Check if the move is valid before proceeding
             if (isValidMove(row, col)) {
                 System.out.println("Valid move! Processing...");
                 // Future UC: Actually place the piece here
             } else {
                 System.out.println("Invalid move. That slot is either taken or out of bounds.");
             }
        }
    }

    /**
     * Checks if the given row and column are within bounds
     * and if the target cell is empty.
     * Input: row, column
     * Output: true if valid, false otherwise. [cite: 1112]
     */
    static boolean isValidMove(int row, int col) {
        // Boundary Checking: Ensure row and column must be 0-2 [cite: 1093, 1096]
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        
        // Defensive Programming: Ensure the cell is empty [cite: 1094, 1097]
        if (board[row][col] != '-') {
            return false;
        }
        
        return true;
    }

    // --- Methods from UC1 to UC4 ---
    
    static int getRowFromSlot(int slot) {
        return (slot - 1) / 3; 
    }

    static int getColFromSlot(int slot) {
        return (slot - 1) % 3;
    }
    
    static int getUserSlot() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a slot number (1-9): ");
        return scanner.nextInt();
    }

    static void tossAndAssignSymbols() {
        Random random = new Random();
        if (random.nextInt(2) == 0) {
            isHumanTurn = true;
            humanSymbol = 'X';
            computerSymbol = 'O';
        } else {
            isHumanTurn = false;
            computerSymbol = 'X';
            humanSymbol = 'O';
        }
    }

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