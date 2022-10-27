package model;

import java.util.Arrays;

public class SudokuBoard {

    public final int size = 9;
    private final SudokuSolver sudokuSolver;
    private final int[][] board = new int[size][size];


    public SudokuBoard() {
        this.sudokuSolver = new BacktrackingSudokuSolver();
    }

    public int get(int x, int y) {
        return board[x][y];
    }

    public void set(int x, int y, int value) {
        this.board[x][y] = value;
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    public boolean checkBoard() {
        int rep;
        //sprawdzanie wierszy
        for (int i = 0; i < 9; i++) {
            rep = 0;
            for (int j = 0; j < 9; j++) {
                for (int num = 1; num < 10; num++) {
                    if (board[i][j] == num) {
                        rep++;
                    }
                }
            }
            if (rep != 9) {
                return false;
            }
        }

        //sprawdzanie kolumn
        for (int i = 0; i < 9; i++) {
            rep = 0;
            for (int j = 0; j < 9; j++) {
                for (int num = 1; num < 10; num++) {
                    if (board[j][i] == num) {
                        rep++;
                    }
                }
            }
            if (rep != 9) {
                return false;
            }
        }

        //sprawdzanie małych kwadratów
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rep = 0;
                for (int rowCheck = 0; rowCheck < 3; rowCheck++) {
                    for (int colCheck = 0; colCheck < 3; colCheck++) {
                        for (int num = 1; num < 10; num++) {
                            if (board[i * 3 + rowCheck][j * 3 + colCheck] == num) {
                                rep++;
                            }
                        }

                    }
                }
                if (rep != 9) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] copyBoard() {
        int[][] copiedBoard = new int[size][];
        for (int i = 0; i < size; i++) {
            copiedBoard[i] = Arrays.copyOf(board[i], size);
        }
        return copiedBoard;
    }

    /*public void clearBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = 0;
            }
        }
    }

    public void printBoard() {
        System.out.println("************");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("************");
    }*/

}


