package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {
    private SudokuBoard sudo_board;
    private SudokuBoard sudo_board2;


    @BeforeEach
    void startup() {
        sudo_board = new SudokuBoard(new BacktrackingSudokuSolver());
        sudo_board2 = new SudokuBoard(new BacktrackingSudokuSolver());

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
    public void checkBoardRowTest() {
        assertFalse(sudo_board.checkRow(1));
        sudo_board.solveGame();
        assertTrue(sudo_board.checkRow(1));

    }

    @Test
    public void checkBoardColumnTest() {
        assertFalse(sudo_board.checkColumn(1));
        sudo_board.solveGame();
        assertTrue(sudo_board.checkColumn(1));

    }

    @Test
    public void checkBoardBoxTest() {
        assertFalse(sudo_board.checkBox(1, 1));
        sudo_board.solveGame();
        assertTrue(sudo_board.checkBox(1, 1));

    }

    @Test
    public void checkBoardTest() {
        assertFalse(sudo_board.checkBoard());
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

    @Test
    public void HashCodeTest() {
        assertEquals(sudo_board.hashCode(),sudo_board.hashCode());
        sudo_board2.solveGame();
        assertNotEquals(sudo_board2.hashCode(),sudo_board.hashCode());
    }

    @Test
    void ToStringTest() {
        assertEquals(sudo_board.toString(),sudo_board.toString());
        sudo_board2.solveGame();
        assertNotEquals(sudo_board2.toString(),sudo_board.toString());
        assertNotEquals(sudo_board.toString().length(), 0);
    }

    @Test
    void equalsTest() {

       assertTrue(sudo_board.equals(sudo_board));

       assertFalse(sudo_board.equals(null));

        sudo_board2.solveGame();
        assertEquals(sudo_board.equals(sudo_board2),sudo_board2.equals(sudo_board));
        sudo_board2.set(1,1,3);
        sudo_board.set(1,1,1);
        assertFalse(sudo_board.equals(sudo_board2));

    }

    @Test
    void consistencyEqualsHashCodeTest() {
        sudo_board2.solveGame();
        if (sudo_board.hashCode() == sudo_board2.hashCode()) {
            assertEquals(sudo_board, sudo_board2);
        }

    }



}