package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {
    private SudokuBoard sudo_board;

    @BeforeEach
    public void startup() {
        sudo_board = new SudokuBoard();
        sudo_board.solveGame();
    }
    @Test
    public void differentArrangementsTest() {
        SudokuBoard sudo_board1 = new SudokuBoard();
        sudo_board1.solveGame();

        int[][] board1 = sudo_board.copyBoard();
        int[][] board2 = sudo_board1.copyBoard();
        assertFalse(Arrays.deepEquals(board1, board2));
    }

    @Test
    public void solveGameTest()
    {
        assertTrue(sudo_board.checkBoard());

    }

    @Test
    public void copyBoardTest() {
        int[][] board1 = sudo_board.copyBoard();
        int[][] board2 = sudo_board.copyBoard();
        assertTrue(Arrays.deepEquals(board1, board2));
    }

    @Test
    public void getSetTest(){
        sudo_board.set(1,1,2);
        assertEquals(sudo_board.get(1,1), 2);
    }


}