package il.ac.tau.cs.sw1.hw6;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

public class SectionBTest {

    @DataProvider
    private Object[][] testContainsDataProvider() {
        return new Object[][]{
                {new int[]{1, 1, 1}, 1},
                {new int[]{1, 1, 1}, 2},
                {new int[]{2, 1, 3}, 4},
                {new int[]{2, 5, 1}, 4},
                {new int[]{1, 2, 4}, 5},
                {new int[]{1, 2, 5}, 5},
        };
    }

    @Test(dataProvider = "testContainsDataProvider")
    public void testContains(int[] array, int value) {
        boolean expected = Arrays.stream(array)
                .anyMatch(i -> i == value);

        assertEquals(SectionB.contains(array, value), expected);
    }

    @DataProvider
    private Object[][] testUnknownDataProvider() {
        return new Object[][]{
                {null, 0},
                {new int[]{}, 0},
                {new int[]{0}, 0},
                {new int[]{0, 0}, 0},
                {new int[]{0, 0, 0}, 1},
                {new int[]{1, 0, 0}, 0},
                {new int[]{-1, 0, 0}, 1},
                {new int[]{-1, 0, -1}, 0}
        };
    }

    @Test(dataProvider = "testUnknownDataProvider")
    public void testUnknown(int[] array, int expected) {
        assertEquals(SectionB.unknown(array), expected);
    }

    @DataProvider
    private Object[][] testMaxDataProvider() {
        return new Object[][]{
                {new int[]{Integer.MIN_VALUE + 1}},
                {new int[]{-4, 0}},
                {new int[]{0, 0, 0}},
                {new int[]{0, 3, 5}},
                {new int[]{0, 3, Integer.MAX_VALUE}},
                {new int[]{0, 0, Integer.MAX_VALUE}}
        };
    }

    @Test(dataProvider = "testMaxDataProvider")
    public void testMax(int[] array) {
        int result = SectionB.max(array);
        assertTrue(Arrays.stream(array).allMatch(value -> value <= result),
                String.format("Array: %s\tReceived Max:%d", Arrays.toString(array), result));
    }

    @DataProvider
    private Object[][] testMinDataProvider() {
        return new Object[][]{
                {new int[]{Integer.MAX_VALUE - 1}},
                {new int[]{5, 0, 3, -5}},
                {new int[]{-1, -2, -3, -1}},
                {new int[]{0, Integer.MIN_VALUE, 5, 16, -15}},
        };
    }

    @Test(dataProvider = "testMinDataProvider")
    public void testMin(int[] array) {
        int result = SectionB.min(array);
        assertTrue(Arrays.stream(array).allMatch(value -> value >= result),
                String.format("Array: %s\tReceived Min:%d", Arrays.toString(array), result));
    }

    @DataProvider
    private Object[][] testReverseDataProvider() {
        return new Object[][]{
                {"abcd", "dcba"},
                {"AbCd", "dCbA"},
                {"Hello World!", "!dlroW olleH"}
        };
    }

    @Test(dataProvider = "testReverseDataProvider")
    public void testReverse(String str, String expected) {
        assertEquals(SectionB.reverse(str), expected);
    }

    @DataProvider
    private Object[][] testGuessDataProvider() {
        return new Object[][]{
                {new int[]{1, 2, 3}},
                {new int[]{-1, 0, 1}},
                {new int[]{3, 3, 3, 3, 3, 5}},
                {new int[]{-3, -3, -3, -3, -3, -1}}
        };
    }

    @Test(dataProvider = "testGuessDataProvider")
    public void testGuess(int[] array) {
        int[] prev = Arrays.copyOf(array, array.length);
        int[] after = SectionB.guess(array);

        assertFalse(isArraySorted(after));
        assertTrue(Arrays.stream(prev).allMatch(i -> Arrays.stream(after).anyMatch(j -> i == j)));
        assertTrue(Arrays.stream(after).allMatch(i -> Arrays.stream(prev).anyMatch(j -> i == j)));

    }

    private boolean isArraySorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}