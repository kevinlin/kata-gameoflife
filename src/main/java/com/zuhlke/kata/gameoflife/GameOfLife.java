package com.zuhlke.kata.gameoflife;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

class GameOfLife {

    public static void main(String... args) throws Exception {
        int[][] arr = new int[30][20];

        for (int[] row : arr) {
            IntStream.range(0, row.length).forEach(i -> row[i] = 0);
        }

        arr[10][10] = 1;
        arr[11][10] = 1;
        arr[12][10] = 1;
        arr[13][10] = 1;
        arr[14][10] = 1;
        arr[15][10] = 1;
        arr[16][10] = 1;
        arr[17][10] = 1;

        arr[10][11] = 1;
        //ar1[31][41] = 1;
        arr[12][11] = 1;
        arr[13][11] = 1;
        arr[14][11] = 1;
        arr[15][11] = 1;
        //ar1[36][41] = 1;
        arr[17][11] = 1;

        arr[10][12] = 1;
        arr[11][12] = 1;
        arr[12][12] = 1;
        arr[13][12] = 1;
        arr[14][12] = 1;
        arr[15][12] = 1;
        arr[16][12] = 1;
        arr[17][12] = 1;

        //@formatter:off
        int[][] initial = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0, 0 ,0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0 ,0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0 ,0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0 ,0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0 ,0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0 ,0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0 ,0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0 ,0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0 ,0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0 ,0},
        };
        //@formatter:on

        while (true) {
            arr = evolve(arr);
            Thread.sleep(100);
            System.out.println(print(arr));
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

    static int[][] evolve(final int[][] initial) {
        int[][] nextGen = new int[initial.length][initial[0].length];

        for (int x = 0; x < initial.length; x++) {
            for (int y = 0; y < initial[0].length; y++) {
                int aliveNeighbours = countOfAliveNeighbours(initial, x, y);
                if (initial[x][y] == 1) {
                    nextGen[x][y] = aliveNeighbours == 2 || aliveNeighbours == 3 ? 1 : 0;
                } else if (aliveNeighbours == 3) {
                    nextGen[x][y] = 1;
                }
            }
        }

        return nextGen;
    }

    private static int countOfAliveNeighbours(final int[][] matrix, final int x, final int y) {
        int count = 0;
        int width = matrix[x].length;
        int height = matrix.length;

        // left
        if (y > 0) {
            count += matrix[x][y - 1];
        }

        // left top
        if (y > 0 && x > 0) {
            count += matrix[x - 1][y - 1];
        }

        // top
        if (x > 0) {
            count += matrix[x - 1][y];
        }

        // top right
        if (x > 0 && y < width - 1) {
            count += matrix[x - 1][y + 1];
        }

        // right
        if (y < width - 1) {
            count += matrix[x][y + 1];
        }

        // bottom right
        if (x < height - 1 && y < width - 1) {
            count += matrix[x + 1][y + 1];
        }

        // bottom
        if (x < height - 1) {
            count += matrix[x + 1][y];
        }

        // bottom left
        if (x < height - 1 && y > 0) {
            count += matrix[x + 1][y - 1];
        }

        return count;
    }

    static String print(final int[][] initial) {

        StringBuilder life = new StringBuilder();

        for (final int[] ints : initial) {
            String row = IntStream.of(ints).mapToObj(i -> i == 0 ? "." : "X").collect(Collectors.joining(" "));

            life.append(row).append(System.lineSeparator());
        }

        return life.toString();
    }
}
