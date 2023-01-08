package com.aldoivan.rubick_game.view;

import com.aldoivan.rubick_game.model.Clock;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

import java.util.Timer;

public class ClockLabel extends Label
{
    private final Timer timer = new Timer(true);
    private final Clock clock = new Clock();

    public ClockLabel()
    {
        this.getStyleClass().add("lbl-timer");
        this.setPadding(new Insets(10));
        this.textProperty().bind(this.clock.clock());
    }

    public void init() { this.timer.schedule(this.clock,0,1000); }

    public void stop()
    {
        this.timer.cancel();
        this.clock.reset();
    }

}
