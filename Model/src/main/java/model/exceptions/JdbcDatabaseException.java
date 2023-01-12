package model.exceptions;

public class JdbcDatabaseException extends FileDaoException {

    public JdbcDatabaseException(String msg, Throwable cause) {
        super(msg, cause);
    }
}