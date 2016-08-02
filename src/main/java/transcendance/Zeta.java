package transcendance;


import arithmetic.BernoulliNumbers;
import arithmetic.Rational;

import java.util.ArrayList;

/**
 * Z function approximation for Riemann's Zeta function on the critical line, (0.5+it).
 * @see https://en.wikipedia.org/wiki/Z_function
 * @author Jon Hough
 *
 */
public final class Zeta {
	
	private static double TWO_PI = 2 * Math.PI;
	private int N = 1;
	private double mImPart = 0;
	private double p = 1;

	
	public Zeta() {

	}

	private void init(double imaginaryPart) {

		mImPart = imaginaryPart;
		double val = Math.sqrt(imaginaryPart / TWO_PI);
		N = (int) val;
		p = val - N;
	}

	private double theta(double value) {
		if (value == 0.0)
			throw new IllegalArgumentException("Must have non-zero value");
		double finalValue = (double) (value * 0.5 * Math.log(value / TWO_PI) - value / 2 - Math.PI / 8 + 1
				/ (48 * value) + 7 / (5760 * Math.pow(value, 3)));

		return finalValue;

	}

	private double summation(double value, int max) {
		float finalValue = 0;
		for (int i = 1; i <= max; i++) {
			finalValue += 2 * Math.pow(i, -0.5) * Math.cos(theta(value) - value * Math.log(i));
		}

		return finalValue;
	}

	private double remainderTerms() {
		double value = 0;
		double minivalue = 0;
		for (int i = 0; i < Coefficients.C0.length; i++) {
			minivalue += Coefficients.C0[i] * Math.pow(1 - 2 * p, 2 * i);

		}
		value += minivalue;
		minivalue = 0;
		for (int i = 0; i < Coefficients.C1.length; i++) {
			minivalue += Coefficients.C1[i] * Math.pow(1 - 2 * p, 2 * i + 1);
		}

		minivalue = minivalue * Math.pow((mImPart * 0.5 / Math.PI), -0.5);

		value += minivalue;
		minivalue = 0;
		for (int i = 0; i < Coefficients.C2.length; i++) {
			minivalue += Coefficients.C2[i] * Math.pow(1 - 2 * p, 2 * i);
		}
		minivalue = minivalue * Math.pow((mImPart * 0.5 / Math.PI), -1);

		value += minivalue;
		for (int i = 0; i < Coefficients.C3.length; i++) {
			value += Coefficients.C3[i] * Math.pow(1 - 2 * p, 2 * i + 1);
		}

		for (int i = 0; i < Coefficients.C4.length; i++) {
			value += Coefficients.C4[i] * Math.pow(1 - 2 * p, 2 * i);
		}
		value = Math.pow(-1, N - 1) * Math.pow((mImPart * 0.5 / Math.PI), -0.25) * value;
		return value;
	}

	/**
	 * Calculates Z(0.5 + it) where t is the imaginary part argument. 
	 * @param imaginaryPart
	 * @return Z(0.5 + i*imaginaryPart), approximation of zeta function at this point.
	 * @throws IllegalArgumentException
	 */
	public double z(double imaginaryPart) throws IllegalArgumentException {
		if (imaginaryPart <= 0)
			throw new IllegalArgumentException();
		this.init(imaginaryPart);
		double value = summation(mImPart, N);
		value += remainderTerms();
		return value;
	}

	/**
	 * Coefficients for the Z function. The arrays of coefficients are taken directly from
	 * HM Edward's
	 * <br>
	 * <i>Riemann's Zeta Function</i>.
	 *
	 */
	private static class Coefficients {

		public static final double[] C0 = { 0.38268343236508977173, 0.43724046807752044936, 0.13237657548034352333,
				-0.01360502604767418865, -0.01356762197010358088, -0.00162372532314446528, 0.00029705353733379691,
				0.00007943300879521469, 0.00000046556124614504, -0.00000143272516309551, -0.00000010354847112314,
				0.00000001235792708384, 0.00000000178810838577, -0.00000000003391414393, -0.00000000001632663392 };

		public static final double[] C1 = { 0.02682510262837535, -0.01378477342635185, -0.03849125048223508,
				-0.00987106629906208, 0.00331075976085840, 0.00146478085779542, 0.00001320794062488,
				-0.00005922748701847, -0.00000598024258537, 0.00000096413224562, 0.00000018334733722 };

		public static final double[] C2 = { 0.005188542830293, 0.000309465838807, -0.011335941078299, 0.002233045741958,
				0.005196637408862, 0.000343991440762, -0.000591064842747, -0.000102299725479, 0.000020888392217,
				0.000005927665493, -0.000000164238384, -0.000000151611998

		};

		public static final double[] C3 = { 0.0013397160907, -0.0037442151364, 0.0013303178920, 0.0022654660765,
				-0.0009548499998, -0.0006010038459, 0.0001012885828, 0.0000686573345, -0.0000005985366,
				-0.0000033316599, -0.0000002191929, 0.0000000789089, 0.0000000094147 };

		public static final double[] C4 = { 0.00046483389, -0.00100566074, 0.00024044856, 0.00102830861,
				-0.00076578609, -0.00020365286, 0.00023212290, 0.00003260215, -0.00002557905, -0.00000410746,
				0.00000117812, 0.00000024456 };
	}


	/**
	 * Calculates <i>Zeta(2 * n)</i> for positive real integer, <i>n</i>.
	 * @param n positive real integer.
	 * @return Zeta(2 * n)
	 */
	public static double zeta2N(int n){
		if( n <= 0)
			throw new IllegalArgumentException("Argument must be positive.");
		Rational b = BernoulliNumbers.calculateNthBernoulliNumber(2 * n);
		return Math.pow(-1, n + 1) * b.toDouble() * Math.pow(( 2* Math.PI), 2 * n) / (2 * Gamma.factorial32(2 * n));
	}
}
