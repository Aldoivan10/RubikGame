package com.aldoivan.rubickgame.model;

import com.aldoivan.rubickgame.exception.RubikException;
import com.aldoivan.rubickgame.util.RubikUtil;

import java.util.Arrays;

public class Rubik
{
    private final RubikSide[] rubik = new RubikSide[7];

    public Rubik() { }

    public Rubik(int dimension) throws RubikException { this.buildRubik(dimension); }

    public RubikSide top() { return this.rubik[0]; }

    public RubikSide bot() { return this.rubik[5]; }

    public RubikSide left() { return this.rubik[1]; }

    public RubikSide right() { return this.rubik[3]; }

    public RubikSide front() { return this.rubik[2]; }

    public RubikSide back() { return this.rubik[4]; }

    public boolean solved() { return Arrays.stream(this.rubik).parallel().allMatch(side -> side.isComplete()); }

    public void buildRubik(int dimension) throws RubikException
    {
            if(dimension < 2) throw new RubikException(String.format("Dimension must be grater than 1. And it was %s.", dimension));
            for (int s = 0; s < 6;s++) { this.rubik[s] = new RubikSide(dimension,s); }
            this.rubik[6] = new RubikSide(dimension, -1);
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        int dimension = this.rubik[0].dimension();
        RubikUtil.sideToString(dimension, sb, this.rubik[6].values(), this.rubik[0].values());
        RubikUtil.sideToString(dimension, sb, this.rubik[1].values(), this.rubik[2].values(),this.rubik[3].values(),this.rubik[4].values());
        RubikUtil.sideToString(dimension, sb, this.rubik[6].values(), this.rubik[5].values());
        return sb.toString();
    }

}
