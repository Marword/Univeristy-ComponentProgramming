package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuBoard {

    private final int size = 9;
    private final int boxSize = 3;
    private List<Integer> randomizuje = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    private int[][] grid = new int[size][size];

    public SudokuBoard() {
    }

    public final int[][] copyArr() {
        int[][] cboard = new int[size][];
        for (int i = 0; i < size; i++) {
            cboard[i] = Arrays.copyOf(grid[i], size);
        }
        return cboard;
    }

    public boolean isValid(int row, int col, int value) {
        //row check
        for (int i = 0; i < size; i++) {
            if (value == grid[row][i]) {
                return false;
            }
        }
        //column check
        for (int i = 0; i < size; i++) {
            if (value == grid[i][col]) {
                return false;
            }
        }
        //box check
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (value == grid[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    //backtracking solving(filling array)
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
    public final void printArr() {
        for (int[] row : grid) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SudokuBoard sudoku = new SudokuBoard();
        sudoku.fillBoard();
        sudoku.printArr();
    }

}


