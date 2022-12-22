package model.exceptions;

public class JdbcDaoConnectionException extends DaoException {

    public JdbcDaoConnectionException(String msg, Throwable cause) {
        super(msg, cause);
    }
}