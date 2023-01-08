package com.aldoivan.rubick_game.view;

import javafx.animation.FillTransition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class RubikSlot extends Pane
{

    private final Rectangle sticker = new Rectangle();

    public RubikSlot() { this.initSlot(Color.WHITE); }

    public RubikSlot(Color color) { this.initSlot(color); }

    private void initSlot(Color color)
    {
        this.sticker.setFill(color);
        this.sticker.setArcWidth(15);
        this.sticker.setArcHeight(15);
        this.sticker.setStrokeWidth(5);
        this.sticker.setStroke(Color.web("#333333"));

        this.sticker.widthProperty().bind(this.widthProperty());
        this.sticker.heightProperty().bind(this.heightProperty());

        this.getChildren().add(this.sticker);
    }

    public void playFillAnimation(Color to)
    {
        final FillTransition transition = this.getFillAnimation(to);
        transition.play();
    }

    public FillTransition getFillAnimation(Color to)
    {
        FillTransition transition = new FillTransition();
        transition.setDuration(Duration.seconds(0.25));
        transition.setFromValue((Color) this.sticker.getFill());
        transition.setToValue(to);
        transition.setShape(this.sticker);
        return transition;
    }

    public Color getColor() { return (Color) this.sticker.getFill(); }
}
