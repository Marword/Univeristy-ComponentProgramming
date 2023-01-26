package com.sudokugame.view.view;

import dao.Dao;
import dao.SudokuBoardDaoFactory;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.exceptions.DaoException;
import model.exceptions.FileDaoException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import model.BacktrackingSudokuSolver;
import model.Level;
import model.SudokuBoard;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;


public class BoardController {

    private final Logger log = LogManager.getLogger(BoardController.class.getName());
    private final ResourceBundle lang = ResourceBundle.getBundle("Languages");

    @FXML
    private GridPane gridPane;
    @FXML
    private Button backButton;
    @FXML
    private Button checkButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button saveButton;

    private BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
    private SudokuBoard sudokuBoard = new SudokuBoard(solver);
    private SudokuBoard sudokuBoardCpy = new SudokuBoard(solver);
    private Dao<SudokuBoard> databaseSudokuBoardDao;
    private Level sudokuLevel = Level.EASY;

    private AlertBox alertBox = new AlertBox();

    @FXML
    private void initialize() {
        backButton.setText(lang.getString("back"));
        checkButton.setText(lang.getString("verifyBoard"));
        loadButton.setText(lang.getString("loadGame"));
        saveButton.setText(lang.getString("saveGame"));

        sudokuBoard.solveGame();
        sudokuLevel.chooseLevel(sudokuBoard, ApplicationController.getDifficulty());
        sudokuBoardCpy = sudokuBoard.clone();

        insertBoardToGrid();
    }

    private void insertBoardToGrid() {
        if (gridPane.getChildren() != null) {
            gridPane.getChildren().clear();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setMinSize(50, 50);
                textField.setFont(Font.font(15));
                if (sudokuBoard.get(i, j) != 0) {
                    if (sudokuBoardCpy.get(i, j) != 0) {
                        textField.setDisable(true);
                    }
                    textField.setText(String.valueOf(sudokuBoard.get(i, j)));
                }
                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (textField.getText().matches("[1-9]") || textField.getText().matches("")) {
                        updateBoard();
                    } else {
                        Platform.runLater(textField::clear);
                    }
                });
                gridPane.add(textField, i, j);

            }
        }
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

    @FXML
    public void loadGame(ActionEvent actionEvent) {
        TextInputDialog td = new TextInputDialog("text");
        td.setTitle("Loading");
        td.setHeaderText("Write name of the board to load");
        td.setContentText("Name:");
        Optional<String> result = td.showAndWait();
        result.ifPresent(name -> {
            SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
            try {
                sudokuBoard = factory.getDatabaseDao(name).read();
                String names = name + "emp";
                sudokuBoardCpy = factory.getDatabaseDao(names).read();
                insertBoardToGrid();
            } catch (FileDaoException e) {
                AlertBox.message("Err",
                        "Can't load from the database.", Alert.AlertType.WARNING);
                log.error("Loading from database failed.");
            }
            ;
        });
    }

    @FXML
    public void saveGame(ActionEvent actionEvent) {
        TextInputDialog td = new TextInputDialog("text");
        td.setTitle("Saving");
        td.setHeaderText("Write name under which board will be saved");
        td.setContentText("Name:");
        Optional<String> result = td.showAndWait();
        result.ifPresent(name -> {
            SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
            try {
                updateBoard();
                factory.getDatabaseDao(name).write(sudokuBoard);
                String names = name + "emp";
                factory.getDatabaseDao(names).write(sudokuBoardCpy);
            } catch (FileDaoException e) {
                AlertBox.message("Err",
                        "Couldn't save the board to the database.", Alert.AlertType.WARNING);
                log.error("Saving to database failed.");

            }
            ;
        });
    }

    @FXML
    public void onActionBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BoardApplication.class.getResource("application-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 421.0, 226.0);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Sudoku");
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void onActionCheck(ActionEvent actionEvent) {
        boolean isSolved = true;
        for (int i = 0; i < 9; i++) {
            if (!sudokuBoard.checkColumn(i) || !sudokuBoard.checkRow(i)) {
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
            AlertBox.message("Win",
                    "You won the game! Congratulations!", Alert.AlertType.WARNING);
        } else {
            AlertBox.message("Fail",
                    "You failed. Better try with lower levels, fellow player!", Alert.AlertType.WARNING);
        }
    }


}