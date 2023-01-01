package com.aldoivan.rubickgame.util;

import com.aldoivan.rubickgame.model.RubikSide;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class RubikUtil
{
    public static HashMap<Integer, Color> basicColors()
    {
        HashMap<Integer, Color> colors = new HashMap<>();
        colors.put(0, Color.WHITE);
        colors.put(1, Color.web("#4CAF50"));
        colors.put(2, Color.web("#F44336"));
        colors.put(3, Color.web("#3F51B5"));
        colors.put(4, Color.web("#FF9800"));
        colors.put(5, Color.web("#FFEB3B"));
        return colors;
    }

    public static ColumnConstraints columnConstraint()
    {
        ColumnConstraints constraint = new ColumnConstraints();
        constraint.setHgrow(Priority.SOMETIMES);
        constraint.setFillWidth(true);
        return constraint;
    }

    public static RowConstraints rowConstraint()
    {
        RowConstraints constraint = new RowConstraints();
        constraint.setVgrow(Priority.SOMETIMES);
        constraint.setFillHeight(true);
        return constraint;
    }

    public static RubikSide blankSide(int dimension) { return new RubikSide(dimension, -1); }

    public static void sideToString(int dimension, StringBuilder sb, int[][]... sides)
    {
        int numRow = 0;
        while(numRow != dimension)
        {
            for(int[][] side:sides)
            {
                for(int col = 0;col < dimension;col++)
                {
                    int column = side[numRow][col];
                    if(column == -1)
                    {
                        if(col == 0) sb.append("   ");
                        else if(col == dimension - 1) sb.append("    ");
                        else sb.append("  ");
                    }
                    else
                    {
                        if(col == 0) sb.append("[ ").append(column);
                        else if(col == dimension - 1) sb.append(' ').append(column).append(" ]");
                        else sb.append(' ').append(column);
                    }
                }
            }
            sb.append('\n');
            numRow++;
        }
    }
}
