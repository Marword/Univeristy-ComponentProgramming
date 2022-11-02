package model;

import model.exceptions.FieldValueException;

public class SudokuField {
    private int value;

    public SudokuField() {

    }

    public SudokuField(int value) {
        this.value = value;
    }

    public int getFieldValue() {
        return this.value;
    }

    public void setFieldValue(int value) {
        if (value < 0 || value > 9) {
            throw new FieldValueException("Must be <1,9>");
        }
        this.value = value;
    }
}
