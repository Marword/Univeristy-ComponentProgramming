package dao;

import model.SudokuBoard;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSudokuBoardDao implements Dao<SudokuBoard>{

    private String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName + ".txt";
    }

    //deserialization
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
    //serialization
    @Override
    public void write(SudokuBoard object) throws IOException {
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
