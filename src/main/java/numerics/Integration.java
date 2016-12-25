package numerics;


import java.util.ArrayList;


public class Integration {



    private static float trapezoid(IFunction function, float lower, float upper, int n){
        float x, tnm, sum, del;

        if(n ==1){
            return 0.5f * (upper - lower) * (function.evaluateAt(lower) + function.evaluateAt(upper));
        }
        else{
            int i =1,j;
            for(j= 1; j < n-1; j++)
                i <<= 1;

            tnm = i;
            del = (upper - lower) / tnm;
            x = lower + 0.5f * del;
            for(sum = 0, j = 0; j < i; j++, x+=del)
                sum += function.evaluateAt(x);
            return 0.5f * (upper - lower) * sum / tnm;
        }
    }


    public static float integrateSimpson(IFunction function, float lower, float upper, int steps){
        if(upper <= lower) throw new IllegalArgumentException("The upper value must be greater than lower value.");
        if( steps < 1) throw new IllegalArgumentException("Number of steps to integrate must be a positive integer.");

        float div = (upper - lower) / steps;
        float endpoints = function.evaluateAt(lower) + function.evaluateAt(upper);
        float evenpoints = 0;
        float oddpoints = 0;
        for(int i = 0; i < steps; i++){
            float x = lower + i * div;
            if(i % 2 == 0){
                evenpoints += function.evaluateAt(x);
            }
            else{
                oddpoints += function.evaluateAt(x);
            }


        }

        return div * (endpoints + 2 * evenpoints + 4 * oddpoints) / 3;
    }


    /**
     * Calculates the integral of <code>function</code> in the region <i>[lower,upper]</i>, using
     * <i>Romberg integration method.</i><br>
     *     This method will throw <code>IllegalArgumentException</code>s if <code>lower >= upper</code><br>
     *         or <code>steps < 1</code>.
     * @param function real valued integrable function.
     * @param lower lower bound of integral
     * @param upper upper bound of integral
     * @param steps number of steps to calculate
     * @return
     */
    public static float integrateRomberg(IFunction function, float lower, float upper, int steps){
        if(upper <= lower) throw new IllegalArgumentException("The upper value must be greater than lower value.");
        if( steps < 1) throw new IllegalArgumentException("Number of steps to integrate must be a positive integer.");

        float h = upper - lower;
        ArrayList<Float> l1 = new ArrayList<Float>();
        ArrayList<Float> l2= new ArrayList<Float>();

        float r11 = 0.5f * h * (function.evaluateAt(lower) + function.evaluateAt(upper));
        l1.add(r11);

        for(int i = 1; i < steps; i++){

            int max = 1<<(i-1);
            float r21 = 0.5f * l1.get(0);
            for(int k = 0; k < max; k++){
                r21 += 0.5f * h * function.evaluateAt(lower + (k  + 0.5f) * h);
            }
            if(l2.isEmpty())
                l2.add(r21);
            else l2.set(0,r21);

            for(int j = 1;j < i;j++ ){
                float r2j = l2.get(j-1) + (l2.get(j-1) - l1.get(j-1)) / (float)(Math.pow(4,j) - 1);

                if(j < l2.size())
                    l2.set(j, r2j);
                else
                    l2.add(r2j);
            }

            h *= 0.5f;
            for(int j = 0; j < i; j++){
                if(j < l1.size())
                    l1.set(j, l2.get(j));
                else
                    l1.add(l2.get(j));
            }
        }

        return l2.get(l2.size() - 1);
    }


    /**
     * Calculates the integral of the <i>real valued function</i>, <code>func</code>, between the
     * points <code>a</code> and <code>b</code>, using the adaptive quadrature method.
     * @param func real valued function
     * @param a lower bound
     * @param b upper bound
     * @return integral
     */
    public static float integrateAdaptiveQuadrature(IFunction func, float a, float b){
        float avg = (a + b) / 2;
        float ax = func.evaluateAt(a);
        float bx = func.evaluateAt(b);
        float avgx = func.evaluateAt(avg);

        return q(func,a,b,ax,avgx,bx);
    }

    private static float q(IFunction func, float a, float b, float fa, float favg, float fb){

        final float tolerance = 0.001f;
        float diff = b - a;
        float h = diff / 2;
        float avg = (a + b) / 2;
        float favg1 = func.evaluateAt((a + avg) /2);
        float favg2 = func.evaluateAt((b + avg) /2);
        float s1 = (diff / 6) * (fa + 4 * favg + fb);
        float s2 = (h / 6) * (fa + 4 * favg1 + 2*favg + 4 * favg2 + fb);
        if(Math.abs(s1 -s2) < tolerance){
            return s2 + (s2 -s1) / 15;
        }
        else{
            float sp1 = q(func,a,avg,fa,favg1,favg);
            float sp2 = q(func,avg,b,favg,favg2,fb);
            return sp1 + sp2;
        }
    }



}
