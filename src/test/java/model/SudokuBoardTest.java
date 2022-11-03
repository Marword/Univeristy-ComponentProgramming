package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {
    private SudokuBoard sudo_board;


    @BeforeEach
    void startup() {
        sudo_board = new SudokuBoard(new BacktrackingSudokuSolver());

    }
    @Test
    void different_arrangements() {
        SudokuBoard sudo_board1 = new SudokuBoard(new BacktrackingSudokuSolver());
        sudo_board.solveGame();
        sudo_board1.solveGame();
        int[][] board1 = sudo_board.copyBoard();
        int[][] board2 = sudo_board1.copyBoard();
        assertFalse(Arrays.deepEquals(board1, board2));
    }


    @Test
    public void checkBoardTest() {
        assertFalse(sudo_board.checkBoard());
        for (int i=0; i< sudo_board.size; i++) {
            sudo_board.set(i, 0, i+1);
        }
        assertFalse(sudo_board.checkBoard());
        for (int i=0; i< sudo_board.size; i++) {
            sudo_board.set(0, i, i+1);
        }
        assertFalse(sudo_board.checkBoard());
    }
    @Test
    public void checkFullBoardTest() {
        sudo_board.solveGame();
        assertTrue(sudo_board.checkBoard());
    }

    @Test
    public void getSetMethodsTest() {
        assertEquals(sudo_board.get(0, 0), 0);
        sudo_board.set(0, 0, 5);
        assertEquals(sudo_board.get(0, 0), 5);
    }

    @Test
    public void getRowTest() {
        assertNotNull(sudo_board.getRow(2));
    }

    @Test
    public void getColumnTest() {
        assertNotNull(sudo_board.getColumn(2));
    }

    @Test
    public void getBoxTest() {
        assertNotNull(sudo_board.getBox(1, 1));
    }


}