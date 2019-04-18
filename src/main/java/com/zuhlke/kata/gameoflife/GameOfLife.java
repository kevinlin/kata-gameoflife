package com.zuhlke.kata.gameoflife;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

class GameOfLife {

    public static void main(String... args) throws Exception {
        //@formatter:off
        int[][] matrix = new int[][] {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        };
        //@formatter:on

        for (int i = 0; i < 1000; i++) {
            matrix = evolve(matrix);
            Thread.sleep(500);
            System.out.println(print(matrix));
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

    private static int countOfAliveNeighbours(final int[][] matrix, final int rowIdx, final int colIdx) {
        int count = 0;
        int width = matrix[0].length;
        int height = matrix.length;

        boolean notLeftMost = colIdx > 0;
        boolean notRightMost = colIdx < width - 1;
        boolean notTopMost = rowIdx > 0;
        boolean notBottomMost = rowIdx < height - 1;

        // top
        if (notTopMost) {
            count += matrix[rowIdx - 1][colIdx];
        }

        // top left
        if (notTopMost && notLeftMost) {
            count += matrix[rowIdx - 1][colIdx - 1];
        }

        // top right
        if (notTopMost && notRightMost) {
            count += matrix[rowIdx - 1][colIdx + 1];
        }

        // left
        if (notLeftMost) {
            count += matrix[rowIdx][colIdx - 1];
        }

        // right
        if (notRightMost) {
            count += matrix[rowIdx][colIdx + 1];
        }

        // bottom
        if (notBottomMost) {
            count += matrix[rowIdx + 1][colIdx];
        }

        // bottom left
        if (notBottomMost && notLeftMost) {
            count += matrix[rowIdx + 1][colIdx - 1];
        }

        // bottom right
        if (notBottomMost && notRightMost) {
            count += matrix[rowIdx + 1][colIdx + 1];
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
