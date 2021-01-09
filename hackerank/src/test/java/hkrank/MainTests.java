package hkrank;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class MainTests {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    public void testCompareTripleTest() {
        List<Integer> compareTriplets = Main.compareTriplets(Arrays.asList(5, 6, 7), Arrays.asList(3, 6, 10));
        Assert.assertEquals(Arrays.asList(1, 1), compareTriplets);

        compareTriplets = Main.compareTriplets(Arrays.asList(17, 28, 30), Arrays.asList(99, 16, 8));
        Assert.assertEquals(Arrays.asList(2, 1), compareTriplets);
    }

    @Test
    public void diagonalDifferenceTest() {
        List<List<Integer>> input = Arrays.asList(
                Arrays.asList(11, 2, 4),
                Arrays.asList(4, 5, 6),
                Arrays.asList(10, 8, -12)
        );
        int actual = Main.diagonalDifference(input);

        Assert.assertEquals(15, actual);
    }

    @Test
    public void plusMinusTest() {
        System.setOut(new PrintStream(outputStreamCaptor));
        int[] input = new int[]{1, 1, 0, -1, -1};
        Main.plusMinus(input);

        String expected = "0.400000\n" +
                "0.400000\n" +
                "0.200000\n";
        Assert.assertEquals(expected.trim(), outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }
}
