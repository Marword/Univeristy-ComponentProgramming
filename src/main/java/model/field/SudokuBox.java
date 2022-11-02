package model.field;

import model.SudokuField;
import model.SudokuFieldGroup;

public class SudokuBox extends SudokuFieldGroup {
    public static final int BOX_SIZE = 3;

    public SudokuBox(final SudokuField[] fields) {
        super(fields);
    }
}
