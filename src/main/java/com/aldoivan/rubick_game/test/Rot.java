package com.aldoivan.rubick_game.test;

public class Rot
{
    public static void main(String[] args)
    {
        int m = 3;
        int n = 3;

        int[][] arr1 = {{10, 20, 30},
                        {40, 50, 60},
                        {70, 80, 90}};
        int[][] arr2 = new int[n][m];
        int[][] arr3 = new int[n][m];
        int[][] arr4 = new int[n][m];

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                // matrix transpose
                arr2[j][i] = arr1[i][j];
                // turn matrix 90º clockwise ⟳
                arr3[j][m - 1 - i] = arr1[i][j];
                // turn matrix 90º anticlockwise ⟲
                arr4[n - 1 - j][i] = arr1[i][j];
            }
        }
        for(int i  = 0;i < m;i++)
        {
            for(int num:arr3[i]) System.out.print("["+num+"]");
            System.out.println();
        }

        System.out.println();

        for(int i  = 0;i < m;i++)
        {
            for(int num:arr4[i]) System.out.print("["+num+"]");
            System.out.println();
        }
    }
}
