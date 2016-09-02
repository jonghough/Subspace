import numerics.IFunction;
import numerics.Integration;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntegrationTest {

    @Test
    public void test1(){
        IFunction sin = new IFunction(){

            @Override
            public float evaluateAt(float x) {
                return (float)Math.sin(x);
            }
        };

        float v = Integration.integrateSimpson(sin, 0, (float)Math.PI / 4, 6);
        System.out.println("Simpson integration of sin(x) is "+v);
        assertTrue("Integration of sin(x) between 0 and pi / 4, test", Math.abs(v - (1 - Math.sqrt(2) / 2)) < 0.01f);
    }

    @Test
    public void test2(){
        IFunction xsquared = new IFunction(){

            @Override
            public float evaluateAt(float x) {
                return x * x;
            }
        };

        float v = Integration.integrateSimpson(xsquared, -10, 10, 2000);
        // [10^3/3] - [-10^3/3] = 1000/3 + 1000/3 = 2 * 1000/3
        assertTrue("Integration of x^2 between -10 and 10, test", Math.abs(v - 666.66667f) < 1.0f);
    }
}
