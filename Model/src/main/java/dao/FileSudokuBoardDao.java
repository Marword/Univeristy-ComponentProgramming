package dao;

import exceptions.FileDaoException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.SudokuBoard;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName + ".txt";
    }

    @Override
    public SudokuBoard read() throws IOException {
        SudokuBoard obj = null;
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream objIn = new ObjectInputStream(fileIn)) {
            obj = (SudokuBoard) objIn.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new FileDaoException("FailedToRead", e);
        }
        return obj;
    }

    @Override
    public void write(SudokuBoard object) throws IOException {
        try (ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objOut.writeObject(object);
        } catch (IOException e) {
            throw new FileDaoException("FailedToWrite", e);
        }
    }

    @Override
    public void close() {

    }
}
