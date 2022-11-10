package model;

import static org.junit.jupiter.api.Assertions.*;

import model.field.SudokuColumn;
import model.field.SudokuRow;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SudokuFieldGroupTest {

    @Test
    public void verifyValidTest() {
        SudokuRow sudokuRow = new SudokuRow(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
        assertTrue(sudokuRow.verify());
    }

    @Test
    public void verifyInvalidTest() {
        SudokuColumn sudokuColumn = new SudokuColumn(Arrays.asList(
                new SudokuField(2),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
        assertFalse(sudokuColumn.verify());
    }


}