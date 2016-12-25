
import numerics.IFirstOrderODE;
import numerics.ODE;
import org.junit.Test;

import java.util.ArrayList;

public class ODETest {

    @Test
    public void test1(){

        IFirstOrderODE ode = new IFirstOrderODE() {
            @Override
            public float t0() {
                return 0;
            }

            @Override
            public float y0() {
                return 0.5f;
            }

            @Override
            public float evaluateDerivative(float t, float y) {
                return y - t * t + 1;
            }

        };

        ArrayList<Float[]> res = ODE.evaluateRungeKutta(ode, 0, 2, 10);

        for(Float[] pair : res){
            System.out.println(pair[0]+"\t\t" + pair[1]);
        }
    }


    @Test
    public void test2(){

        IFirstOrderODE ode = new IFirstOrderODE() {
            @Override
            public float t0() {
                return 0;
            }

            @Override
            public float y0() {
                return 0.5f;
            }

            @Override
            public float evaluateDerivative(float t, float y) {
                return  y - t * t + 1;
            }

        };

        float res = ODE.evaluateRKF(ode, 0, 2, 2, 0.00001f, 0.25f, 0.01f);

        System.out.println("result = "+res);

    }
}
