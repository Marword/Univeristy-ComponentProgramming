package model.field;

import java.util.List;
import model.SudokuField;
import model.SudokuFieldGroup;

public class SudokuBox extends SudokuFieldGroup {
    public static final int BOX_SIZE = 3;

    public SudokuBox(final List<SudokuField> fields) {
        super(fields);
    }
}


