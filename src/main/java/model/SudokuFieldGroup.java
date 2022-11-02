package model;

public abstract class SudokuFieldGroup {

    public static final int SIZE = 0;
    protected SudokuField[] fields;

    public SudokuFieldGroup(final SudokuField[] fields) {
        if (fields.length != SIZE) {
            throw new SizeException("Length must be 9.");
        }
        this.fields = fields;
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int i2 = i + 1; i2 < 9; i2++) {
                if (fields[i].getFieldValue() == fields[i2].getFieldValue()) {
                    return false;
                }
            }
        }

        return true;
    }
}
