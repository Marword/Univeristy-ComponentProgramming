package model;

import static org.junit.jupiter.api.Assertions.*;

import model.field.SudokuColumn;
import model.field.SudokuRow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SudokuFieldGroupTest {

    SudokuColumn sudokuColumn;
    SudokuRow sudokuRow, sudokuRow2;

    @BeforeEach
    void setup() {
        sudokuRow = new SudokuRow(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));

        sudokuRow2 = new SudokuRow(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));

        sudokuColumn = new SudokuColumn(Arrays.asList(
                new SudokuField(2),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
    }

    @Test
    public void verifyValidTest() {
        assertTrue(sudokuRow.verify());
    }

    @Test
    public void verifyInvalidTest() {
        assertFalse(sudokuColumn.verify());
    }
    @Test
    void ToStringTest() {
        assertEquals(sudokuRow.toString(),sudokuRow.toString());
        assertNotEquals(sudokuRow.toString().length(), 0);
    }

    @Test
    void equalsTest() {
        assertTrue(sudokuRow.equals(sudokuRow));
        assertFalse(sudokuRow.equals(null));
        assertTrue(sudokuRow2.equals(sudokuRow));

    }


    @Test
    public void HashCodeTest() {
        assertEquals(sudokuRow.hashCode(), sudokuRow.hashCode());
        SudokuRow sudokuRow2 = new SudokuRow(Arrays.asList(
                new SudokuField(2),
                new SudokuField(1),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
        assertNotEquals(sudokuRow.hashCode(), sudokuRow2.hashCode());


    }

}