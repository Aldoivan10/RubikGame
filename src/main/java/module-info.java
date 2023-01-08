module com.aldoivan.rubickgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.fxyz3d.importers;
    requires com.jfoenix;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.material2;
    requires org.kordamp.ikonli.javafx;

    opens com.aldoivan.rubick_game to javafx.fxml;
    exports com.aldoivan.rubick_game;
    exports com.aldoivan.rubick_game.test;
}