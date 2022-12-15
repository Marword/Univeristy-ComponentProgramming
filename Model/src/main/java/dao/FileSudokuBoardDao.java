package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.SudokuBoard;
import model.exceptions.FileDaoException;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName + ".txt";
    }

    @Override
    public SudokuBoard read() throws FileDaoException {
        SudokuBoard obj = null;
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream objIn = new ObjectInputStream(fileIn)) {
            obj = (SudokuBoard) objIn.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new FileDaoException("FailedToWrite",e);
        }
        return obj;
    }

    @Override
    public void write(SudokuBoard object) throws FileDaoException {
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
