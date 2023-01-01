package com.aldoivan.rubickgame.test;

import com.aldoivan.rubickgame.model.Cube;
import com.aldoivan.rubickgame.view.Canvas3D;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.transform.Translate;

public class CubeTest extends BorderPane
{
    public CubeTest()
    {
        final Canvas3D canvas3D = new Canvas3D();
        final HBox toolBar = new HBox(10);
        final Cube center = new Cube();
        final Cube left = new Cube();
        final Button btnT = new Button("U"), btnB = new Button("D"),
                btnL = new Button("L"),btnR = new Button("R");

        canvas3D.addChild(center.obj());
        canvas3D.addChild(left.obj());
        toolBar.setAlignment(Pos.BASELINE_CENTER);
        toolBar.getChildren().addAll(btnT,btnB,btnL,btnR);

        left.obj().getTransforms().add(new Translate(-200,0,0));



        this.setCenter(canvas3D);
        this.setTop(toolBar);
    }
}
