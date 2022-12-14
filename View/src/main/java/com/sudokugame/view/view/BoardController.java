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

import java.util.ArrayList;
import java.util.List;


public class BoardController {

    private SudokuBoard sudokuBoard;
    private Level sudokuLevel =Level.EASY;

    private TextField textFieldTmp;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

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
    public void initButtonsOnEvent() {

    List<Button> buttonList = new ArrayList<>();
    buttonList.add(button1);
    buttonList.add(button2);
    buttonList.add(button3);
    buttonList.add(button4);
    buttonList.add(button5);
    buttonList.add(button6);
    buttonList.add(button7);
    buttonList.add(button8);
    buttonList.add(button9);

    for (Button butt : buttonList) {
        butt.setOnAction(actionEvent -> {
            textFieldTmp.setText(butt.getText());
        });
        }
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
    private TextField createTextField(int row, int col){
        TextField textField;
        textField = new TextField(String.valueOf(sudokuBoard.get(col,row)));
        textField.setAlignment(Pos.CENTER);
        textField.setFont(new Font("Calibri", 35));
        textField.setEditable(true);


        textField.setOnMouseClicked(e -> {
            textField.setText(textFieldTmp.getText());
//            exception jezeli nie zostal wybrany wczesniej button z wartoscia
//            try {
//
//            } catch () {
//
//            }
        });

        return textField;
    }

    public void  initSudoku(){
        for (int col = 0; col < sudokuBoard.size; col++) {
            for (int row = 0; row < sudokuBoard.size; row++) {
//                TextField textField;
//                textField = new TextField(String.valueOf(sudokuBoard.get(col,row)));
//                textField.setAlignment(Pos.CENTER);
//                textField.setFont(new Font("Calibri", 35));
//                textField.setEditable(true);
                gridPane.add(createTextField(row,col), col,row);
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