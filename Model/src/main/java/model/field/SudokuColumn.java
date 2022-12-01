package model.field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.SudokuField;
import model.SudokuFieldGroup;


public class SudokuColumn extends SudokuFieldGroup {
    public SudokuColumn(final List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuColumn clone() {
        SudokuColumn col = new SudokuColumn(fields);

        for (int i = 0; i < size; i++) {
            col.fields.set(i, this.fields.get(i));
        }

        return col;
    }
}
