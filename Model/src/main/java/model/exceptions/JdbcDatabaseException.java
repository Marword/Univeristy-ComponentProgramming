package model.exceptions;

public class JdbcDatabaseException extends DaoException {

    public JdbcDatabaseException(String msg, Throwable cause) {
        super(msg, cause);
    }
}