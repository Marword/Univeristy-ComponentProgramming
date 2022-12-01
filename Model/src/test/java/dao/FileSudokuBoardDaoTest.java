package dao;

import model.BacktrackingSudokuSolver;
import model.SudokuBoard;
import model.SudokuSolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {
    SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
    SudokuBoard sudokuBoard2 = null;
    Dao<SudokuBoard> fileSudokuBoardDao;
    SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();


    @Test
    void writeReadTest() throws IOException {
        fileSudokuBoardDao = factory.getFileDao("test");
        fileSudokuBoardDao.write(sudokuBoard);
        sudokuBoard2 = fileSudokuBoardDao.read();
        assertEquals(sudokuBoard, sudokuBoard2);
    }

    @Test
    void readIOExceptionTest() {
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            fileSudokuBoardDao = factory.getFileDao("?");
            fileSudokuBoardDao.read();
        });
    }

    @Test
    public void writeIOExceptionTest() {
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            fileSudokuBoardDao = factory.getFileDao("?");
            fileSudokuBoardDao.write(sudokuBoard);
        });
    }

    @Test
    public void closeTest() {
        Assertions.assertThrows(Exception.class, () -> {
          fileSudokuBoardDao.close();
        });
    }
}