package com.aldoivan.rubick_game.view;

import com.aldoivan.rubick_game.controller.RubikController;
import com.aldoivan.rubick_game.exception.RubikException;
import com.aldoivan.rubick_game.model.Rubik;
import com.aldoivan.rubick_game.util.RubikUtil;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

public class RubikFrame extends BorderPane
{
    public RubikFrame()
    {

        this.setPadding(new Insets(15));
        this.initialize(true);
    }

    private void initialize(boolean showPad)
    {
        if(showPad)
        {
            RubikPad pad = new RubikPad();
            this.setTop(pad);
        }
    }

    private static Rubik2DPane createRubikView() throws RubikException
    {
        Rubik rubik = new Rubik(3);
        RubikController controller = new RubikController(rubik);
        Rubik2DPane rubik2DPane = new Rubik2DPane(controller, RubikUtil.basicColors());
        return rubik2DPane;
    }
}
