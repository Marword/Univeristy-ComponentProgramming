module com.sudokugame.view.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires model;
    requires log4j;
    requires org.apache.commons.lang3;


    opens com.sudokugame.view.view to javafx.fxml;
    exports com.sudokugame.view.view;
}