package projecteuler.problem49;

import org.junit.Assert;
import org.junit.Test;
import projecteuler.problem43.Listener;
import projecteuler.problem43.ListenerImplCheck;
import projecteuler.problem43.Permutation;
import projecteuler.problem43.PermutationImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import static org.junit.Assert.*;
import static projecteuler.problem49.Solution.checkCondition;
import static projecteuler.problem49.Solution.convertInteger;

/**
 * Created by grzesikl on 09/09/2016.
 */
public class SolutionTest {
    @Test
    public void testCheckCondition() throws Exception {

        Assert.assertTrue(checkCondition(1487));
        Assert.assertTrue(checkCondition(4817));
        Assert.assertFalse(checkCondition(1467));
    }

    @Test
    public void testConvertInteger() throws Exception {
        LinkedList<Character> testList = new LinkedList<>();
        testList.add('1');
        testList.add('2');
        testList.add('5');

        Assert.assertEquals(testList,convertInteger(125));

    }

    @Test
    public void testPermutation() {

        LinkedList<Character> numberListChar = new LinkedList<>();
        numberListChar.add('1');
        numberListChar.add('2');
        numberListChar.add('3');
        numberListChar.add('4');

        Permutation<Character> perm = new PermutationImpl<Character>();
        Listener<Character> listener = new ListenerImplTest();

        perm.generate(numberListChar.size()-1,numberListChar,listener);
    }

}