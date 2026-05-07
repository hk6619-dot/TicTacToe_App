import java.util.Random;
import java.util.Scanner;

/**
 * TicTacToe.java
 * UC9: Check Winning Condition
 * Goal: Detect if a player has won the game by checking rows, columns, and diagonals.
 */
public class TicTacToe {

    static char[][] board = new char[3][3];
    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;
    static boolean gameOver = false; 

    public static void main(String[] args) {
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
                    
                    // UC9: Check if the human won
                    if (hasWon(humanSymbol)) {
                        System.out.println("Congratulations! You win!");
                        gameOver = true;
                    } else if (checkDraw()) {
                        System.out.println("It's a draw!");
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
                
                // UC9: Check if the computer won
                if (hasWon(computerSymbol)) {
                    System.out.println("Computer wins! Better luck next time.");
                    gameOver = true;
                } else if (checkDraw()) {
                    System.out.println("It's a draw!");
                    gameOver = true;
                } else {
                    isHumanTurn = true;
                }
            }
        }
        
        System.out.println("Game Over!");
    }

    /**
     * Checks all possible winning patterns for the given symbol.
     * Input: Player symbol ('X' or 'O')
     * Output: true if win detected.
     */
    static boolean hasWon(char symbol) {
        // Loop-Based Checks: Rows and Columns
        for (int i = 0; i < 3; i++) {
            // Check Row 'i'
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return true;
            }
            // Check Column 'i'
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
                return true;
            }
        }

        // Logical Conditions: Diagonals
        // Top-left to bottom-right
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;
        }
        // Top-right to bottom-left
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true;
        }

        // If no patterns match, they haven't won yet
        return false;
    }

    // --- Placeholder for UC10 ---
    static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false; 
                }
            }
        }
        return true; 
    }

    // --- Methods from UC1 to UC7 ---
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