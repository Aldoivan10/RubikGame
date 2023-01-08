package com.aldoivan.rubick_game.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Test extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        Scene scene = new Scene(new CubeTest(), 800, 600);
        stage.setTitle("Rubik");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}