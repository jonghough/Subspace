package numerics;


public class Differentiation {

    /* UNDER CONSTRUCTION */
    public static final float EPSILON = 0.0000001f;

    /**
     * Numerical differentiation of a function of a single real variable,
     * using <i>3-point Midpoint formula</i>. THe function is assumed to
     * be differentiable.
     * @param function
     * @param xValue
     * @param epsilon
     * @return
     */
    public static float diff3(IFunction function, float xValue, float epsilon){
        if(epsilon < EPSILON)
            epsilon = EPSILON;

        float coeff = 0.5f / epsilon;
        float v1 = function.evaluateAt(xValue + epsilon);
        float v2 = function.evaluateAt(xValue - epsilon);

        return coeff * (v1 - v2);
    }

    /**
     * Numerical differentiation of a function of a single real variable,
     * using <i>5-point Midpoint formula</i>. THe function is assumed to
     * be differentiable.
     * @param function
     * @param xValue
     * @param epsilon
     * @return
     */
    public static float diff5(IFunction function, float xValue, float epsilon){
        if(epsilon < EPSILON)
            epsilon = EPSILON;

        float coeff = 1.0f / ( 12.0f * epsilon);
        float v1 = function.evaluateAt(xValue - 2 * epsilon);
        float v2 = function.evaluateAt(xValue - epsilon);
        float v3 = function.evaluateAt(xValue + epsilon);
        float v4 = function.evaluateAt(xValue + 2 * epsilon);

        return coeff * (v1 - 8 * v2 + 8 * v3 - v4);
    }
}
