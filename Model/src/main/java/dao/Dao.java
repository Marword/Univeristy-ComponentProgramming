package dao;

import java.io.IOException;

public interface Dao<T> extends AutoCloseable {

    T read() throws IOException;

    void write(final T object) throws IOException;

    @Override
    void close();
}
