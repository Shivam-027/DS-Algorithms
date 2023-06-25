//Nqueens problem

import java.util.Scanner;

public class NQueen {
    static int count = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // take the size of the board from the user
        System.out.print("Enter the size of the board: ");
        int n = scanner.nextInt();

        // take the number of queens from the user
        System.out.print("Enter the number of queens: ");
        int queens = scanner.nextInt();

        scanner.close();

        if (queens > n) {
            System.out.println("The number of queens cannot be greater than the size of the board!");
            return;
        }

        int[][] board = new int[n][n]; // create an empty board
        solveNQueens(board, 0, queens); // call the solveNQueens method to solve the problem
    }

    // solveNQueens method to solve the N-Queens problem using backtracking
    public static boolean solveNQueens(int[][] board, int col, int queens) {
        if (col == queens) { // all queens are placed successfully
            count ++;
            printSolution(board);
            return true;
        }

        boolean res = false;
        for (int i = 0; i < board.length; i++) {
            if (isSafe(board, i, col)) { // check if it is safe to place the queen in this row and column
                board[i][col] = 1; // place the queen
                res = solveNQueens(board, col + 1, queens) || res; // recursively call solveNQueens method
                board[i][col] = 0; // backtrack and remove the queen from this position
            }
        }

        return res;
    }

    // isSafe method to check if it is safe to place the queen in this row and column
    public static boolean isSafe(int[][] board, int row, int col) {
        int i, j;

        // check this row on left side
        for (i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        // check upper diagonal on left side
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // check lower diagonal on left side
        for (i = row, j = col; j >= 0 && i < board.length; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    // printSolution method to print the board
    public static void printSolution(int[][] board) {
        int n = board.length;
        System.out.println();
        System.out.println("Solution Number: " + count);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
