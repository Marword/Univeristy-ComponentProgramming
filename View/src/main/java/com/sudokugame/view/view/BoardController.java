package com.sudokugame.view.view;

import dao.Dao;
import dao.SudokuBoardDaoFactory;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.exceptions.DaoException;
import model.exceptions.FileDaoException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import model.BacktrackingSudokuSolver;
import model.Level;
import model.SudokuBoard;
import org.apache.commons.lang3.math.NumberUtils;


import java.util.ResourceBundle;


public class BoardController {

    private final Logger log = LogManager.getLogger(BoardController.class.getName());
    private Level sudokuLevel = Level.EASY;
    private final ResourceBundle bundle = ResourceBundle.getBundle("Languages");

    @FXML
    private GridPane gridPane;

    @FXML
    private ToggleGroup difficulty;

    @FXML
    private RadioButton easy;

    @FXML
    private RadioButton hard;

    @FXML
    private RadioButton master;

    @FXML
    private RadioButton medium;

    @FXML
    private Button verifyButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button saveButton;

    private BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();

    private AlertBox alertBox = new AlertBox();
    private SudokuBoard sudokuBoard = new SudokuBoard(solver);
    private SudokuBoard sudokuBoardCopy = new SudokuBoard(solver);

    public String getDifficulty() {
        RadioButton button = (RadioButton) difficulty.getSelectedToggle();
        return button.getId();
    }

    public void setDifficulty(ActionEvent event) {
        if (easy.isSelected()) {
            sudokuLevel = Level.EASY;
//            zmienić stringi na pliki zasobow potem
            log.info("easy level chosen");
        } else if (medium.isSelected()) {
            sudokuLevel = Level.MEDIUM;
            log.info("medium level chosen");
        } else if (hard.isSelected()) {
            sudokuLevel = Level.HARD;
            log.info("hard level chosen");
        } else {
            sudokuLevel = Level.MASTER;
            log.info("master level chosen");
        }
    }

    @FXML
    private void initialize() {
        verifyButton.setText(bundle.getString("verify"));
        loadButton.setText(bundle.getString("load"));
        saveButton.setText(bundle.getString("save"));
        sudokuBoard.solveGame();
        sudokuLevel.removeValues(sudokuBoard);
        sudokuBoardCopy = sudokuBoard.clone();
        fillGrid();
    }

    @FXML
    private void updateBoard() {
        ObservableList<Node> gridList = gridPane.getChildren();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = (TextField) gridList.get(i + 9 * j);
                if (NumberUtils.isParsable(textField.getText())) {
                    int valueToSet = Integer.parseInt(textField.getText());
                    sudokuBoard.set(j, i, valueToSet);
                } else {
                    sudokuBoard.set(j, i, 0);
                }
            }
        }
    }

    private void fillGrid() {
        if (gridPane.getChildren() != null) {
            gridPane.getChildren().clear();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setMinSize(50, 50);
                textField.setFont(Font.font(11));
                if (sudokuBoard.get(i, j) != 0) {
                    if (sudokuBoardCopy.get(i, j) != 0) {
                        textField.setDisable(true);
                    }
                    textField.setText(String.valueOf(sudokuBoard.get(i, j)));
                }
                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (textField.getText().matches("[1-9]") || textField.getText().matches("")) {
                        updateBoard();
                    } else {
                        Platform.runLater(textField::clear);
//                        popOutWindow.messageBox("Warning",
//                                "The inserted value must be an integer in range: (1-9)", Alert.AlertType.WARNING);
                    }
                });
                gridPane.add(textField, i, j);

            }
        }
    }


    @FXML
    private void saveGame(ActionEvent actionEvent) throws DaoException {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> file;
        file = factory.getFileDao("save");
        updateBoard();
        try {
            file.write(sudokuBoard);
            log.info("Game saved.");
        } catch (FileDaoException e) {
            AlertBox.messageBox("Error",
                    "Couldn't save the game progress.", Alert.AlertType.WARNING);
            log.error("Saving failed.");
        }
    }


    @FXML
    private void loadGame(ActionEvent actionEvent) throws DaoException {
        try {
            SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
            Dao<SudokuBoard> file;
            file = factory.getFileDao("save");
            sudokuBoard = file.read();
            fillGrid();
            log.info("Game loaded.");
        } catch (FileDaoException e) {
            AlertBox.messageBox("Error",
                    "Couldn't load the game progress.", Alert.AlertType.WARNING);
            log.error("Loading failed.");
        }

    }

    @FXML
    public void onActionButtonCheck(ActionEvent actionEvent) {
        boolean isSolved = true;
        for (int i = 0; i < 9; i++) {
            if (!sudokuBoard.checkColumn(i) || !sudokuBoard.checkRow(i) ) {
                isSolved = false;
                break;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!sudokuBoard.checkBox(i, j)) {
                    isSolved = false;
                    break;
                }
            }
        }
        if (isSolved) {
            AlertBox.messageBox("Win",
                    "You won.", Alert.AlertType.WARNING);
        } else {
            AlertBox.messageBox("Fail",
                    "It's not the right solution.", Alert.AlertType.WARNING);
        }
    }


}