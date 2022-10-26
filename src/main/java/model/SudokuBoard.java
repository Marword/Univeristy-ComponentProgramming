package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuBoard {

    private final int size = 9;
    private final int boxSize = 3;
    private List<Integer> randomizuje = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    private final int[][] grid = new int[size][size];

    public SudokuBoard() {
    }

    private boolean isValidInRow(int rowNumber, int numberToInsert) {
        //Sprawdzanie wiersza, czy można podaną liczę dać do niego
        for (int i = 0; i < size; i++) {
            if (numberToInsert == grid[rowNumber][i]) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidInColumn(int columnNumber, int numberToInsert) {
        //Sprawdzanie kolumny, czy można podaną liczę dać do niego
        for (int i = 0; i < size; i++) {
            if (numberToInsert == grid[i][columnNumber]) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidInBox(int columnNumber, int rowNumber, int numberToInsert) {
        //Sprawdzanie boxa, czy można podaną liczbę można dać do niego
        int boxRowIndex = rowNumber / (size / 3);
        int boxColumnIndex = columnNumber / (size / 3);

        for (int i = 0; i < (size / 3); i++) {
            for (int j = 0; j < (size / 3); j++) {
                int boxRow = i + boxRowIndex * 3;
                int boxColumn = j + boxColumnIndex * 3;
                if (grid[boxRow][boxColumn] == numberToInsert) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(int rowNumber, int columnNumber, int numberToInsert) {
        return isValidInBox(columnNumber, rowNumber, numberToInsert)
                && isValidInRow(rowNumber, numberToInsert)
                && isValidInColumn(columnNumber, numberToInsert);
    }

    //backtracking solving(filling array)
    private void shuffleArray(Integer[] currentGuidingRow) {

        List<Integer> guidingList = Arrays.asList(currentGuidingRow);
        Collections.shuffle(guidingList);
        guidingList.toArray(currentGuidingRow);
    }


    public boolean fillBoard() {
        Collections.shuffle(randomizuje);
        int[] tmp = new int[randomizuje.size()];
        for (int i = 0; i < randomizuje.size(); i++) {
            tmp[i] = randomizuje.get(i);
        }
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (0 == grid[row][col]) {
                    for (int x = 0; x <= 8; x++) {
                        if (isValid(row, col, tmp[x])) {
                            grid[row][col] = tmp[x];
                            if (fillBoard()) {
                                return true;
                            } else {
                                grid[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    //wypisanei tablicy zeby sprawdzic
    public void clearBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public void newBoard() {
        clearBoard();
        fillBoard();
    }

    public void printBoard() {
        System.out.println("************");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("************");
    }

    public int[][] copyBoard() {
        int[][] copiedBoard = new int[size][];
        for (int i = 0; i < size; i++) {
            copiedBoard[i] = Arrays.copyOf(grid[i], size);
        }
        return copiedBoard;
    }

    public static void main(String[] args) {
        SudokuBoard sudoku = new SudokuBoard();
        sudoku.fillBoard();
        sudoku.printBoard();
    }

}


