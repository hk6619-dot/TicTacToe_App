import java.util.Random;
import java.util.Scanner;

/**
 * TicTacToe.java
 * UC10: Detect Draw Condition
 * Goal: Detect when no moves remain and no winner exists.
 */
public class TicTacToe {

    static char[][] board = new char[3][3];
    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;
    static boolean gameOver = false; 

    public static void main(String[] args) {
        System.out.println("Welcome to Tic-Tac-Toe!\n");
        
        // --- Setup ---
        tossAndAssignSymbols();
        displayTossResult();
        initializeBoard();
        printBoard();

        // --- Continuous Game Loop ---
        while (!gameOver) {
            
            if (isHumanTurn) {
                System.out.println("--- Human Turn ---");
                int slot = getUserSlot();
                int row = getRowFromSlot(slot);
                int col = getColFromSlot(slot);
                
                if (isValidMove(row, col)) {
                    placeMove(row, col, humanSymbol);
                    printBoard();
                    
                    if (hasWon(humanSymbol)) {
                        System.out.println("Congratulations! You win!");
                        gameOver = true;
                    } else if (isDraw()) { // UC10: Updated method call
                        System.out.println("It's a draw! Well played.");
                        gameOver = true;
                    } else {
                        isHumanTurn = false;
                    }
                } else {
                    System.out.println("Invalid move. Try again.");
                }
                
            } else {
                System.out.println("--- Computer Turn ---");
                System.out.println("Computer is thinking...");
                computerMove();
                printBoard();
                
                if (hasWon(computerSymbol)) {
                    System.out.println("Computer wins! Better luck next time.");
                    gameOver = true;
                } else if (isDraw()) { // UC10: Updated method call
                    System.out.println("It's a draw! Well played.");
                    gameOver = true;
                } else {
                    isHumanTurn = true;
                }
            }
        }
        
        System.out.println("Game Over!");
    }

    /**
     * Traverses the board to check for any remaining empty cells.
     * Output: true if draw, false otherwise.
     */
    static boolean isDraw() {
        // Loop Traversal: Check every single cell
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == '-') {
                    return false; // Found an empty cell, so it is NOT a draw yet
                }
            }
        }
        return true; // No empty cells found, it MUST be a draw
    }

    // --- Methods from UC1 to UC9 ---
    
    static boolean hasWon(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) return true;
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) return true;
        }
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) return true;
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) return true;
        return false;
    }

    static void computerMove() {
        Random random = new Random();
        while (true) {
            int slot = random.nextInt(9) + 1; 
            int row = getRowFromSlot(slot);
            int col = getColFromSlot(slot);
            if (isValidMove(row, col)) {
                System.out.println("Computer chose slot: " + slot);
                placeMove(row, col, computerSymbol);
                break; 
            }
        }
    }
    
    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    static boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) return false;
        if (board[row][col] != '-') return false;
        return true;
    }
    
    static int getRowFromSlot(int slot) { return (slot - 1) / 3; }
    static int getColFromSlot(int slot) { return (slot - 1) % 3; }
    
    static int getUserSlot() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a slot number (1-9): ");
        return scanner.nextInt();
    }

    static void tossAndAssignSymbols() {
        Random random = new Random();
        if (random.nextInt(2) == 0) {
            isHumanTurn = true; humanSymbol = 'X'; computerSymbol = 'O';
        } else {
            isHumanTurn = false; computerSymbol = 'X'; humanSymbol = 'O';
        }
    }

    static void displayTossResult() {
        if (isHumanTurn) System.out.println("You won the toss! You play first.");
        else System.out.println("Computer won the toss. Computer plays first.");
        System.out.println("Your symbol is: " + humanSymbol + "\nComputer symbol is: " + computerSymbol + "\n");
    }

    static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) board[i][j] = '-';
        }
    }

    static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) System.out.print(board[i][j] + " | ");
            System.out.println("\n-------------");
        }
    }
}