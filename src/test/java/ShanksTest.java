
import factoring.Shanks;
import org.junit.Test;
import utils.RootFinder;
import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ShanksTest {

    @Test
    public void test1(){
        BigInteger N = new BigInteger("30325000");
            Shanks sh = new Shanks(N, 8);
        BigInteger s = sh.factor();
        System.out.println("Factor of 30325000 Shanks Algo is "+s);

    }

    @Test
    public void test2(){
        BigInteger N = new BigInteger("3546413");
        Shanks sh = new Shanks(N, 8);
        BigInteger s = sh.factor();
        System.out.println("Factor of 3546413 using Shanks Algo is "+s);

    }


    @Test
    public void test3(){
        BigInteger N = new BigInteger("11675231167");
        Shanks sh = new Shanks(N, 8);
        BigInteger s = sh.factor();
        System.out.println("Factor of 11675231167 using Shanks Algo is "+s);

    }
}
