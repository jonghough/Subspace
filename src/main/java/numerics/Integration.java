package numerics;


/* UNDER CONSTRUCTION */
public class Integration {


    public static float integrateSimpson(IFunction function, float lower, float upper, int parts){
        if(upper <= lower) throw new IllegalArgumentException("The upper value must be greater than lower value.");
        if( parts < 1) throw new IllegalArgumentException("Number of parts to integrate must be a positive integer.");

        float div = (upper - lower) / parts;
        float endpoints = function.evaluateAt(lower) + function.evaluateAt(upper);
        float evenpoints = 0;
        float oddpoints = 0;
        for(int i = 0; i < parts; i++){
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


//    public static float integrateAdaptiveQuadrature(IFunction function, float lower, float upper, int levels, float tolerance){
//        float out = 0;
//        int i = 1;
//        float tol = 10 * tolerance;
//        //TODO
//        return 0;
//    }

}
