package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {
    private SudokuBoard sudo_board;
    private SudokuBoard sudo_board1;


    @BeforeEach
    void startup() {
        sudo_board = new SudokuBoard();
        sudo_board.solveGame();
        sudo_board1 = new SudokuBoard();
        sudo_board1.solveGame();
    }
    @Test
    void different_arrangements() {
        int[][] board1 = sudo_board.copyBoard();
        int[][] board2 = sudo_board1.copyBoard();
        assertFalse(Arrays.deepEquals(board1, board2));
    }


}