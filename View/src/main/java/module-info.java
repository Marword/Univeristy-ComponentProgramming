module com.sudokugame.view.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires model;


    opens com.sudokugame.view.view to javafx.fxml;
    exports com.sudokugame.view.view;
}