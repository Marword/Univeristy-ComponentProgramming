package com.sudokugame.view.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.BacktrackingSudokuSolver;
import model.Level;
import model.SudokuBoard;


public class BoardController {

    private SudokuBoard sudokuBoard;
    private Level sudokuLevel = Level.EASY;


    @FXML
    private AnchorPane boardController;

    @FXML
    private ToggleGroup difficulty;

    @FXML
    private RadioButton easy;

    @FXML
    private GridPane gridPane;

    @FXML
    private RadioButton hard;

    @FXML
    private RadioButton master;

    @FXML
    private RadioButton medium;

    public String getDifficulty() {
        RadioButton button = (RadioButton) difficulty.getSelectedToggle();
        return button.getId();
    }

    public void setDifficulty(){

    }

    @FXML
    public void initialize() {

        sudokuBoard =  new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();

        initSudoku();


    }
    public void  initSudoku(){

    }




/*    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }*/
}