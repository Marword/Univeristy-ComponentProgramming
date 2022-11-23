module com.sudokugame.view.view {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.sudokugame.view.view to javafx.fxml;
    exports com.sudokugame.view.view;
}