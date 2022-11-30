package model.field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.SudokuField;
import model.SudokuFieldGroup;

public class SudokuBox extends SudokuFieldGroup {
    public static final int BOX_SIZE = 3;

    public SudokuBox(final List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuBox clone() {
        List<SudokuField> cfields = new ArrayList<>(Collections.unmodifiableList(fields));
        return new SudokuBox(cfields);
    }
}


