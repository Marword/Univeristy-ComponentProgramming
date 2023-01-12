package dao;

import model.BacktrackingSudokuSolver;
import model.SudokuBoard;
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

    private Connection connect() throws JdbcDatabaseException{
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            throw new JdbcDatabaseException("connectionError", e);
        }
        return connection;
    }


    @Override
    public SudokuBoard read() throws JdbcDatabaseException {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        Connection connection = connect();
        String receivedData;
        ResultSet resultSet;
        String select = "select tableName, fields from sudokuboards_table where tableName=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setString(1,boardName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                receivedData = resultSet.getString(2);
            } else {
                receivedData = null;
            }
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    board.set(i, j, Character.getNumericValue(receivedData.charAt(i * 9 + j)));
                }
            }
        } catch (SQLException | NullPointerException e) {
            throw new JdbcDatabaseException("Read error", e);
        }
        return board;
    }

    @Override
    public void write(SudokuBoard board) throws JdbcDatabaseException {
        Connection connection = connect();
        StringBuilder boardValues = new StringBuilder("");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boardValues.append(String.valueOf(board.get(i, j)));
            }
        }
        String insert = "INSERT INTO `sudokuproject`.`"
                + "sudokuboards_table`(`tableName`,`fields`) VALUES (?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1,boardName);
            preparedStatement.setString(2,boardValues.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            throw new JdbcDatabaseException("Write error", e);
        }
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
