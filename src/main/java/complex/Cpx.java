package complex;



/**
 * Implementation of Complex Numbers.
 */
public final class Cpx {

    private double mR, mI;
    private static final double EPSILON = 0.000_0000_001;

    public static final Cpx ONE = new Cpx(1,0);
    public static final Cpx I = new Cpx(0,1);
    public static final Cpx ZERO = new Cpx(0,0);

    public Cpx(double real, double imaginary){
        mR = real;
        mI = imaginary;
    }

    /**
     * Creates a new COmplex Numebr from the given polar coordinates, <i>(argument, magnitude)</i>.
     * @param radians argument
     * @param magnitude magnitude of the complex number
     * @return complex number
     */
    public static Cpx fromPolar(double radians, double magnitude){
        double c = Math.cos(radians);
        double s = Math.sin(radians);
        return new Cpx(c * magnitude, s * magnitude);
    }

    /**
     * Returns the real part of this complex number/
     * @return real part
     */
    public double real(){ return mR;}

    /**
     * Returns the imaginary part of this cimplex number.
     * @return imaginary part
     */
    public double imaginary(){ return mI;}

    /**
     * Sets the real part of this complex number.
     * @param r real part
     */
    public void setReal(double r){
        mR = r;
    }

    /**
     * Sets the imaginary part of this complex number.
     * @param i imaginary part
     */
    public void setImaginary(double i){
        mI = i;
    }

    /**
     * Sets the real and imaginary parts of this complex number.
     * @param real real part
     * @param im imaginary part
     */
    public void setComplex(double real, double im){
        mR = real;
        mI = im;
    }

    /**
     * Calcualtes the magnitude of this complex number.
     * @return magnitude
     */
    public double magnitude(){
        return Math.sqrt(mR * mR + mI * mI);
    }


    /**
     * Inserts the given polar coodinate data into the given array. The first
     * array element, <code>polarData[0]</code> is the magnitude, and
     * <code>polarData[1]</code> is the argument (angle subtended by positive
     * real axis). The <code>polarData</code> argument must be a non-null double array
     * with of length at least 2.
     * @param polarData array
     */
    public void asPolar(double[] polarData){
        double mg = magnitude();
        double argument = arg();
        if(mg < EPSILON){
            polarData[0] = mg;
            polarData[1] = 0;
        }
        else {
            polarData[0] = mg;
            polarData[1] = argument;
        }
    }


    /**
     * Calculates the argument of this complex number, the angle
     * which this complex number creates with the positive real
     * axis on the complex plane.
     * @return argument value
     */
    public double arg(){
        return Math.atan2(mI, mR);
    }


    /**
     * Normalizes this complex number by dividing both real and imaginary
     * parts by the magnitude of the complex number.
     */
    public void normalize(){
        double mg = magnitude();
        if(mg < EPSILON){
            mR = 0;
            mI = 0;
        }
        else{
            mR /= mg;
            mI /= mg;
        }
    }

    /**
     * Returns a new complex number equal to the conjugate of this
     * complex number.<br>
     *     e.g. for given complex number
     *     <br>
     *         <i>a + i*b</i><br>
     * this method will return a complex number equal to<br>
     *     <i> a - i*b</i>
     * @return complex conjugate
     */
    public Cpx conjugate(){
        return new Cpx(mR, -mI);
    }

    /**
     * Returns a new complex number equal to this complex number plus the
     * given complex number.
     * @param other complex number
     * @return complex number
     */
    public Cpx add(Cpx other){
        return new Cpx(mR + other.mR, mI + other.mI);
    }

    /**
     * Returns a new complex number equal to this complex number plus the given
     * real number.
     * @param real real number
     * @return complex number
     */
    public Cpx addr(double real){ return new Cpx(mR+real, mI);}

    /**
     * Returns a new complex number equal to this complex number plus the given
     * imaginary number.
     * @param imaginary imaginary number
     * @return complex number
     */
    public Cpx addi(double imaginary){
        return new Cpx(mR, mI + imaginary);
    }

    /**
     * Returns a new complex number equal to this complex number minus the given
     * complex number argument.
     * @param other complex number
     * @return complex number
     */
    public Cpx subtract(Cpx other){
        return new Cpx(mR - other.mR, mI - other.mI);
    }

    /**
     * Returns a new complex number equal to this complex number minus the
     * given real number.
     * @param real real number
     * @return complex number
     */
    public Cpx subtractr(double real){
        return new Cpx(mR - real, mI);
    }

    /**
     * Returns a new complex number equal to this complex number minus the
     * given imaginary number.
     * @param imaginary imaginary number
     * @return complex number
     */
    public Cpx subtracti(double imaginary){
        return new Cpx(mR, mI - imaginary);
    }

    /**
     * Multiplies this complex number by the given complex number and returns a new
     * complex number equal to this value.
     * @param other complex number
     * @return complex number
     */
    public Cpx multiply(Cpx other){
        return new Cpx(mR * other.mR - mI * other.mI, mR *other.mI + mI * other.mR);
    }


    /**
     * Multiplies this complex number by the given real number.
     * @param real imaginary number
     * @return complex number
     */
    public Cpx multiplyr(double real){
        return new Cpx(mR * real, mI * real);
    }


    /**
     * Multiplies this complex number by the given imaginary number.
     * @param imaginary imaginary number
     * @return complex number
     */
    public Cpx multiplyi(double imaginary){
        return new Cpx(-mI * imaginary, mR * imaginary);
    }


    /**
     * Returns a new Complex Number equal to this complex number divided by the given
     * Complex Number. If the magnitude of the complex number parameter is zero then
     * an <code>ArithmeticException</code> will be thrown.
     * @param other complex number
     * @return complex number
     * @throws ArithmeticException
     */
    public Cpx divide(Cpx other) throws ArithmeticException{
        double mg = other.magnitude();
        if(mg < EPSILON) throw new ArithmeticException("Divide by zero-magnitude complex number.");
        else{
            Cpx conj = other.conjugate();
            Cpx cm = other.multiply(conj); //im part is zero
            Cpx numerator = this.multiply(conj);

            numerator.mR /= cm.mR;
            numerator.mI /= cm.mR;
            return numerator;
        }
    }

    /**
     * Returns a new complex number equal to this complex number
     * divided by the given real number. If the real number argument
     * is zero then an <code>ArithmeticException</code> will be thrown.
     * @param real real number
     * @return complex number
     */
    public Cpx divider(double real) throws ArithmeticException{
        return new Cpx(mR / real, mI / real);
    }

    /**
     * Returns a new complex number equal to this complex number
     * divided by the given imaginary number. If the imaginary number argument
     * is zero then an <code>ArithmeticException</code> will be thrown.
     * @param imaginary imaginary number
     * @return complex number
     */
    public Cpx dividei(double imaginary) throws ArithmeticException{
        return new Cpx(-mI / imaginary, mR / imaginary);
    }





    /**
     * Calculates the inverse of this complex number.
     * @return
     */
    public Cpx inverse(){
        return ONE.divide(this);
    }


    /**
     * Calculates the integer power of a complex number.
     * @param n
     * @return
     */
    public Cpx pow(int n){
        if(n == 0) return ONE;
        else if(n < 0){
            return inverse().pow(-n);
        }
        else {
            double[] d = {0, 0};
            this.asPolar(d);
            double rn = Math.pow(d[0], n);
            if (rn < 0) throw new RuntimeException("Double overflow");
            double tn = d[1] * n;
            return Cpx.fromPolar(rn, tn);
        }
    }

    /**
     * Calculates <i>this ^ other</i> complex arithmetic expression. Does not check for
     * floating point overflows (for doubles).
     * @param c
     * @return
     */
    public Cpx pow(Cpx c){
        try {
            double argument = arg();
            double mag = magnitude();

            if(mag < EPSILON)
                return ONE;

            double R1 = Math.pow(mag, c.mR);
            double R2 = Math.exp(-argument * c.mI);

            double E1 = argument * c.mR + c.mI * Math.log(mag);

            return Cpx.fromPolar(E1, R1 * R2);
        }catch(ArithmeticException e){
            throw new ComplexException("Could not comlete complex arithmetic. "+e.getMessage());
        }

    }

    @Override
    public String toString(){
        return "("+mR+", "+mI+")";
    }


    /**
     * Exception class thrown for various arithmetic errors.
     */
    public class ComplexException extends ArithmeticException{

        public ComplexException(String msg){
            super(msg);
        }

    }

}



