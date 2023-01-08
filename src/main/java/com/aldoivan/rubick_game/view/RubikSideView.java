package com.aldoivan.rubick_game.view;

import com.aldoivan.rubick_game.model.RubikSide;
import com.aldoivan.rubick_game.util.RubikUtil;
import javafx.animation.FillTransition;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.HashMap;

public class RubikSideView extends GridPane
{
    private HashMap<Integer, Color> colors;

    public RubikSideView() { }

    public RubikSideView(RubikSide rubikSide, HashMap<Integer, Color> colors) { this.initSide(rubikSide,colors); }

    public void initSide(RubikSide rubikSide, HashMap<Integer, Color> colors)
    {
        int[][] values = rubikSide.values();

        for(int row = 0;row < 3;row++)
        {
            for(int col = 0;col < 3;col++)
            {
                RubikSlot slot = new RubikSlot(colors.get(values[row][col]));
                this.add(slot, col, row);
            }
            this.getColumnConstraints().add(RubikUtil.columnConstraint());
            this.getRowConstraints().add(RubikUtil.rowConstraint());
        }

        this.colors = colors;
    }

    public ArrayList<FillTransition> setValues(RubikSide rubikSide) { return this.changeValues(rubikSide.values()); }

    public ArrayList<FillTransition> setValues(int[][] values) { return this.changeValues(values); }

    private ArrayList<FillTransition> changeValues(int[][] values)
    {
        ArrayList<FillTransition> transitions = new ArrayList<>();
        RubikSlot rubikSlot;
        int numSlot = 0;
        Color color;

        for(int[] row:values)
        {
            for(int col:row)
            {
                rubikSlot = (RubikSlot) this.getChildren().get(numSlot++);
                color = this.colors.get(col);
                if(!rubikSlot.equals(col)) transitions.add(rubikSlot.getFillAnimation(color));
            }
        }

        return transitions;
    }

    public String toString()
    {
        return "";
    }
}
