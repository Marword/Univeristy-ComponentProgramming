package dao;

import model.BacktrackingSudokuSolver;
import model.SudokuBoard;
import model.SudokuSolver;
import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private SudokuBoard sudokuBoard = new SudokuBoard();

    private Dao<SudokuBoard> fileSudokuBoardDao;
    private SudokuBoard sudokuBoardSecond;

    @Test
    public void writeReadTest() {
        fileSudokuBoardDao = factory.getFileDao("xxx");
        fileSudokuBoardDao.write(sudokuBoard);
        sudokuBoardSecond = fileSudokuBoardDao.read();

        assertEquals(sudokuBoard, sudokuBoardSecond);
    }

    @Test(expected = FileOperationException.class)
    public void readIOExceptionTest() {
        fileSudokuBoardDao = factory.getFileDao("yyy");
        fileSudokuBoardDao.read();
    }

    @Test(expected = FileOperationException.class)
    public void writeIOExceptionTest() {
        if (SystemUtils.IS_OS_WINDOWS) {
            fileSudokuBoardDao = factory.getFileDao("?");
        } else if (SystemUtils.IS_OS_LINUX) {
            fileSudokuBoardDao = factory.getFileDao("/");
        } else {
            fileSudokuBoardDao = factory.getFileDao("?");
        }
        fileSudokuBoardDao.write(sudokuBoard);
    }

}