module com.aldoivan.rubickgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.fxyz3d.importers;
    requires com.jfoenix;

    opens com.aldoivan.rubickgame to javafx.fxml;
    exports com.aldoivan.rubickgame;
    exports com.aldoivan.rubickgame.test;
}