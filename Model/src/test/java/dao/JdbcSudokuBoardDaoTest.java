package dao;

import org.junit.jupiter.api.Test;
import model.exceptions.FileDaoException;
import model.BacktrackingSudokuSolver;
import model.SudokuBoard;
import java.io.IOException;

import static dao.JdbcSudokuBoardDao.createSudokuBoardsTable;
import static org.junit.jupiter.api.Assertions.*;

class JdbcSudokuBoardDaoTest {

    @Test
    void addtabletest() throws IOException {
        createSudokuBoardsTable();
    }

}