package dao;

import model.SudokuBoard;
import model.exceptions.FileDaoException;
import model.exceptions.JdbcDaoConnectionException;
import model.exceptions.JdbcDatabaseException;

import java.sql.*;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable{

    private static final String URL = "jdbc:postgresql://localhost/jdbcSudoku";
    private static final String USER = "postgres";
    private static final String PASSWORD = "kurak123";
    private final String boardName;

    public JdbcSudokuBoardDao(String boardName) {
        this.boardName = boardName;
    }

    private Connection connect() throws JdbcDaoConnectionException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            throw new JdbcDaoConnectionException("connectionError", e);
        }
        return connection;
    }


    @Override
    public SudokuBoard read() throws FileDaoException {
        return null;
    }

    @Override
    public void write(SudokuBoard object) throws FileDaoException {

    }

    @Override
    public void close() throws Exception {

    }

    static void createSudokuBoardsTable() throws JdbcDatabaseException {
        String sudokuBoards = "Create Table SudokuBoards(id_board int primary key, name text) ";
        try (var con = DriverManager.getConnection(URL,USER,PASSWORD)) {
            Statement statement = con.createStatement();
            statement.execute(sudokuBoards);
        } catch (SQLException e) {
            throw new JdbcDatabaseException("Cannot create table error", e);
        }
    }

    private static void createSudokuValuesTable() throws JdbcDatabaseException {
        String sudokuValues = "Create Table SudokuValues(id_value int primary key, value int, id_board int, CONSTRAINT board_FK FOREIGN KEY (id_board) REFERENCES SudokuBoards(id_board)) ";
        try (var con = DriverManager.getConnection(URL,USER,PASSWORD)) {
            Statement statement = con.createStatement();
            statement.execute(sudokuValues);
        } catch (SQLException e) {
            throw new JdbcDatabaseException("Cannot create table error", e);
        }
    }

//    public ResultSet executeSelect(){
//
//    }

}
