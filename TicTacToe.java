import java.util.Random;
import java.util.Scanner;

/**
 * TicTacToe.java
 * UC3: Accept User Slot Input (1-9)
 * Goal: Allow the user to enter a slot number between 1 and 9.
 */
public class TicTacToe {

    static char[][] board = new char[3][3];
    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;

    public static void main(String[] args) {
        // --- UC2: Toss ---
        tossAndAssignSymbols();
        displayTossResult();
        
        // --- UC1: Board Setup ---
        initializeBoard();
        printBoard();

        // --- UC3: Get Input ---
        // For testing UC3, we just call the method and print the result.
        if (isHumanTurn) {
             int slot = getUserSlot();
             System.out.println("Slot entered: " + slot);
        }
    }

    /**
     * Reads an integer slot value from the user.
     * Input: Scanner object
     * Output: Slot number (1-9)
     * Hint: Validation will be added in later use cases.
     */
    static int getUserSlot() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a slot number (1-9): ");
        int slot = scanner.nextInt();
        return slot;
    }

    // --- Methods from UC1 & UC2 ---
    
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