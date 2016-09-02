package numerics;

/**
 * First Order ODE of the form
 * <br>
 * <i>y' = f(y,t)</i>
 * <br>
 * with boundary conditions<br>
 * <i>y(t0) = y0</i>
 */
public interface IFirstOrderODE {

    float t0();

    float y0();

    float evaluateDerivative(float t, float y);
}
