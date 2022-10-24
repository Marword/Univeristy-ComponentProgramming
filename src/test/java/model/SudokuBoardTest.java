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
        sudo_board.fillBoard();
        sudo_board1 = new SudokuBoard();
        sudo_board1.fillBoard();
    }
    @Test
    void different_arrangements() {
        int[][] board1 = sudo_board.copyArr();
        int[][] board2 = sudo_board1.copyArr();
        assertFalse(Arrays.deepEquals(board1, board2));
    }


}