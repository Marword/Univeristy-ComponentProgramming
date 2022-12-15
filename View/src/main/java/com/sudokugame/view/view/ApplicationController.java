package com.sudokugame.view.view;

import com.sudokugame.view.view.Exceptions.LevelNotSelectedException;
import com.sudokugame.view.view.Exceptions.StageReloadingException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class ApplicationController {

    @FXML
    private Label author1;
    @FXML
    private Label author2;
    @FXML
    private Label chooseDifficulty;
    @FXML
    private Label chooseLanguage;
    @FXML
    private Button buttonStartGame;
    @FXML
    private Button buttonConfirmLevel;
    @FXML
    private Button english;
    @FXML
    private ComboBox<String> comboBoxSystemDifficult;
    @FXML
    private Button polish;
    private AlertBox alertBox = new AlertBox();
    private static String level;
    private final ResourceBundle bundle = ResourceBundle.getBundle("languages");
    private final ResourceBundle authors = ResourceBundle.getBundle("com.sudokugame.view.view.Authors");


    public static String getLevel() {
        return level;
    }

    private static final Logger log = LogManager.getRootLogger();

    @FXML
    private void initialize() {
        String writing = authors.getString("name1") + " " + authors.getString("surname1");
        author1.setText(writing);
        writing = authors.getString("name2") + " " + authors.getString("surname2");
        author2.setText(writing);
        chooseDifficulty.setText(bundle.getString("chooseDifficulty"));
        chooseLanguage.setText(bundle.getString("chooseLanguage"));
        comboBoxSystemDifficult.setValue(bundle.getString("choice"));
        buttonStartGame.setText(bundle.getString("startButton"));
        buttonConfirmLevel.setText(bundle.getString("confirm"));
        english.setText(bundle.getString("lang_en"));
        polish.setText(bundle.getString("lang_pol"));
        comboBoxSystemDifficult.getItems().addAll(
                bundle.getString("lvlEasy"),
                bundle.getString("lvlMedium"),
                bundle.getString("lvlHard"),
                bundle.getString("lvlMaster")
        );
    }

    @FXML
    private void setEnglishLanguage(ActionEvent actionEvent) throws IOException {
        Locale locale = new Locale.Builder().setLanguage("en").setRegion("UK").build();
        Locale.setDefault(locale);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        reloadStage(window);
        log.info("Language set: English");
    }

    @FXML
    private void setPolishLanguage(ActionEvent actionEvent) throws IOException {
        Locale locale = new Locale.Builder().setLanguage("pl").setRegion("PL").build();
        Locale.setDefault(locale);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        reloadStage(window);
        log.info("Language set: Polish");
    }

    private void reloadStage(Stage window) throws IOException {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("application-view.fxml")));
            Scene scene = new Scene(root);
            window.setScene(scene);
            window.show();
        } catch (StageReloadingException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    public void onActionButtonStartGame(ActionEvent actionEvent) throws IOException {
        if (!(level == null)) {
            FXMLLoader fxmlLoader = new FXMLLoader(BoardApplication.class.getResource("board-view.fxml"));
            Scene game = new Scene(fxmlLoader.load(), 500, 659);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setTitle("Game");
            window.setScene(game);
            window.show();
        } else {
            AlertBox.messageBox("Warning",
                    "You need to choose level of difficulty, before we can proceed.", Alert.AlertType.WARNING);
            log.warn("Difficulty not set.");
        }
    }

    @FXML
    public void onActionButtonConfirm(ActionEvent actionEvent) {
        try {
            if (!Objects.equals(comboBoxSystemDifficult.getSelectionModel().getSelectedItem(), "Wybierz")) {
                level = comboBoxSystemDifficult.getSelectionModel().getSelectedItem();
                log.info("Difficulty set: " + level);
            }
        } catch (LevelNotSelectedException exception) {
            AlertBox.messageBox("Warning",
                    "Level of difficulty has not been chosen", Alert.AlertType.WARNING);
            log.warn("Difficulty not set.");
        }
    }

}