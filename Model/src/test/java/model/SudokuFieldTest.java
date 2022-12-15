package model;

import model.exceptions.FieldValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {

    SudokuField sudokuField;
    SudokuField sudokuField2;

    @BeforeEach
    void setup() {
        sudokuField = new SudokuField();
        sudokuField2 = new SudokuField();
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

    @Test
    public void HashCodeFieldTest() {
        sudokuField.setFieldValue(1);
        assertEquals(sudokuField.hashCode(), sudokuField.hashCode());
        sudokuField2.setFieldValue(1);
        assertEquals(sudokuField.hashCode(),sudokuField2.hashCode());
        sudokuField2.setFieldValue(2);
        assertNotEquals(sudokuField.hashCode(),sudokuField2.hashCode());
    }

    @Test
    void ToStringFieldTest() {
        assertEquals(sudokuField.toString(),sudokuField.toString());
        assertNotEquals(sudokuField.toString().length(), 0);
    }

    @Test
    void equalsFieldTest() {
        assertTrue(sudokuField.equals(sudokuField));
        Object o = new Object();
        assertFalse(sudokuField.equals(null));
        assertFalse(sudokuField.equals(o));

        sudokuField.setFieldValue(1);
        sudokuField2.setFieldValue(1);
        assertEquals(sudokuField.equals(sudokuField2),sudokuField2.equals(sudokuField));

    }

    @Test
    public void compareToTest() {
        sudokuField2 = new SudokuField();

        sudokuField.setFieldValue(1);
        sudokuField2.setFieldValue(1);
        assertEquals(sudokuField.compareTo(sudokuField2), 0);
        assertThrows(NullPointerException.class, () -> {
            sudokuField.compareTo(null);
        });
    }

    @Test
    public void isEmptyFieldTest() {
        assertFalse(sudokuField.isEmptyField());
    }

    @Test
    public void setEmptyFieldTest() {
        sudokuField.setEmptyField();
        assertTrue(sudokuField.isEmptyField());
    }

    @Test
    void cloneTest() {
        sudokuField2 = sudokuField.clone();
        assertEquals(sudokuField2, sudokuField);
    }

}