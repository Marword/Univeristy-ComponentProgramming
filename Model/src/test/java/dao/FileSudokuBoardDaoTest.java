package dao;

import model.BacktrackingSudokuSolver;
import model.SudokuBoard;
import model.SudokuSolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {
    SudokuBoard sudokuBoard;
    SudokuBoard sudokuBoard2 = null;
    Dao<SudokuBoard> fileSudokuBoardDao;

    @BeforeEach
    void setUp() {
        sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        fileSudokuBoardDao = factory.getFileDao("test");
    }
//    @Test
//    void writeReadTest() throws IOException, ClassNotFoundException {
//        fileSudokuBoardDao.write(sudokuBoard);
//        sudokuBoard2 = fileSudokuBoardDao.read();
//        assertEquals(sudokuBoard,sudokuBoard2);
//
//    }

}