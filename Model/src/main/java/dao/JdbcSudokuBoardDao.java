package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import model.BacktrackingSudokuSolver;
import model.SudokuBoard;
import model.exceptions.JdbcDatabaseException;


public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private static final String URL = "jdbc:postgresql://localhost/jdbcSudoku";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Marcin123";
    private final String boardName;

    public JdbcSudokuBoardDao(String boardName) {
        this.boardName = boardName;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connect() throws JdbcDatabaseException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new JdbcDatabaseException("connectionError", e);
        }
        return connection;
    }


    @Override
    public SudokuBoard read() throws JdbcDatabaseException {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        Connection connection = connect();
        int receivedData = 0;
        ResultSet resultSet;
        ResultSet resultSet2;
        String selectData = "select id_board, name from SudokuBoards where name=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectData)) {
            preparedStatement.setString(1, boardName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                receivedData = resultSet.getInt("id_board");
            }

        } catch (SQLException e) {
            throw new JdbcDatabaseException("Read board error", e);
        }

        String selectData2 = "select id_value, x, y, val, id_board "
                + "from SudokuValues where id_board=?";

        try (PreparedStatement preparedStatement2 = connection.prepareStatement(selectData2)) {
            preparedStatement2.setInt(1, receivedData);
            resultSet2 = preparedStatement2.executeQuery();

            for (int i = 0; i < 81; i++) {
                if (resultSet2.next()) {
                    sudokuBoard.set(resultSet2.getInt(2),
                            resultSet2.getInt(3), resultSet2.getInt(4));
                }
            }
        } catch (SQLException e) {
            throw new JdbcDatabaseException("Read values error", e);
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new JdbcDatabaseException("Read conn error", e);
        }

        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard sudokuBoard) throws JdbcDatabaseException {
        Connection connection = connect();

        String insertData = "insert into SudokuBoards(id_board, name) values (?,?)";
        int boardId = new Random().nextInt();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertData)) {
            preparedStatement.setInt(1, boardId);
            preparedStatement.setString(2, boardName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new JdbcDatabaseException("Update SudokuBoards", e);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String insertData2 = "insert into SudokuValues(id_value, x, y, "
                        + "val, id_board) values (?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement(insertData2)) {
                    preparedStatement.setInt(1, boardId * 100 + i * 9 + j);
                    preparedStatement.setInt(2, i);
                    preparedStatement.setInt(3, j);
                    preparedStatement.setInt(4, sudokuBoard.get(i, j));
                    preparedStatement.setInt(5, boardId);
                    preparedStatement.addBatch();
                    preparedStatement.executeBatch();
                } catch (SQLException e) {
                    throw new JdbcDatabaseException("insert", e);
                }
            }
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new JdbcDatabaseException("write", e);
        }
    }

    @Override
    public void close() throws Exception {

    }


    public static void createTables() throws JdbcDatabaseException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new JdbcDatabaseException("connectionError", e);
        }

        String createTable = "Create Table SudokuBoards(id_board int primary key, name text) ";
        String sudokuValues = "Create Table SudokuValues(id_value int primary key,"
                + " x int, y int, val int, id_board int,"
                + " CONSTRAINT board_FK FOREIGN KEY (id_board) "
                + "REFERENCES SudokuBoards(id_board)) ";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTable);
            statement.execute(sudokuValues);
        } catch (SQLException e) {
            throw new JdbcDatabaseException("Creating tables error", e);
        }
    }

    public static void deleteTables() throws JdbcDatabaseException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new JdbcDatabaseException("connectionError", e);
        }

        String deleteTable = "Drop Table SudokuBoards";
        String deleteValues = "Drop Table SudokuValues";

        try (Statement statement = connection.createStatement()) {
            statement.execute(deleteValues);
            statement.execute(deleteTable);
        } catch (SQLException e) {
            throw new JdbcDatabaseException("Dropping tables error", e);
        }
    }
}
