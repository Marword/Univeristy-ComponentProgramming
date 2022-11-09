package model;

import java.util.ArrayList;
import java.util.List;

public abstract class SudokuFieldGroup {

    public static final int size = 9;
    protected List<SudokuField> fields;

    public SudokuFieldGroup(final List<SudokuField> fields) {
        this.fields = fields;
    }

    public boolean verify() {
        for (int i = 0; i < size; i++) {
            for (int x = i + 1; x < size; x++) {
                if (fields.get(i).getFieldValue() == fields.get(x).getFieldValue()) {
                    return false;
                }
            }
        }

        return true;
    }
}
