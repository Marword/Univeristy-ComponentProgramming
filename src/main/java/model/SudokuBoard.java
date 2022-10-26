package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuBoard {

    public final int size = 9;
    public final int boxSize = 3;
    //private List<Integer> randomizuje = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    private SudokuSolver sudokuSolver;
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

    private void shuffleArray(Integer[] currentGuidingRow) {

        List<Integer> guidingList = Arrays.asList(currentGuidingRow);
        Collections.shuffle(guidingList);
        guidingList.toArray(currentGuidingRow);
    }
//    private boolean isValidInRow(int rowNumber, int numberToInsert) {
//        //Sprawdzanie wiersza, czy można podaną liczę dać do niego
//        for (int i = 0; i < size; i++) {
//            if (numberToInsert == board[rowNumber][i]) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean isValidInColumn(int columnNumber, int numberToInsert) {
//        //Sprawdzanie kolumny, czy można podaną liczę dać do niego
//        for (int i = 0; i < size; i++) {
//            if (numberToInsert == board[i][columnNumber]) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean isValidInBox(int columnNumber, int rowNumber, int numberToInsert) {
//        //Sprawdzanie boxa, czy można podaną liczbę można dać do niego
//        int boxRowIndex = rowNumber / (size / 3);
//        int boxColumnIndex = columnNumber / (size / 3);
//
//        for (int i = 0; i < (size / 3); i++) {
//            for (int j = 0; j < (size / 3); j++) {
//                int boxRow = i + boxRowIndex * 3;
//                int boxColumn = j + boxColumnIndex * 3;
//                if (board[boxRow][boxColumn] == numberToInsert) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    public boolean isValid(int rowNumber, int columnNumber, int numberToInsert) {
//        return isValidInBox(columnNumber, rowNumber, numberToInsert)
//                && isValidInRow(rowNumber, numberToInsert)
//                && isValidInColumn(columnNumber, numberToInsert);
//    }


    //wypisanei tablicy zeby sprawdzic
    public void clearBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = 0;
            }
        }
    }

//    public void newBoard() {
//        clearBoard();
//        fillBoard();
//    }

    public void printBoard() {
        System.out.println("************");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("************");
    }

    public int[][] copyBoard() {
        int[][] copiedBoard = new int[size][];
        for (int i = 0; i < size; i++) {
            copiedBoard[i] = Arrays.copyOf(board[i], size);
        }
        return copiedBoard;
    }

    public static void main(String[] args) {
        SudokuBoard sudoku = new SudokuBoard();
        sudoku.solveGame();
        sudoku.printBoard();
    }

}


