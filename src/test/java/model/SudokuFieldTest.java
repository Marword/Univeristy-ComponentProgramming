package model;

import model.exceptions.FieldValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {

    SudokuField sudokuField;

    @BeforeEach
    void setup() {
        sudokuField = new SudokuField();
    }
    @Test
    void getFieldValue() {
        assertEquals(0, sudokuField.getFieldValue());
    }

    @Test
    void setFieldValue() {
        sudokuField.setFieldValue(1);
        assertEquals(1, sudokuField.getFieldValue());
        sudokuField.setFieldValue(7);
        assertEquals(7, sudokuField.getFieldValue());
    }

    @Test
    void setWrongFieldValue() {
        assertThrows(FieldValueException.class, () -> sudokuField.setFieldValue(10));
        assertThrows(FieldValueException.class, () -> sudokuField.setFieldValue(-10));
    }



}