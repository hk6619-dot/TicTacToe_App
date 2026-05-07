import java.util.Random;
import java.util.Scanner;

/**
 * TicTacToe.java
 * UC7: Computer Makes a Random Move (Easy Level)
 * Goal: Allow the computer to make a random valid move. [cite: 1158]
 */
public class TicTacToe {

    static char[][] board = new char[3][3];
    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;

    public static void main(String[] args) {
        // --- Setup (UC1 & UC2) ---
        tossAndAssignSymbols();
        displayTossResult();
        initializeBoard();
        printBoard();

        // --- Gameplay Testing ---
        if (isHumanTurn) {
             System.out.println("--- Human Turn ---");
             int slot = getUserSlot();
             int row = getRowFromSlot(slot);
             int col = getColFromSlot(slot);
             
             if (isValidMove(row, col)) {
                 placeMove(row, col, humanSymbol);
                 printBoard();
             } else {
                 System.out.println("Invalid move. That slot is taken.");
             }
        } else {
             // UC7: Triggering the computer move 
             System.out.println("--- Computer Turn ---");
             System.out.println("Computer is thinking...");
             computerMove();
             printBoard();
        }
    }

    /**
     * Generates random slot values until a valid move is found,
     * then places the computer symbol on the board.
     */
    static void computerMove() {
        Random random = new Random();
        
        // Loop Until Valid: Keep trying until we find an empty spot [cite: 1165]
        while (true) {
            // Generate a random slot from 1 to 9 
            int slot = random.nextInt(9) + 1; 
            
            // Logic Reuse: Convert slot to row and col [cite: 1166]
            int row = getRowFromSlot(slot);
            int col = getColFromSlot(slot);
            
            // Ensure move validity using our existing method [cite: 1169]
            if (isValidMove(row, col)) {
                System.out.println("Computer chose slot: " + slot);
                placeMove(row, col, computerSymbol); // Place the move 
                break; // Exit the loop once a valid move is placed
            }
        }
    }

    // --- Methods from UC1 to UC6 ---
    
    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    static boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        if (board[row][col] != '-') {
            return false;
        }
        return true;
    }
    
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