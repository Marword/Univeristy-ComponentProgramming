package model.field;

import java.util.List;
import model.SudokuField;
import model.SudokuFieldGroup;


public class SudokuRow extends SudokuFieldGroup {
    public SudokuRow(final List<SudokuField> fields) {
        super(fields);
    }
}
