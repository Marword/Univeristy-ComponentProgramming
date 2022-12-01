package model.field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.SudokuField;
import model.SudokuFieldGroup;


public class SudokuRow extends SudokuFieldGroup {
    public SudokuRow(final List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuRow clone() {
        SudokuRow row = new SudokuRow(fields);

        for (int i = 0; i < size; i++) {
            row.fields.set(i, this.fields.get(i));
        }

        return row;
    }
}
