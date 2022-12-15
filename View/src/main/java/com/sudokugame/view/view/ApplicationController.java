package com.sudokugame.view.view;

import com.sudokugame.view.view.Exceptions.MissingLevelException;
import com.sudokugame.view.view.Exceptions.RefreshException;
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

    private static final Logger log = LogManager.getRootLogger();
    @FXML
    private Button buttonStart;
    @FXML
    private Label author1;
    @FXML
    private Label author2;
    @FXML
    private Label chooseDiff;
    @FXML
    private ComboBox<String> systemDiff;
    @FXML
    private Button buttonConfirm;
    @FXML
    private Label chooseLang;
    @FXML
    private Button english;
    @FXML
    private Button polish;
    private static String difficulty;
    private final ResourceBundle langs = ResourceBundle.getBundle("languages");
    private final ResourceBundle authors = ResourceBundle.getBundle("com.sudokugame.view.view.Authors");


    @FXML
    private void initialize() {
        String text = authors.getString("name1") + " " + authors.getString("surname1");
        author1.setText(text);
        text = authors.getString("name2") + " " + authors.getString("surname2");
        author2.setText(text);

        chooseLang.setText(langs.getString("chooseLang"));
        english.setText(langs.getString("lang_en"));
        polish.setText(langs.getString("lang_pol"));

        chooseDiff.setText(langs.getString("chooseDiff"));
        systemDiff.setValue(langs.getString("diffBox"));
        systemDiff.getItems().addAll(langs.getString("easy"), langs.getString("medium"), langs.getString("hard"), langs.getString("master"));

        buttonConfirm.setText(langs.getString("confirmLevel"));

        buttonStart.setText(langs.getString("startButton"));
    }

    @FXML
    private void setEnglish(ActionEvent actionEvent) throws IOException {
        Locale locale = new Locale.Builder().setLanguage("en").setRegion("UK").build();
        Locale.setDefault(locale);
        Stage openWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        refreshStage(openWindow);
        log.info("Language: English");
    }

    @FXML
    private void setPolish(ActionEvent actionEvent) throws IOException {
        Locale locale = new Locale.Builder().setLanguage("pl").setRegion("PL").build();
        Locale.setDefault(locale);
        Stage openWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        refreshStage(openWindow);
        log.info("Language: Polish");
    }

    @FXML
    public void onActionConfirm(ActionEvent actionEvent) {
        try {
            if (!Objects.equals(systemDiff.getSelectionModel().getSelectedItem(), "Wybierz")) {
                difficulty = systemDiff.getSelectionModel().getSelectedItem();
                log.info("Difficulty set: " + difficulty);
            }
        } catch (MissingLevelException exception) {
            AlertBox.message("Warning", "Level of difficulty has not been chosen", Alert.AlertType.WARNING);
            log.warn("Difficulty not set.");
        }
    }

    @FXML
    public void onActionStart(ActionEvent actionEvent) throws IOException {
        if (!(difficulty == null)) {
            FXMLLoader fxmlLoader = new FXMLLoader(BoardApplication.class.getResource("board-view.fxml"));
            Scene game = new Scene(fxmlLoader.load(), 500, 659);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setTitle("Game");
            window.setScene(game);
            window.show();
        } else {
            AlertBox.message("Warning", "You need to choose level of difficulty, before we can proceed.", Alert.AlertType.WARNING);
            log.warn("Difficulty not set.");
        }
    }

    private void refreshStage(Stage window) throws IOException {
        Parent refresh;
        try {
            refresh = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("application-view.fxml")));
            Scene scene = new Scene(refresh);
            window.setScene(scene);
            window.show();
        } catch (RefreshException exception) {
            exception.printStackTrace();
        }
    }

    public static String getDifficulty() {
        return difficulty;
    }

}