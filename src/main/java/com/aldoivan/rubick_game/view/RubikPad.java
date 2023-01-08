package com.aldoivan.rubick_game.view;

import com.jfoenix.controls.*;
import com.jfoenix.effects.JFXDepthManager;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material2.Material2RoundAL;
import org.kordamp.ikonli.material2.Material2RoundMZ;

public class RubikPad extends AnchorPane
{
    public RubikPad()
    {
        this.initPad();
        this.initGameControl();
    }

    private void initGameControl()
    {
        ClockLabel clock = new ClockLabel();
        GridPane controlContainer = new GridPane();
        controlContainer.getStyleClass().add("pane");
        controlContainer.setPadding(new Insets(2));
        controlContainer.setHgap(2);
        controlContainer.setVgap(2);

        JFXButton btnPlay = new JFXButton();
        JFXButton btnReset = new JFXButton();
        JFXButton btnScramble = new JFXButton();
        JFXToggleNode btnTimer = new JFXToggleNode();

        btnPlay.getStyleClass().add("btn-play");
        btnPlay.setTooltip(new JFXTooltip("Jugar"));
        btnPlay.setGraphic(FontIcon.of(Material2RoundMZ.PLAY_ARROW,18));

        btnReset.getStyleClass().add("btn-reset");
        btnReset.setTooltip(new JFXTooltip("Resetear"));
        btnReset.setGraphic(FontIcon.of(Material2RoundAL.AUTORENEW,18));

        btnScramble.getStyleClass().add("btn-scramble");
        btnScramble.setTooltip(new JFXTooltip("Revolver"));
        btnScramble.setGraphic(FontIcon.of(Material2RoundMZ.SHUFFLE,18));

        btnTimer.getStyleClass().add("btn-timer");
        btnTimer.setTooltip(new JFXTooltip("Temporizador"));
        btnTimer.setGraphic(FontIcon.of(Material2RoundMZ.TIMER_OFF,20));
        btnTimer.setOnAction(event ->
        {
            if(btnTimer.isSelected())
            {
                btnTimer.setGraphic(FontIcon.of(Material2RoundMZ.TIMER,20));
                controlContainer.add(clock, 2,0,1,2);
            }
            else
            {
                btnTimer.setGraphic(FontIcon.of(Material2RoundMZ.TIMER_OFF,20));
                controlContainer.getChildren().remove(clock);
            }
        });

        btnPlay.setOnAction(event -> clock.init());
        btnReset.setOnAction(event -> clock.stop());

        controlContainer.add(btnPlay,0,0);
        controlContainer.add(btnScramble,1,0);
        controlContainer.add(btnReset,0,1);
        controlContainer.add(btnTimer,1,1);

        AnchorPane.setRightAnchor(controlContainer, 0d);
        JFXDepthManager.setDepth(controlContainer,1);
        this.getChildren().add(controlContainer);
    }

    private void initPad()
    {
        GridPane padContainer = new GridPane();
        padContainer.setPadding(new Insets(2));
        padContainer.getStyleClass().add("pane");
        padContainer.setHgap(2);
        padContainer.setVgap(2);

        JFXButton btnU = new JFXButton(" U ");
        JFXButton btnD = new JFXButton(" D ");
        JFXButton btnR = new JFXButton(" R ");
        JFXButton btnL = new JFXButton(" L ");
        JFXButton btnF = new JFXButton(" F ");
        JFXButton btnB = new JFXButton(" B ");
        JFXButton btnUP = new JFXButton(" U'");
        JFXButton btnDP = new JFXButton(" D'");
        JFXButton btnRP = new JFXButton(" R'");
        JFXButton btnLP = new JFXButton(" L'");
        JFXButton btnFP = new JFXButton(" F'");
        JFXButton btnBP = new JFXButton(" B'");

        padContainer.add(btnU,0,0);
        padContainer.add(btnD,1,0);
        padContainer.add(btnR,2,0);
        padContainer.add(btnL,3,0);
        padContainer.add(btnF,4,0);
        padContainer.add(btnB,5,0);
        padContainer.add(btnUP,0,1);
        padContainer.add(btnDP,1,1);
        padContainer.add(btnRP,2,1);
        padContainer.add(btnLP,3,1);
        padContainer.add(btnFP,4,1);
        padContainer.add(btnBP,5,1);

        JFXDepthManager.setDepth(padContainer,1);
        AnchorPane.setLeftAnchor(padContainer, 0d);

        this.addStyle(btnU,btnD,btnR,btnL,btnF,btnB,btnUP,btnDP,btnRP,btnLP,btnFP,btnBP);
        this.getChildren().add(padContainer);
    }

    private void addStyle(JFXButton... buttons) { for(JFXButton button:buttons) { button.getStyleClass().add("button-pad"); } }
}
