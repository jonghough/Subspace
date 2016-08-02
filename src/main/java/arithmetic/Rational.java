package arithmetic;

/**
 * Implementation of rational numbers, represented as a tuple (a,b), where a
 * is the numerator and b is the denominator.
 */
public final class Rational {

    private long mNumerator;
    private long mDenominator;

    /**
     * Instantiates a rational number from the given numerator and
     * denominator. If denominator == 0, this will throw an exception.
     *
     * @param num numerator
     * @param den denominator
     */
    public Rational(long num, long den) {
        if (den == 0)
            throw new IllegalArgumentException(("Denominator can not be zero."));
        long divisor = gcd(num, den);
        mNumerator = num / divisor;
        mDenominator = den / divisor;
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, (b + a) % b);
    }

    public double toDouble() {
        return mNumerator * 1.0 / mDenominator;
    }

    public long getNumerator() {
        return mNumerator;
    }

    public long getDenominator() {
        return mDenominator;
    }

    public Rational add(Rational rational) {
        return new Rational(this.mNumerator * rational.getDenominator() + this.mDenominator * rational.getNumerator(),
                this.mDenominator * rational.getDenominator());
    }

    public Rational subtract(Rational rational) {
        return new Rational(this.mNumerator * rational.getDenominator() - this.mDenominator * rational.getNumerator(),
                this.mDenominator * rational.getDenominator());
    }

    public Rational multiply(Rational rational) {
        return new Rational(this.mNumerator * rational.getNumerator(),
                this.mDenominator * rational.getDenominator());
    }

    public Rational multiply(int n){
        return new Rational(this.mNumerator * n,
                this.mDenominator);
    }

    public Rational divide(Rational rational) {
        return new Rational(this.mNumerator * rational.getDenominator(),
                this.mDenominator * rational.getNumerator());
    }

    public Rational reciprocal() {
        if (mNumerator == 0L)
            throw new RuntimeException("Exception finding reciprical of rational where numerator = 0.");
        return new Rational(mDenominator, mNumerator);
    }

    public Rational negate() {
        return new Rational(-1 * mNumerator, mDenominator);
    }


    @Override
    public String toString() {
        return String.format("(%10d, %10d)", mNumerator, mDenominator);
    }

}
