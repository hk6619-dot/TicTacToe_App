import java.util.Random;
import java.util.Scanner;

/**
 * TicTacToe.java
 * UC8: Continuous Turn-Based Game Loop
 * Goal: Continue gameplay until win or draw is detected.
 */
public class TicTacToe {

    static char[][] board = new char[3][3];
    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;
    
    // Game State Flag: Controls the main game loop [cite: 1209]
    static boolean gameOver = false; 

    public static void main(String[] args) {
        // --- Setup (UC1 & UC2) ---
        tossAndAssignSymbols();
        displayTossResult();
        initializeBoard();
        printBoard();

        // --- UC8: The Game Loop ---
        while (!gameOver) {
            
            if (isHumanTurn) {
                System.out.println("--- Human Turn ---");
                int slot = getUserSlot();
                int row = getRowFromSlot(slot);
                int col = getColFromSlot(slot);
                
                if (isValidMove(row, col)) {
                    placeMove(row, col, humanSymbol);
                    printBoard();
                    
                    // Check for end conditions
                    if (checkWin(humanSymbol)) {
                        System.out.println("You win!");
                        gameOver = true;
                    } else if (checkDraw()) {
                        System.out.println("It's a draw!");
                        gameOver = true;
                    } else {
                        // Turn Switching
                        isHumanTurn = false;
                    }
                } else {
                    System.out.println("Invalid move. That slot is taken or out of bounds. Try again.");
                }
                
            } else {
                System.out.println("--- Computer Turn ---");
                System.out.println("Computer is thinking...");
                computerMove();
                printBoard();
                
                // Check for end conditions
                if (checkWin(computerSymbol)) {
                    System.out.println("Computer wins!");
                    gameOver = true;
                } else if (checkDraw()) {
                    System.out.println("It's a draw!");
                    gameOver = true;
                } else {
                    // Turn Switching
                    isHumanTurn = true;
                }
            }
        }
        
        System.out.println("Game Over!");
    }

    // --- Placeholder Methods for Future UCs ---
    
    static boolean checkWin(char symbol) {
        // Stub: Will be implemented in the next UC
        return false; 
    }

    static boolean checkDraw() {
        // Basic check to prevent an infinite loop if the board fills up
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false; // Found an empty spot, not a draw yet
                }
            }
        }
        return true; // No empty spots left
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