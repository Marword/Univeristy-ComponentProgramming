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
        List<SudokuField> cfields = new ArrayList<>(Collections.unmodifiableList(fields));
        return new SudokuColumn(cfields);
    }
}
