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


//    public static ArrayList<Float[]> evaluateRKF(IFirstOrderODE ode, float lower, float higher, float initialCondition, float tolerance, int stepMax, int stepMin){
//
//        float v = lower;
//        float u = initialCondition;
//        float step = stepMax;
//        boolean d = true;
//        while(d){
//            float k1 = step * ode.evaluateDerivative(v,u);
//            float k2 = step * ode.evaluateDerivative(v + 0.25f * step, u + 0.25f * k1);
//            float k3 = step * ode.evaluateDerivative(v + 0.375f * step, u + 0.09375f * k1 + 0.28125f * k2);
//            float k4 = step * ode.evaluateDerivative(v + (12f/13f) * step,
//                    u + (1932f / 2197f) * k1 - (7200f / 2197f) * k2 + (7296 / 2197f) * k3);
//            float k5 = step * ode.evaluateDerivative(v + step,
//                    u + (439f / 216f) * k1 - 8 * k2 + (3680f / 513f) * k3 - (845f / 4104f) * k4);
//            float k6 = step * ode.evaluateDerivative(v + 0.25f * step,
//                    u - (8f / 26f) * k1 + 2 * k2 - (3544f / 2565f) * k3 + (1859f / 4104f) * k4 - (11f / 40f) * k5);
//
//            float r = // page 298!
//
//        }
//    }

}
