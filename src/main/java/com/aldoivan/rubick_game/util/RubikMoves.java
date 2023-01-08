package com.aldoivan.rubick_game.util;

import com.aldoivan.rubick_game.model.Rubik;
import com.aldoivan.rubick_game.model.RubikSide;

import java.util.function.Function;
import java.util.stream.IntStream;

public class RubikMoves
{
    public static void rotateRow(Rubik rubik, boolean clockwise, int numRow)
    {
        RubikSide top = rubik.top();
        RubikSide bot = rubik.bot();
        RubikSide left = rubik.left();
        RubikSide right = rubik.right();
        RubikSide front = rubik.front();
        RubikSide back = rubik.back();

        int[] auxRow = front.row(numRow);

        if(clockwise)
        {
            front.setRow(numRow,left.row(numRow));
            left.setRow(numRow,back.row(numRow));
            back.setRow(numRow,right.row(numRow));
            right.setRow(numRow,auxRow);

            if(numRow == 0) rotateCounterClockwise(top);
            else if(numRow == 5) rotateClockwise(bot);
        }
        else
        {
            front.setRow(numRow,right.row(numRow));
            right.setRow(numRow,back.row(numRow));
            back.setRow(numRow,left.row(numRow));
            left.setRow(numRow,auxRow);

            if(numRow == 0) rotateClockwise(top);
            else if(numRow == 5) rotateCounterClockwise(bot);
        }
    }

    public static void rotateCol(Rubik rubik, boolean clockwise, int numCol)
    {
        RubikSide back = rubik.back();
        RubikSide top = rubik.top();
        RubikSide bot = rubik.bot();
        RubikSide left = rubik.left();
        RubikSide right = rubik.right();
        RubikSide front = rubik.front();

        int[] auxCol = front.col(numCol);
        back.setRows(reverseMatrix(back.values()));

        if(clockwise)
        {
            front.setCol(numCol, bot.col(numCol));
            bot.setCol(numCol, back.col(numCol));
            back.setCol(numCol, top.col(numCol));
            top.setCol(numCol, auxCol);

            if(numCol == 0) rotateCounterClockwise(left);
            else if(numCol == 5) rotateClockwise(right);
        }
        else
        {
            front.setCol(numCol, top.col(numCol));
            top.setCol(numCol, back.col(numCol));
            back.setCol(numCol, bot.col(numCol));
            bot.setCol(numCol, auxCol);

            if(numCol == 0) rotateClockwise(left);
            else if(numCol == 5) rotateCounterClockwise(right);
        }
    }

    public static void rotateFace(Rubik rubik, boolean clockwise, int numFace)
    {
        RubikSide back = rubik.back();
        RubikSide top = rubik.top();
        RubikSide bot = rubik.bot();
        RubikSide left = rubik.left();
        RubikSide right = rubik.right();
        RubikSide front = rubik.front();

        switch (numFace)
        {
            case 0 ->
            {
                if(clockwise) rotateClockwise(front);
                else rotateCounterClockwise(front);
                rotateFace(2,0,top,bot,left,right,clockwise);
            }
            case 1 -> rotateFace(1,1,top,bot,left,right,clockwise);
            default ->
            {
                if(clockwise) rotateClockwise(back);
                else rotateCounterClockwise(back);
                rotateFace(0,2,top,bot,left,right,clockwise);
            }
        }
    }

    private static void rotateFace(int num1,int num2, RubikSide top, RubikSide bot,RubikSide left, RubikSide right, boolean clockwise)
    {
        int[] colLeft,rowTop,colRight,rowBot;

        colLeft = left.col(num1);
        rowTop = top.row(num1);
        colRight = right.col(num2);
        rowBot = bot.row(num2);

        if(clockwise)
        {
            top.setRow(num1, reverseArray(colLeft));
            bot.setRow(num2, reverseArray(colRight));
            left.setCol(num1, rowBot);
            right.setCol(num2, rowTop);
        }
        else
        {
            bot.setRow(num2, colLeft);
            top.setRow(num1, colRight);
            left.setCol(num1, reverseArray(rowTop));
            right.setCol(num2, reverseArray(rowBot));
        }
    }

    private static void rotateClockwise(RubikSide side) { doRotate(side,i -> reverseArray(side.col(i - 1))); }

    private static void rotateCounterClockwise(RubikSide side) { doRotate(side, i -> side.col(side.dimension() - i)); }

    private static int[][] reverseMatrix(int[][] arr)
    {
        int[][] interim =  IntStream.range(0, arr.length)
                .boxed()
                .map(i -> reverseArray(arr[i]))
                .toArray(int[][]::new);
        return IntStream.rangeClosed(1, interim.length)
                .boxed()
                .map(i -> interim[interim.length - i])
                .toArray(int[][]::new);
    }

    private static void doRotate(RubikSide side, Function<Integer,int[]> function)
    {
        int dim = side.dimension();
        int[][] rotation = IntStream.rangeClosed(1, dim)
                .boxed()
                .map(function)
                .toArray(int[][]::new);
        side.setRows(rotation);
    }
    private static int[] reverseArray(int[] arr)
    {
        return IntStream.rangeClosed(1, arr.length)
                .map(i -> arr[arr.length - i])
                .toArray();
    }
}
