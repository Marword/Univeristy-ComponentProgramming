package model;

import model.field.SudokuBox;
import model.field.SudokuColumn;
import model.field.SudokuRow;


public class SudokuBoard {

    public final int size = 9;
    private final SudokuSolver sudokuSolver;
    private final SudokuField[][] board = new SudokuField[size][size];


    public SudokuBoard(SudokuSolver solver) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = new SudokuField();
            }
        }

        this.sudokuSolver = solver;
    }

    public int get(int i, int j) {
        return board[i][j].getFieldValue();
    }

    public void set(int i, int j, int value) {
        this.board[i][j].setFieldValue(value);
    }

    public SudokuRow getRow(int row) {
        SudokuField[] fields = new SudokuField[SudokuFieldGroup.size];
        for (int i = 0; i < size; i++) {
            fields[i] = board[row][i];
        }

        return new SudokuRow(fields);
    }

    public SudokuColumn getColumn(int col) {
        SudokuField[] fields = new SudokuField[SudokuFieldGroup.size];

        for (int i = 0; i < size; i++) {
            fields[i] = board[i][col];
        }
        return new SudokuColumn(fields);
    }

    public SudokuBox getBox(int row, int col) {
        SudokuField[] fields = new SudokuField[SudokuFieldGroup.size];
        int index = 0;
        for (int i = 0; i < SudokuBox.BOX_SIZE; i++) {
            for (int j = 0; j < SudokuBox.BOX_SIZE; j++) {
                fields[index++] = board[row * 3 + i][col * 3 + j];
            }
        }

        return new SudokuBox(fields);
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
                    if (board[i][j].getFieldValue() == num) {
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
                    if (board[j][i].getFieldValue() == num) {
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
                            if (board[i * 3 + rowCheck][j * 3 + colCheck].getFieldValue() == num) {
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
        int[][] copiedBoard = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copiedBoard[i][j] = board[i][j].getFieldValue();
            }
        }
        return copiedBoard;
    }

}


