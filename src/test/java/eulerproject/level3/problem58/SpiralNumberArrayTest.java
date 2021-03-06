package eulerproject.level3.problem58;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpiralNumberArrayTest {
    int[][] testIntArray1;
    int[][] testIntArray2;

    @Before
    public void init() {
        testIntArray1 = new int[][]{
                {
                        37, 36, 35, 34, 33, 32, 31
                },
                {
                        38, 17, 16, 15, 14, 13, 30
                },
                {
                        39, 18, 5, 4, 3, 12, 29
                },
                {
                        40, 19, 6, 1, 2, 11, 28
                },
                {
                        41, 20, 7, 8, 9, 10, 27
                },
                {
                        42, 21, 22, 23, 24, 25, 26
                },
                {
                        43, 44, 45, 46, 47, 48, 49
                }
        };
        testIntArray2 = new int[][]{
                {5, 4, 3},
                {6, 1, 2},
                {7, 8, 9}
        };

    }

    @Test
    public void getFirstDiagonal2() throws Exception {
        SpiralNumberArray testSpiralArray = new SpiralNumberArray(testIntArray1);
        Assert.assertArrayEquals(new int[]{1},testSpiralArray.getFirstDiagonal(1));
        Assert.assertArrayEquals(new int[]{5,1,9},testSpiralArray.getFirstDiagonal(3));
        Assert.assertArrayEquals(new int[]{17,5,1,9,25},testSpiralArray.getFirstDiagonal(5));
        Assert.assertArrayEquals(new int[]{37,17,5,1,9,25,49},testSpiralArray.getFirstDiagonal(7));
    }


    @Test
    public void getSecondDiagonal() throws Exception {
        SpiralNumberArray testSpiralArray = new SpiralNumberArray(testIntArray1);
        Assert.assertArrayEquals(new int[]{1},testSpiralArray.getSecondDiagonal(1));
        Assert.assertArrayEquals(new int[]{3,1,7},testSpiralArray.getSecondDiagonal(3));
        Assert.assertArrayEquals(new int[]{13,3,1,7,21},testSpiralArray.getSecondDiagonal(5));
        Assert.assertArrayEquals(new int[]{31,13,3,1,7,21,43},testSpiralArray.getSecondDiagonal(7));
    }




}
