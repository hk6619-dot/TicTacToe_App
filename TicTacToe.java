import java.util.Random;
import java.util.Scanner;

/**
 * TicTacToe.java
 * UC4: Convert Slot Number (1-9) to Board Index (Row, Column)
 * Goal: Convert user-entered slot number into row and column indices.
 */
public class TicTacToe {

    static char[][] board = new char[3][3];
    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;

    public static void main(String[] args) {
        // --- Previous UCs ---
        tossAndAssignSymbols();
        displayTossResult();
        initializeBoard();
        printBoard();

        // --- UC3 & UC4: Get Input and Convert to Indices ---
        if (isHumanTurn) {
             int slot = getUserSlot();
             
             // Convert slot to zero-based 2D indices 
             int row = getRowFromSlot(slot); 
             int col = getColFromSlot(slot); 
             
             System.out.println("Row: " + row); 
             System.out.println("Column: " + col); 
        }
    }

    /**
     * Converts a slot number (1-9) to a row index (0-2). [cite: 1077]
     */
    static int getRowFromSlot(int slot) { 
        return (slot - 1) / 3; 
    }

    /**
     * Converts a slot number (1-9) to a column index (0-2). [cite: 1078]
     */
    static int getColFromSlot(int slot) { 
        return (slot - 1) % 3;
    }

    // --- Methods from UC1, UC2 & UC3 ---
    
    static int getUserSlot() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a slot number (1-9): ");
        int slot = scanner.nextInt();
        return slot;
    }

    static void tossAndAssignSymbols() {
        Random random = new Random();
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