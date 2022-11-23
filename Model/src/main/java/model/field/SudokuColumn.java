package model.field;

import java.util.List;
import model.SudokuField;
import model.SudokuFieldGroup;


public class SudokuColumn extends SudokuFieldGroup {
    public SudokuColumn(final List<SudokuField> fields) {
        super(fields);
    }
}
