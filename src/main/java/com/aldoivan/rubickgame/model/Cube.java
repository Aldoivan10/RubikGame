package com.aldoivan.rubickgame.model;

import com.aldoivan.rubickgame.App;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.fxyz3d.importers.Importer3D;
import org.fxyz3d.importers.Model3D;

import java.util.Objects;

import static java.lang.Math.abs;

public class Cube
{
    private Model3D model;
    private final Rotate rotX = new Rotate(0,Rotate.X_AXIS);
    private final Rotate rotY = new Rotate(0,Rotate.Y_AXIS);
    private final Rotate rotZ = new Rotate(0,Rotate.Z_AXIS);

    public Cube()
    {
        try
        {
            this.model = Importer3D.load(Objects.requireNonNull(App.class.getResource("Cube.obj")));
            this.model.getRoot().getTransforms().addAll(this.rotX,this.rotY,this.rotZ);
        }
        catch (Exception e) { e.printStackTrace(); }
    }


    private void rotate(DoubleProperty init, int finish)
    {
        final Timeline timeline = new Timeline();
        final KeyValue kv = new KeyValue(init, finish, Interpolator.LINEAR);
        final KeyFrame kf = new KeyFrame(Duration.seconds(0.1), kv);

        timeline.setOnFinished(event ->
        {
            if(abs(finish) == 360) init.setValue(0);
        });
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    public Group obj() { return this.model.getRoot(); }
}
