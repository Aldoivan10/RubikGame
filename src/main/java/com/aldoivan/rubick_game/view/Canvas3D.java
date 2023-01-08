package com.aldoivan.rubick_game.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.util.concurrent.atomic.AtomicReference;

public class Canvas3D extends StackPane
{
    private final Group root = new Group();
    public Canvas3D()
    {
        SubScene subScene = new SubScene(root,800,600,true, SceneAntialiasing.BALANCED);
        PerspectiveCamera camera = this.createCamera(this);
        subScene.heightProperty().bind(this.heightProperty());
        subScene.widthProperty().bind(this.widthProperty());
        subScene.setFill(Color.LIGHTSALMON);
        subScene.setCamera(camera);

        this.addRotationListener(this.root,this);
        this.getChildren().add(subScene);
    }
    private PerspectiveCamera createCamera(Pane parent)
    {
        PerspectiveCamera camera = new PerspectiveCamera(true);
        Rotate rotateX = new Rotate(-20,Rotate.X_AXIS),rotateY = new Rotate(0,Rotate.Y_AXIS);

        camera.getTransforms().addAll(rotateX, rotateY, new Translate(0, 0, -2000));

        camera.setNearClip(0.1);
        camera.setFarClip(4000);
        camera.setFieldOfView(35);

        parent.setOnScroll(event -> camera.setTranslateZ(camera.getTranslateZ() + event.getDeltaY()));
        parent.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
        {
            if(event.getButton() == MouseButton.MIDDLE && event.getClickCount() == 2) camera.setTranslateZ(0);
        });

        return camera;
    }
    private void addRotationListener(Node node, Pane parent)
    {
        Rotate xRotate = new Rotate(0, Rotate.X_AXIS);
        Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
        DoubleProperty angleX = new SimpleDoubleProperty(0);
        DoubleProperty angleY = new SimpleDoubleProperty(0);
        AtomicReference<Double> anchorX = new AtomicReference<>(0d);
        AtomicReference<Double> anchorY = new AtomicReference<>(0d);
        AtomicReference<Double> anchorAngleX = new AtomicReference<>(0d);
        AtomicReference<Double> anchorAngleY = new AtomicReference<>(0d);

        node.getTransforms().addAll(xRotate, yRotate);

        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        parent.setOnMouseClicked(event ->
        {
            if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2)
            {
                angleX.set(0);
                angleY.set(0);
            }
        });

        parent.addEventHandler(MouseEvent.MOUSE_PRESSED, event ->
        {
            if(!event.isPrimaryButtonDown()) return;
            anchorX.set(event.getSceneX());
            anchorY.set(event.getSceneY());
            anchorAngleX.set(angleX.get());
            anchorAngleY.set(angleY.get());
        });

        parent.addEventHandler(MouseEvent.MOUSE_DRAGGED,event ->
        {
            if(!event.isPrimaryButtonDown()) return;
            angleX.set(anchorAngleX.get() - (anchorY.get() - event.getSceneY()));
            angleY.set(anchorAngleY.get() + anchorX.get() - event.getSceneX());
        });
    }
    public void addChild(Node shape) { this.root.getChildren().add(shape); }
}
