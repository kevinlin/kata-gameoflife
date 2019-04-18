package com.zuhlke.kata.gameoflife;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;

public class GameOfLifeTest {

    @Test
    public void TwoByTwo_1LiveCell_AllDead() {
        int[][] initial = new int[][]{{1, 0}, {0, 0}};

        int[][] actual = GameOfLife.evolve(initial);

        int[][] expected = new int[][]{{0, 0}, {0, 0}};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void ThreeByThree_1LiveCell_AllDead() {
        int[][] initial = new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};

        int[][] actual = GameOfLife.evolve(initial);

        int[][] expected = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void OneByThree_3LiveCell_1MiddleAlive() {
        int[][] initial = new int[][]{{1, 1, 1},};

        int[][] actual = GameOfLife.evolve(initial);

        int[][] expected = new int[][]{{0, 1, 0},};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void OneByFour_3LiveCellSeparated_AllDead() {
        int[][] initial = new int[][]{{1, 1, 0, 1},};

        int[][] actual = GameOfLife.evolve(initial);

        int[][] expected = new int[][]{{0, 0, 0, 0},};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void twoByTwo_allAlive_allStayAlive() {
        int[][] initial = new int[][]{{1, 1}, {1, 1},};

        int[][] actual = GameOfLife.evolve(initial);

        int[][] expected = new int[][]{{1, 1}, {1, 1},};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void twoByTwo_oneDead_comesToLife() {
        int[][] initial = new int[][]{{1, 1}, {1, 0},};

        int[][] actual = GameOfLife.evolve(initial);

        int[][] expected = new int[][]{{1, 1}, {1, 1},};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void twoByThree_5Alive_overPopulation() {
        int[][] initial = new int[][]{{1, 1, 1}, {1, 0, 1},};

        int[][] actual = GameOfLife.evolve(initial);

        int[][] expected = new int[][]{{1, 0, 1}, {1, 0, 1},};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void fiveByfour_failed() {
        int[][] initial = new int[][]{{0, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 0},};

        int[][] expected = new int[][]{{0, 0, 1, 0, 0}, {0, 0, 1, 1, 0}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 0},};

        int[][] actual = GameOfLife.evolve(initial);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void printMatrix_2x3() {
        int[][] initial = new int[][]{{1, 1, 1}, {1, 0, 1},};

        String expectedOutput = "X X X\n" + "X   X\n";
        assertEquals(expectedOutput, GameOfLife.print(initial));
    }

    private static void assertArrayEquals(String message, int[][] expected, int[][] actual) {
        // If both arrays are null, then we consider they are equal
        if (expected == null && actual == null) {
            return; // We get out of the function as everything is fine.
        }

        // We test to see if the first dimension is the same.
        if (expected.length != actual.length) {
            fail(message + ". The array lengths of the first dimensions aren't the same.");
        }

        // We test every array inside the 'outer' array.
        for (int i = 0; i < expected.length; i++) {
            // Can also use (with JUnit 4.3, but never tried it) assertArrayEquals(actual, expected);
            assertTrue(message + ". Array no." + i + " in expected and actual aren't the same.", Arrays.equals(expected[i], actual[i]));
        }
    }

    /**
     * This method test two "2 dimensional" arrays to see if they are the same
     * size and if the items inside are the same.
     *
     * @param expected The expected 2 dimensional array.
     * @param actual   The actual 2 dimensional array.
     */
    static public void assertArrayEquals(int[][] expected, int[][] actual) {
        assertArrayEquals("Array comparison failed!", expected, actual);
    }
}
