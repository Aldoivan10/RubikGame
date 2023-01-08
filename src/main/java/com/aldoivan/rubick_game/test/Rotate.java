package com.aldoivan.rubick_game.test;

public class Rotate
{

    // Method
    // To rotate a matrix of
    // dimension r x c. And initially,
    // r = r and q = c
    static void rotate_matrix(int m, int n, int matrix[][])
    {
        int rw = 0, cl = 0;
        int previous, current;

        // rw is the Starting row index
        // r is the ending row index
        // cl is the starting column index
        // q is the ending column index and
        // x is the iterator
        while (rw < m && cl < n) {

            if (rw + 1 == m || cl + 1 == n)
                break;

            // After storing the first element of the
            // next row, this element will substitute
            // the first element of the current row
            previous = matrix[rw + 1][cl];

            // Moving the elements of the first row
            // from rest of the rows
            for (int x = cl; x < n; x++) {
                current = matrix[rw][x];
                matrix[rw][x] = previous;
                previous = current;
            }
            rw++;

            // Moving the elements of the last column
            // from rest of the columns
            for (int x = rw; x < m; x++) {
                current = matrix[x][n - 1];
                matrix[x][n - 1] = previous;
                previous = current;
            }
            n--;

            // Moving the elements of the last row
            // from rest of the rows
            if (rw < m) {
                for (int x = n - 1; x >= cl; x--) {
                    current = matrix[m - 1][x];
                    matrix[m - 1][x] = previous;
                    previous = current;
                }
            }
            m--;

            // Moving elements of the first column
            // from rest of the rows
            if (cl < n) {
                for (int x = m - 1; x >= rw; x--) {
                    current = matrix[x][cl];
                    matrix[x][cl] = previous;
                    previous = current;
                }
            }
            cl++;
        }

        // Prints the rotated matrix
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++)
                System.out.print(matrix[x][y] + " ");
            System.out.print("\n");
        }
    }

    // Method 2
    // Main driver method
    public static void main(String[] args)
    {

        // Custom input array
        int b[][] = { { 5, 6, 7},
                { 1, 2, 3 },
                { 0, 15, 6}};

        // Calling function(Method1) to rotate matrix
        rotate_matrix(3,3,b);
    }
}
