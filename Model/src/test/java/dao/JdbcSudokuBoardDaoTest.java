package dao;

import model.exceptions.DaoException;
import model.exceptions.JdbcDatabaseException;
import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import model.exceptions.FileDaoException;
import model.BacktrackingSudokuSolver;
import model.SudokuBoard;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dao.JdbcSudokuBoardDao;

import static dao.JdbcSudokuBoardDao.createTables;

import static org.junit.jupiter.api.Assertions.*;

class JdbcSudokuBoardDaoTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
    private SudokuBoard sudokuBoard2;
    private Dao<SudokuBoard> databaseSudokuBoardDao;

    /*------------------------ METHODS REGION ------------------------*/

    @Test
    public void writeReadDbTest() throws IOException {
        databaseSudokuBoardDao = factory.getDatabaseDao("xxx.db");
        createTables();
        databaseSudokuBoardDao.write(sudokuBoard);
        sudokuBoard2 = databaseSudokuBoardDao.read();

        assertEquals(sudokuBoard, sudokuBoard2);
    }



}