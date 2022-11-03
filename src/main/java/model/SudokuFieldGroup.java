package model;

public abstract class SudokuFieldGroup {

    public static final int size = 9;
    protected SudokuField[] fields;

    public SudokuFieldGroup(final SudokuField[] fields) {
        this.fields = fields;
    }

    public boolean verify() {
        for (int i = 0; i < size; i++) {
            for (int x = i + 1; x < size; x++) {
                if (fields[i].getFieldValue() == fields[x].getFieldValue()) {
                    return false;
                }
            }
        }

        return true;
    }
}
