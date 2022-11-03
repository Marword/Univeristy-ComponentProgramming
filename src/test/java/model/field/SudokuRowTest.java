package model.field;

import static org.junit.jupiter.api.Assertions.*;

import model.SudokuField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuRowTest {
    SudokuColumn col;
    SudokuField[] fields;

    @BeforeEach
    void setUp() {
        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField();
            fields[i].setFieldValue(i);
        }
        col = new SudokuColumn(fields);
    }

    @Test
    void columnVerifyTest() {
        assertEquals(col.verify(), true);
    }

    @Test
    void columnSetVerifyTest() {

        fields[2].setFieldValue(1);
        SudokuColumn column1 = new SudokuColumn(fields);
        assertEquals(column1.verify(), false);
    }

}