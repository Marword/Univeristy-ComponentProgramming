package com.sudokugame.view.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.io.IOException;

public class BoardApplication extends Application {
    private static final Logger log = LogManager.getRootLogger();

    @Override
    public void start(Stage stage) throws IOException {
        log.info("Application is starting...");
        FXMLLoader fxmlLoader = new FXMLLoader(BoardApplication.class.getResource("application-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 421.0, 226.0);
        stage.setTitle("Sudoku Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}