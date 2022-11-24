package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.SudokuBoard;

public class FileSudokuBoardDao implements Dao<SudokuBoard>{

    private String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName + ".txt";
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard obj = null;
        try (FileInputStream fileOut = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileOut)) {
            obj = (SudokuBoard) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    @Override
    public void write(SudokuBoard object) {
        try (ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(fileName))) {
            obj.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {

    }
}
