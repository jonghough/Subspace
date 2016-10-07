import combinatorics.Binomial;
import org.junit.Test;

public class BinomialTest {

    @Test
    public void test1(){
        long b = Binomial.coefficient(5,2);
        System.out.println("(5 2) ==================> "+b);

        long b2 = Binomial.coefficient(10,6);
        System.out.println("(10 6) ==================> "+b2);

        long b3 = Binomial.coefficient(15,7);
        System.out.println("(15 7) ==================> "+b3);


        long b4 = Binomial.coefficient(10,10);
        System.out.println("(10 10) ==================> "+b4);


        long b5 = Binomial.coefficient(12,1);
        System.out.println("(12 1) ==================> "+b5);
    }
}
