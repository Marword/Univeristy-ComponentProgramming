package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

    private SudokuBoard sudokuBoard;
    private Level sudokuLevel;

    @BeforeEach
    void setUp() {
        sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
    }

    @Test
    void valuesTest() {
        sudokuLevel = Level.EASY;
        assertEquals(sudokuLevel.getGaps(), 30);

        sudokuLevel = Level.MEDIUM;
        assertEquals(sudokuLevel.getGaps(), 40);

        sudokuLevel = Level.HARD;
        assertEquals(sudokuLevel.getGaps(), 50);

        sudokuLevel = Level.MASTER;
        assertEquals(sudokuLevel.getGaps(), 60);
    }

    @Test
    void removeValuesTest() {
        sudokuBoard.solveGame();
        sudokuLevel = Level.EASY;
        sudokuLevel.removeValues(sudokuBoard);
        int gapsNo =0;
        for (int i = 0; i < sudokuBoard.size; i++) {
            for (int j = 0; j < sudokuBoard.size; j++) {
                if (sudokuBoard.get(i, j) == 0) {
                    gapsNo++;
                }
            }
        }
        assertEquals(sudokuLevel.getGaps(), gapsNo);

    }
}