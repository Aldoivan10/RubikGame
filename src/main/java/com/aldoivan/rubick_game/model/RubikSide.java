package com.aldoivan.rubick_game.model;

import java.util.Arrays;
import java.util.stream.IntStream;

public class RubikSide
{
    private int dim;
    private int[][] rows;

    public RubikSide() { }

    public RubikSide(int dim) { this.dim = dim; }

    public RubikSide(int dim, int val) { this.buildSide(dim, val); }

    public void buildSide(int dim, int val)
    {
        this.dim = dim;
        int[] cols = IntStream.generate(() -> val).limit(dim).toArray();
        this.rows = IntStream.range(0, dim).boxed().map(i -> cols.clone()).toArray(int[][]::new);
    }

    public int[] col(int numCol) { return  IntStream.range(0, this.dim).map(i -> this.rows[i][numCol]).toArray(); }

    public int[] row(int numRow) { return this.rows[numRow]; }

    public void setRow(int numRow, int[] newValues) { this.rows[numRow] = newValues; }
    public void setCol(int numCol, int[] newValues) { IntStream.range(0, this.dim).forEach(i -> this.rows[i][numCol] = newValues[i]); }

    public void setRows(int[][] rows) { this.rows = rows; }

    public int[][] values() { return this.rows; }

    public int dimension() { return this.dim; }

    public boolean isComplete()
    {
        int val = this.rows[0][0];
        if(val == -1) return true;
        return Arrays.stream(this.rows).parallel().allMatch(cols -> Arrays.stream(cols).allMatch(col -> col == val));
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < dim;i++)
        {
            for(int j = 0;j < dim;j++)
            {
                sb.append(String.format("[%s]",this.rows[i][j]));
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
