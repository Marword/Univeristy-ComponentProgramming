package com.sudokugame.view.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.BacktrackingSudokuSolver;
import model.Level;
import model.SudokuBoard;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class BoardController {

    private SudokuBoard sudokuBoard;
    private Level sudokuLevel = Level.EASY;
    @FXML
    GridPane gridPane;


    @FXML
    public void setDifficulty(){

    }

    @FXML
    public void initialize() {

        sudokuBoard =  new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        initSudokuCells(sudokuLevel);

    }



    public void initSudokuCells(Level sudokuBoardLevel) {


    }
/*    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }*/
}