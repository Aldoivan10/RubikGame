package com.aldoivan.rubick_game.model;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Clock extends java.util.TimerTask
{
    private final StringBinding timeBinding;
    private final SimpleStringProperty time = new SimpleStringProperty("00:00:00");
    private final SimpleIntegerProperty hours = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty minutes = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty seconds = new SimpleIntegerProperty(0);

    public Clock()
    {
        this.timeBinding = Bindings.createStringBinding(() -> String.format("%s:%s:%s",
                hours.get() < 10 ? "0" + hours.get() : hours.get(),
                minutes.get() < 10 ? "0" + minutes.get() : minutes.get(),
                seconds.get() < 10 ? "0" + seconds.get() : seconds.get()), this.hours, this.seconds, this.minutes);
        this.time.bind(this.timeBinding);
    }

    public SimpleStringProperty clock() { return this.time; }

    public void reset()
    {
        this.hours.set(0);
        this.minutes.set(0);
        this.seconds.set(0);
    }

    @Override
    public void run()
    {
        Platform.runLater(() ->
        {
            int h = hours.get();
            int m = minutes.get();
            int s = seconds.get();

            if(s == 59)
            {
                seconds.set(0);
                if(m == 59)
                {
                    minutes.set(0);
                    hours.set(h + 1);
                    if(h == 59)
                    {
                        this.cancel();
                        time.set("- Límite de tiempo -");
                    }
                }
                else minutes.set(m + 1);
            }
            else seconds.set(s + 1);
        });
    }
}
