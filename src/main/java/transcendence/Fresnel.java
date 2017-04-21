package transcendence;


import complex.Cpx;


public class Fresnel {

    private static final double EPS = 6.0E-8;
    private static final double FPMIN = 1.0E-10;
    private static final double MAXIT = 100;
    private static final double XMIN = 1.5;

    /**
     * Computes the <i>Fresnel Integrals</i>, <i>C(x)</i> and <i>S(x)</i> for real
     * value <i>x</i>. The result is returned as a Complex object, where real part
     * is equal to <i>C(x)</i>, and imaginary part is equal to <i>S(x)</i>.
     * <br>
     *     Algorithm from <i>Numerical Recipsed p. 256</i>
     */
    public static Cpx fresnel(double x){

        double[] res = {0,0};
        double absx = Math.abs(x);

        if(absx < Math.sqrt(FPMIN)){
            res[0] = absx;
            res[1] = 0.0;
        }
        else if(absx <= XMIN){
            double sum = 0.0, sumS = 0.0;
            double sumC = absx;
            double sign = 1.0;
            double fact = Math.PI * 0.5 * absx * absx;
            boolean odd = true;
            double term = absx;
            int n = 3;
            for(int k = 1; k <= MAXIT; k++){
                term *= fact / k;
                sum += sign * term / n;
                double test = Math.abs(sum) * EPS;
                if(odd){
                    sign = -sign;
                    sumS = sum;
                    sum = sumC;
                }else{
                    sumC = sum;
                    sum = sumS;
                }
                if(term < test) break;
                odd = !odd;
                n += 2;
            }
           res[0] = sumC;
            res[1] = sumS;

        }
        else{
            double pix2 = Math.PI * absx * absx;
            Cpx b = new Cpx(1.0, - pix2);
            Cpx cc = new Cpx(1.0 / FPMIN, 0.0);
            Cpx d = Cpx.ONE.divide(b);
            Cpx h = Cpx.ONE.divide(b);
            int n = -1;
            for (int k = 2; k <= MAXIT; k++){
                n += 2;
                int a = -n * (n + 1);
                b = b.add(new Cpx(4.0, 0.0));
                d = Cpx.ONE.divide(d.multiplyr(a).add(b));
                cc = b.add(new Cpx(a, 0.0).divide(cc));
                Cpx del = cc.multiply(d);
                h = h.multiply(del);
                if(Math.abs(del.real() - 1.0) + Math.abs(del.imaginary()) < EPS) break;
            }
            h = new Cpx(absx, -absx).multiply(h);
            Cpx cs = new Cpx(0.5,0.5).multiply(Cpx.ONE.subtract(new Cpx(Math.cos(0.5 * pix2), Math.sin(0.5 * pix2)).multiply(h)));
            res[0] = cs.real();
            res[1] = cs.imaginary();
        }
        if(x < 0.0){
            res[0] *= -1.0;
            res[1] *= -1.0;
        }
        return new Cpx(res[0], res[1]);
    }
}
