package com.aldoivan.rubick_game;

import com.aldoivan.rubick_game.view.RubikFrame;
import com.jfoenix.assets.JFoenixResources;
import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class App extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        final String iconPath = Objects.requireNonNull(this.getClass().getResource("icon.png")).toExternalForm();
        final Image icon = new Image(iconPath,25d,25d,true,true);
        Font.loadFont(this.getClass().getResource("fonts/DIGITALDREAMFAT.ttf").toExternalForm(), 10);

        JFXDecorator decorator = new JFXDecorator(stage, new RubikFrame());
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(decorator,800,600);


        stage.initStyle(StageStyle.TRANSPARENT);
        decorator.setCustomMaximize(true);
        decorator.setGraphic(new ImageView());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        stage.getIcons().add(icon);
        stage.setTitle("Rubik game");
        decorator.setTitle("Rubik game");
        decorator.setGraphic(new ImageView(icon));

        decorator.heightProperty().addListener((observableValue, lastHeight, newHeight) ->
        {
            if(newHeight.intValue() == screenBounds.getHeight()) decorator.getStyleClass().add("jfx-decorator-square");
            else decorator.getStyleClass().remove("jfx-decorator-square");
        });

        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(JFoenixResources.load("css/jfoenix-fonts.css").toExternalForm(),
                JFoenixResources.load("css/jfoenix-design.css").toExternalForm(),
                Objects.requireNonNull(this.getClass().getResource("stylesheet/md-style.css")).toExternalForm(),
                Objects.requireNonNull(this.getClass().getResource("stylesheet/rubik-style.css")).toExternalForm());
    }

    public static void main(String[] args) {
        launch();
    }
}