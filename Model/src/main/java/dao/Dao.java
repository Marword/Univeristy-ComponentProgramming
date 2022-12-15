package dao;

import model.exceptions.FileDaoException;

public interface Dao<T> extends AutoCloseable {

    T read() throws FileDaoException;

    void write(final T object) throws FileDaoException;

}
