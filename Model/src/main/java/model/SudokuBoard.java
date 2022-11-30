package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import model.field.SudokuBox;
import model.field.SudokuColumn;
import model.field.SudokuRow;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuBoard implements Serializable, Cloneable {

    public final int size = 9;
    private final SudokuSolver sudokuSolver;
    private final List<List<SudokuField>> board;


    public SudokuBoard(SudokuSolver solver) {
        board = Arrays.asList(new List[size]);

        for (int i = 0; i < size; i++) {
            board.set(i, Arrays.asList(new SudokuField[size]));
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board.get(i).set(j, new SudokuField());
            }
        }

        this.sudokuSolver = solver;
    }

    public int get(int i, int j) {
        return board.get(i).get(j).getFieldValue();
    }

    public void set(int i, int j, int value) {
        this.board.get(i).get(j).setFieldValue(value);
    }

    public SudokuRow getRow(int row) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SudokuFieldGroup.size]);
        for (int i = 0; i < size; i++) {
            fields.set(i, board.get(row).get(i));
        }

        return new SudokuRow(fields);
    }

    public SudokuColumn getColumn(int col) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SudokuFieldGroup.size]);
        for (int i = 0; i < size; i++) {
            fields.set(i, board.get(i).get(col));
        }

        return new SudokuColumn(fields);
    }

    public SudokuBox getBox(int row, int col) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SudokuFieldGroup.size]);
        int index = 0;
        for (int i = 0; i < SudokuBox.BOX_SIZE; i++) {
            for (int j = 0; j < SudokuBox.BOX_SIZE; j++) {
                fields.set(index++, board.get(row * 3 + i).get(col * 3 + j));
            }
        }

        return new SudokuBox(fields);
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    public boolean checkRow(int row) {
        int rep = 0;
        for (int j = 0; j < 9; j++) {
            for (int num = 1; num < 10; num++) {
                if (board.get(row).get(j).getFieldValue() == num) {
                    rep++;
                }
            }
        }
        if (rep != 9) {
            return false;
        }
        return true;
    }

    public boolean checkColumn(int col) {
        int rep = 0;
        for (int j = 0; j < 9; j++) {
            for (int num = 1; num < 10; num++) {
                if (board.get(j).get(col).getFieldValue() == num) {
                    rep++;
                }
            }
        }
        if (rep != 9) {
            return false;
        }
        return true;
    }

    public boolean checkBox(int rowIndex, int colIndex) {
        int rep = 0;
        for (int rowCheck = 0; rowCheck < 3; rowCheck++) {
            for (int colCheck = 0; colCheck < 3; colCheck++) {
                for (int num = 1; num < 10; num++) {
                    if (board.get(rowIndex * 3 + rowCheck).get(colIndex * 3 + colCheck)
                            .getFieldValue() == num) {
                        rep++;
                    }
                }

            }
        }
        if (rep != 9) {
            return false;
        }
        return true;

    }

    public boolean checkBoard() {
        int rep = 0;
        for (int i = 0; i < 9; i++) {
            if (checkRow(i)) {
                rep++;
            }
            if (checkColumn(i)) {
                rep++;
            }
        }

        //sprawdzanie małych kwadratów
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (checkBox(i, j)) {
                    rep++;
                }
            }

        }

        if (rep != 27) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuBoard that = (SudokuBoard) o;

        return new EqualsBuilder()
                .append(size, that.size)
                .append(sudokuSolver, that.sudokuSolver)
                .append(board, that.board)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(size)
                .append(sudokuSolver)
                .append(board)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("board", board)
                .toString();
    }

    @Override
    public SudokuBoard clone() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard.set(i, j, get(i, j));
            }
        }

        return sudokuBoard;
    }

}


