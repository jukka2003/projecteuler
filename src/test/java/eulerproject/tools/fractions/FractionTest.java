package eulerproject.tools.fractions;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by grzesikl on 10/11/2016.
 */
public class FractionTest {
    @Test
    public void testGetResilentFactor() throws Exception {
        Assert.assertEquals(new Fraction(4,11),new Fraction(1,12).getResilenceFactor());

    }

    @Test
    public void testIsResilent() throws Exception {
        Assert.assertFalse(new Fraction(2,12).isResilent());
        Assert.assertFalse(new Fraction(3,12).isResilent());
        Assert.assertFalse(new Fraction(4,12).isResilent());
        Assert.assertFalse(new Fraction(10,12).isResilent());

        Assert.assertTrue(new Fraction(5,12).isResilent());
        Assert.assertTrue(new Fraction(7,12).isResilent());
        Assert.assertTrue(new Fraction(11,12).isResilent());
    }

    @Test
    public void testCompareTo() {
        Assert.assertTrue(new Fraction(1,2).compareTo(new Fraction(2,3))<0);
        Assert.assertTrue(new Fraction(2,51).compareTo(new Fraction(2,50))<0);
        Assert.assertTrue(new Fraction(1,2).compareTo(new Fraction(1,2))==0);
        Assert.assertTrue(new Fraction(1,2).compareTo(new Fraction(1,3))>0);
        Assert.assertTrue(new Fraction(1,4).compareTo(new Fraction(2,8))==0);
        Assert.assertTrue(new Fraction(51,50).compareTo(new Fraction(1,1))>0);

    }

}