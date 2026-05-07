import java.util.Random;
import java.util.Scanner;

/**
 * TicTacToe.java
 * UC6: Place Move on Board
 * Goal: Update the board with the given symbol. [cite: 1121, 1122]
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

        // --- Gameplay Loop (UC3, UC4, UC5 & UC6) ---
        if (isHumanTurn) {
             int slot = getUserSlot();
             int row = getRowFromSlot(slot);
             int col = getColFromSlot(slot);
             
             if (isValidMove(row, col)) {
                 System.out.println("Valid move! Processing...");
                 
                 // UC6: Place the move and show the updated board
                 placeMove(row, col, humanSymbol);
                 printBoard();
             } else {
                 System.out.println("Invalid move. That slot is either taken or out of bounds.");
             }
        } else {
             System.out.println("Computer is thinking...");
             // Future UC: Computer logic goes here
        }
    }

    /**
     * Updates the board by placing the given symbol at
     * the specified row and column. [cite: 1146, 1147]
     * Input: Row, Column, Symbol [cite: 1148]
     * Hint: Assume the move is already validated. 
     */
    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    // --- Methods from UC1 to UC5 ---
    
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