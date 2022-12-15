package model.exceptions;

import java.io.IOException;

public class DaoException extends IOException {
    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
