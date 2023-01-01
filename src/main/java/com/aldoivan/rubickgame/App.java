package com.aldoivan.rubickgame;

import com.aldoivan.rubickgame.controller.RubikController;
import com.aldoivan.rubickgame.exception.RubikException;
import com.aldoivan.rubickgame.model.Rubik;
import com.aldoivan.rubickgame.util.RubikUtil;
import com.aldoivan.rubickgame.view.Rubik2DPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application
{
    @Override
    public void start(Stage stage) throws RubikException
    {
        Rubik2DPane rubikPane = createRubikView();
        BorderPane parent = new BorderPane();
        HBox buttons = new HBox(5);

        Button btnU = new Button("U");
        Button btnUP = new Button("U'");
        Button btnD = new Button("D");
        Button btnDP = new Button("D'");
        Button btnR = new Button("R");
        Button btnRP = new Button("R'");
        Button btnL = new Button("L");
        Button btnLP = new Button("L'");
        Button btnF = new Button("F");
        Button btnFP = new Button("F'");
        Button btnB = new Button("B");
        Button btnBP = new Button("B'");

        buttons.getChildren().addAll(btnU, btnUP, btnD, btnDP, btnF);

        btnU.setOnAction(event -> { if(rubikPane.canPlayAnimation()) rubikPane.controller().rotateRow(0,true); });
        btnF.setOnAction(event -> { if(rubikPane.canPlayAnimation()) rubikPane.controller().rotateFace(0,false); });

        parent.setTop(buttons);
        parent.setCenter(rubikPane);
        parent.setPadding(new Insets(15));

        Scene scene = new Scene(parent,800,600);
        stage.setScene(scene);
        stage.show();
    }

    private static Rubik2DPane createRubikView() throws RubikException
    {
        Rubik rubik = new Rubik(3);
        RubikController controller = new RubikController(rubik);
        Rubik2DPane rubik2DPane = new Rubik2DPane(controller, RubikUtil.basicColors());
        return rubik2DPane;
    }

    public static void main(String[] args) {
        launch();
    }
}