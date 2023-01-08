package com.aldoivan.rubick_game.view;

import com.aldoivan.rubick_game.controller.RubikController;
import com.aldoivan.rubick_game.model.Rubik;
import com.aldoivan.rubick_game.util.RubikUtil;
import javafx.animation.ParallelTransition;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class Rubik2DPane extends GridPane
{
    private final ParallelTransition transition = new ParallelTransition();
    private boolean canPlayAnimation = true;
    private RubikController controller;

    public Rubik2DPane() { }

    public Rubik2DPane(RubikController controller, HashMap<Integer, Color> bindingColors) { this.initRubik(controller, bindingColors); }

    public void initRubik(RubikController controller, HashMap<Integer, Color> colors)
    {
        Rubik rubik = controller.rubik();

        RubikSideView top = new RubikSideView(rubik.top(), colors);
        RubikSideView bot = new RubikSideView(rubik.bot(), colors);
        RubikSideView left = new RubikSideView(rubik.left(), colors);
        RubikSideView right = new RubikSideView(rubik.right(), colors);
        RubikSideView front = new RubikSideView(rubik.front(), colors);
        RubikSideView back = new RubikSideView(rubik.back(), colors);

        this.add(top,1,0);
        this.getColumnConstraints().add(RubikUtil.columnConstraint());
        this.getRowConstraints().add(RubikUtil.rowConstraint());

        this.add(left,0,1);
        this.add(front,1,1);
        this.add(right,2,1);
        this.getColumnConstraints().add(RubikUtil.columnConstraint());

        this.add(back,3,1);
        this.getColumnConstraints().add(RubikUtil.columnConstraint());
        this.getRowConstraints().add(RubikUtil.rowConstraint());

        this.add(bot,1,2);
        this.getColumnConstraints().add(RubikUtil.columnConstraint());
        this.getRowConstraints().add(RubikUtil.rowConstraint());

        this.transition.setOnFinished(event -> this.canPlayAnimation = true);

        controller.onChange(newRubik ->
        {
            this.canPlayAnimation = false;
            this.transition.getChildren().clear();
            this.transition.getChildren().addAll(top.setValues(newRubik.top()));
            this.transition.getChildren().addAll(bot.setValues(newRubik.bot()));
            this.transition.getChildren().addAll(left.setValues(newRubik.left()));
            this.transition.getChildren().addAll(right.setValues(newRubik.right()));
            this.transition.getChildren().addAll(front.setValues(newRubik.front()));
            this.transition.getChildren().addAll(back.setValues(newRubik.back()));
            this.transition.play();
        });

        this.setPadding(new Insets(15));
        this.controller = controller;
    }

    public boolean canPlayAnimation() { return this.canPlayAnimation; }

    public RubikController controller() { return this.controller; }
}
