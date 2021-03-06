package eulerproject.tools.fractions;

import eulerproject.tools.functions.EulersTotient;
import eulerproject.tools.primes.Primes;

/**
 * Created by grzesikl on 15/09/2016.
 */
public class Fraction implements Comparable {

    final static Primes primes;

    static {
        primes = new Primes(1000000);
        primes.init();
    }

    public Fraction(long nominator, long denominator) {
        this.nominator = nominator;
        this.denominator = denominator;
    }

    public Fraction(Fraction a) {
        this.nominator = a.getNominator();
        this.denominator = a.getDenominator();
    }

    public long getNominator() {
        return nominator;
    }

    public void setNominator(long nominator) {
        this.nominator = nominator;
    }

    public long getDenominator() {
        return denominator;
    }

    public void setDenominator(long denominator) {
        this.denominator = denominator;
    }

    private long nominator;
    private long denominator;

    @Override
    public String toString() {
        return "[" + nominator + "/" + denominator + "]";
    }

    public Fraction add(Fraction oth) {
        Fraction result;

        if (!isCommonDenominator(oth)) {
            long denominator = getDenominator() * oth.getDenominator();
            long nominator = getNominator() * oth.getDenominator() + oth.getNominator() * getDenominator();

            result = new Fraction(nominator, denominator);

        } else {
            result = new Fraction(getNominator() + oth.getNominator(), getDenominator());
        }

        return result;

    }

    public Fraction sub(Fraction oth) {
        Fraction othRev = new Fraction(-oth.getNominator(), oth.getDenominator());
        return this.add(othRev);
    }

    public boolean isCommonDenominator(Fraction oth) {
        return oth.getDenominator() == getDenominator();
    }

    public Fraction simplifyFraction() {
       long hcf = gcd();
        Fraction result = new Fraction(this.nominator/hcf,this.denominator/hcf);
        return result;
    }

    public Fraction revertFraction() {
        return new Fraction(getDenominator(), getNominator());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;

        if (nominator != fraction.nominator) return false;
        return denominator == fraction.denominator;

    }

    public boolean isResilent() {

        int i = 2;
        long nom = getNominator();
        long den = getDenominator();

        while (i > 0 && i <= nom && i <= den) {

            if (nom % i == 0 && den % i == 0) {
                return false;
            } else {
                i = primes.getNextPrime(i);
            }
        }

        return true;

    }

    public Fraction getResilenceFactor(int[] primes) {

        int counter = EulersTotient.eulersTotientFunction((int)denominator,primes);  //TODO: I dont like this casting

        return new Fraction(counter, denominator - 1);
    }

    @Override
    public int compareTo(Object o) {
        return (int) this.simplifyFraction().sub((Fraction)o).getNominator();
    }

    public double asDouble() {
        return (double)nominator/(double)denominator;
    }

    public long gcd() {
        long hcf;

        if (nominator > denominator)
            hcf = denominator;
        else
            hcf = nominator;

        for (; hcf > 0; hcf--)
            if ((nominator % hcf == 0) && (denominator % hcf == 0))
                break;

        return hcf;
    }

    public boolean isReducedProperFraction() {
        return gcd() == 1;
    }
}
