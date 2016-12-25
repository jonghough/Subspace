package numerics;

import java.util.ArrayList;

/**
 * Numerical solution to Ordinary Differential Equations
 */
public class ODE {
/* UNDER CONSTRUCTION */

    /**
     *
     * @param ode
     * @param lower
     * @param higher
     * @param parts
     * @return
     */
    public static ArrayList<Float[]> evaluateRungeKutta(IFirstOrderODE ode, float lower, float higher, float parts){

        float h = (higher - lower) / parts;
        float t = ode.t0();
        float w = ode.y0();

        ArrayList<Float[]> results = new ArrayList<>();
        results.add(new Float[]{t,w});

        for(int i = 1; i <= parts; i++){
            float k1 = h * ode.evaluateDerivative(t, w);
            float k2 = h * ode.evaluateDerivative(t + h / 2, w + k1 / 2);
            float k3 = h * ode.evaluateDerivative(t + h / 2, w + k2 / 2);
            float k4 = h * ode.evaluateDerivative(t + h, w + k3);

            w += (k1 + 2 * k2+ 2 * k3 + k4) / 6;
            t = ode.t0() + i * h;
            results.add(new Float[]{t,w});
        }
        return results;
    }


    public static float evaluateRKF(IFirstOrderODE ode, float lower, float higher, float initialCondition, float tolerance, float stepMax, float stepMin){

        float t = lower;
        float w = initialCondition;
        float h = stepMax;
        boolean d = true;
        int count = 1000;
        while(d && count-->0){
            float k1 = h * ode.evaluateDerivative(t,w);
            float k2 = h * ode.evaluateDerivative(t + 0.25f * h, w + 0.25f * k1);
            float k3 = h * ode.evaluateDerivative(t + 0.375f * h, w + 0.09375f * k1 + 0.28125f * k2);
            float k4 = h * ode.evaluateDerivative(t + (12f/13f) * h,
                    w + (1932f / 2197f) * k1 - (7200f / 2197f) * k2 + (7296 / 2197f) * k3);
            float k5 = h * ode.evaluateDerivative(t + h,
                    w + (439f / 216f) * k1 - 8 * k2 + (3680f / 513f) * k3 - (845f / 4104f) * k4);
            float k6 = h * ode.evaluateDerivative(t + 0.25f * h,
                    w - (8f / 26f) * k1 + 2 * k2 - (3544f / 2565f) * k3 + (1859f / 4104f) * k4 - (11f / 40f) * k5);

            float r = (1.0f / h) * Math.abs(k1 / 360 - (128f / 4275f) * k3 - (2197 / 75240) * k4 + (1f/50f) * k5 + (2f / 55f) * k6);
            if(r < tolerance){
                t += h;
                w += (25f / 216f) * k1 + (1408f / 2565f) * k3 + (2197f / 4104f) * k4 - (1f / 5f) * k5;
            }
            float delta = 0.84f * (float) Math.pow(tolerance / r, 0.25);
            if(delta < 0.1f) h *= 0.1f;
            else if(delta > 4){
                h *= 4;
                h = delta * h;
            }
            if(t >= higher) {
                d = false;
                System.out.println("greater than higher ending");
            }
            else if(t + h > higher){
                h = higher - t;
            }
            else if( h < stepMin){

                System.out.println("less than minimum h ending: "+h);
                d = false;
                return 0;
            }
        }
        throw new RuntimeException("fail");
    }

}
