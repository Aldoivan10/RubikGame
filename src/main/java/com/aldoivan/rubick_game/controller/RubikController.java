package com.aldoivan.rubick_game.controller;

import com.aldoivan.rubick_game.model.Rubik;
import com.aldoivan.rubick_game.util.RubikMoves;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RubikController
{
    private final ArrayList<Consumer<Rubik>> events =  new ArrayList<>();

    private Rubik rubik;

    public RubikController() { }

    public RubikController(Rubik rubik) { this.setRubik(rubik); }

    public void setRubik(Rubik rubik) { this.rubik = rubik; }

    public void rotateRow(int numRow, boolean clockwise)
    {
        RubikMoves.rotateRow(this.rubik, clockwise, numRow);
        this.events.stream().parallel().forEach(rubikConsumer -> rubikConsumer.accept(this.rubik));
    }

    public void rotateColumn(int numColumn, boolean clockwise)
    {
        RubikMoves.rotateCol(this.rubik, clockwise, numColumn);
        this.events.stream().parallel().forEach(rubikConsumer -> rubikConsumer.accept(this.rubik));
    }

    public void rotateFace(int numFace, boolean clockwise)
    {
        RubikMoves.rotateFace(this.rubik, clockwise, numFace);
        this.events.stream().parallel().forEach(rubikConsumer -> rubikConsumer.accept(this.rubik));
    }

    public void onChange(Consumer<Rubik>... events) { this.events.addAll(List.of(events)); }

    public Rubik rubik() { return this.rubik; }

    public void print() { System.out.println(this.rubik); }
}
