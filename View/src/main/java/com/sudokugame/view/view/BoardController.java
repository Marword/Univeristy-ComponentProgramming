package com.sudokugame.view.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.BacktrackingSudokuSolver;
import model.Level;
import model.SudokuBoard;


public class BoardController {

    private SudokuBoard sudokuBoard;
    private Level sudokuLevel =Level.EASY;


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

    public void setDifficulty(ActionEvent event){
        if (easy.isSelected()) {
            sudokuLevel = Level.EASY;
        }
        else if (medium.isSelected()) {
            sudokuLevel = Level.MEDIUM;
        }
        else if (hard.isSelected()) {
            sudokuLevel = Level.HARD;
        }
        else {
            sudokuLevel = Level.MASTER;
        }
    }

    @FXML
    public void initialize() {

        sudokuBoard =  new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        sudokuLevel.removeValues(sudokuBoard);
        initSudoku();


    }
    public void  initSudoku(){
        for (int col = 0; col < sudokuBoard.size; col++) {
            for (int row = 0; row < sudokuBoard.size; row++) {
                TextField textField;
                textField = new TextField(String.valueOf(sudokuBoard.get(col,row)));
                textField.setAlignment(Pos.CENTER);
                textField.setFont(new Font("Calibri", 35));
                textField.setEditable(true);
                gridPane.add(textField, col,row);
                }
            }
    }




/*    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }*/
}