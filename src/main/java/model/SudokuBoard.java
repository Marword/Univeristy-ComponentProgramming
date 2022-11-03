package model;

import java.util.Arrays;
import model.field.SudokuBox;
import model.field.SudokuColumn;
import model.field.SudokuRow;


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

    public SudokuRow getRow(int row) {
        //SudokuRow row = new Sud
        SudokuField[] fields = new SudokuField[size];
        for (int i = 0; i < size; i++) {
            fields[i].setFieldValue(get(row, i));
        }

        //return row;
        return new SudokuRow(fields);
    }

    public SudokuColumn getColumn(int col) {
        SudokuField[] fields = new SudokuField[size];

        for (int i = 0; i < size; i++) {
            fields[i].setFieldValue(get(i, col));
        }
        return new SudokuColumn(fields);
    }

    public SudokuBox getBox(int row, int col) {
        SudokuField[] fields = new SudokuField[size];
        int x = row - row % 3;
        int y = col - col % 3;
        int z = 0;
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                fields[z++].setFieldValue(get(i, j));
            }
        }
        return new SudokuBox(fields);
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

}


