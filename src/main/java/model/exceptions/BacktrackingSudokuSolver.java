package model.exceptions;

import model.SudokuBoard;
import model.SudokuSolver;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BacktrackingSudokuSolver implements SudokuSolver {

    private final List<Integer> numberShuffle = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    public boolean solve(SudokuBoard board) {
        Collections.shuffle(numberShuffle);
        int[] tmp = new int[numberShuffle.size()];
        for (int i = 0; i < numberShuffle.size(); i++) {
            tmp[i] = numberShuffle.get(i);
        }
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (0 == board.get(row,col)) {
                    for (int x = 0; x <= 8; x++) {
                        if (isValid(row, col, tmp[x], board)) {
                            board.set(row,col,tmp[x]);
                            if (solve(board)) {
                                return true;
                            } else {
                                board.set(row,col, 0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidInRow(int rowNumber, int numberToInsert, final SudokuBoard board) {
        //Sprawdzanie wiersza, czy można podaną liczbę dać do niego
        for (int i = 0; i < 9; i++) {
            if (numberToInsert == board.get(rowNumber,i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidInColumn(int columnNumber, int numberToInsert,
                                    final SudokuBoard board) {
        //Sprawdzanie kolumny, czy można podaną liczbę dać do niej
        for (int i = 0; i < 9; i++) {
            if (numberToInsert == board.get(i,columnNumber)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidInBox(int columnNumber, int rowNumber, int numberToInsert,
                                 final SudokuBoard board) {
        //Sprawdzanie boxa, czy można podaną liczbę dać do niego
        int boxRowIndex = rowNumber / (9 / 3);
        int boxColumnIndex = columnNumber / (9 / 3);

        for (int i = 0; i < (9 / 3); i++) {
            for (int j = 0; j < (9 / 3); j++) {
                int boxRow = i + boxRowIndex * 3;
                int boxColumn = j + boxColumnIndex * 3;
                if (board.get(boxRow,boxColumn) == numberToInsert) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(int rowNumber, int columnNumber, int numberToInsert,
                           final SudokuBoard board) {
        return isValidInBox(columnNumber, rowNumber, numberToInsert, board)
                && isValidInRow(rowNumber, numberToInsert, board)
                && isValidInColumn(columnNumber, numberToInsert, board);
    }
}
