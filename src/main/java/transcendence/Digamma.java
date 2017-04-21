package transcendence;

/**
 * Digamma function
 */
public class Digamma {

    public static double digamma(double real) {

        //first summand, natural log of arg
        double ln = Math.log(real);

        // summands, up to the 12th power.
        double sum1 = -1.0 / (2 * real);
        double sum2 = -1.0 / (12 * real * real);
        double sum3 = 1.0 / (120 * real * real * real * real);
        double sum4 = -1.0 / (252 * Math.pow(real, 6));
        double sum5 = 1.0 / (240 * Math.pow(real, 8));
        double sum6 = -5.0 / (660 * Math.pow(real, 10));
        double sum7 = 691.0 / (32760 * Math.pow(real, 12));
        double sum8 = -1.0 / (12 * Math.pow(real, 14));

        return ln + sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7 + sum8;
    }

    public static double digamma2(double real){
        double r = real -0.5;

        double s1 = r;
        double s2 = 1.0 / (24 * r);
        double s3 = -37.0 / (8 * 720 * r*r*r);
        double s4 = 10313.0 / (72 * 40320 * Math.pow(r,5));
        double s5 = -5509121.0 / (384 * 3628800 * Math.pow(r,7));

        return Math.log(s1+s1+s3+s4+s5);
    }
}
