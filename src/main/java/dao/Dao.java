package dao;

import java.io.IOException;

public interface Dao<T> extends AutoCloseable{

    //List<T> getAll();

    T read() throws IOException,ClassNotFoundException;

    void write(final T object) throws IOException;
}
