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
        List<SudokuField> cfields = new ArrayList<>(Collections.unmodifiableList(fields));
        return new SudokuRow(cfields);
    }
}
