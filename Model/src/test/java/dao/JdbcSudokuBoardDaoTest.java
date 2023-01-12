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

import static dao.JdbcSudokuBoardDao.createSudokuBoardsTable;
import static org.junit.jupiter.api.Assertions.*;

class JdbcSudokuBoardDaoTest {

    @Test
    void wrongInputTest() throws DaoException {
        JdbcDatabaseException thrown = Assertions.assertThrows(JdbcDatabaseException.class, () -> {
            SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
            ;
            if (SystemUtils.IS_OS_WINDOWS) {
                factory.getDatabaseDao("?").read();
            } else if (SystemUtils.IS_OS_LINUX) {
                factory.getDatabaseDao("/").read();
            } else {
                factory.getDatabaseDao("?").read();
            }
        });
    }

    @Test
    void databaseAlreadyExistsWriteScenario() throws DaoException {
        JdbcDatabaseException thrown = Assertions.assertThrows(JdbcDatabaseException.class, () -> {
            SudokuBoard board2 = new SudokuBoard(new BacktrackingSudokuSolver());
            board2.solveGame();
            SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String name = dtf.format(now).toString();
            name += "z";
            factory.getDatabaseDao(name).write(board2);
            factory.getDatabaseDao(name).write(board2);
        });
    }

    @Test
    void databaseDoesNotExistsReadScenario() throws DaoException {
        JdbcDatabaseException thrown = Assertions.assertThrows(JdbcDatabaseException.class, () -> {
            SudokuBoard board1 = new SudokuBoard(new BacktrackingSudokuSolver());
            SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String name = dtf.format(now).toString();
            name += "zz";
            board1 = factory.getDatabaseDao(name).read();
        });
    }
}