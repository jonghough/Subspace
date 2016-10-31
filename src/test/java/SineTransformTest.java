import numerics.FFT;
import org.junit.Test;

public class SineTransformTest {

    @Test
    public void test1(){
        double[] arr = {2.3,1,3,0.5,3,2.1,0,-3};
        FFT.sinft(arr);
        FFT.sinft(arr);
        for(double d : arr){
            System.out.println("sin transform >> "+d);
        }
    }
}
