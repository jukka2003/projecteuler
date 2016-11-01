package eulerproject.level3.problem62;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static eulerproject.level3.problem62.Solution.convertToCharList;
import static eulerproject.level3.problem62.Solution.getNumberOfCubePermutations;
import static eulerproject.level3.problem62.Solution.isPermutation;

/**
 * Created by Lukasz on 2016-10-31.
 */
public class SolutionTest {
    @Test
    public void testGetNumberOfCubePermutations() throws Exception {
        Assert.assertEquals(3,getNumberOfCubePermutations("41063625"));

    }

    @Test
    public void testIsPermutation() throws Exception {

        Assert.assertTrue(isPermutation("asdf","adfs"));
        Assert.assertTrue(isPermutation("1234","4321"));
        Assert.assertTrue(isPermutation("111234","121314"));

    }

    @Test
    public void testConvertToCharList() throws Exception {
        LinkedList<Character> list =   new LinkedList<Character>();
        list.addAll(Arrays.asList('a','b','c','d'));

        Assert.assertEquals(list,convertToCharList(new char[]{'a','b','c','d'}));

    }


}