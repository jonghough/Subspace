import combinatorics.SteinerTripleSystem;
import static org.junit.Assert.*;
import org.junit.Test;
import transcendance.Exp;

import java.math.BigDecimal;

public class SteinerTripleSystemTest {

    @Test
    public void test1(){
        SteinerTripleSystem sts7 = new SteinerTripleSystem(7);
        System.out.println("STEINER TRIPLE SYSTEM");
        for(SteinerTripleSystem.Triple t : sts7.getElements()){
            System.out.println("TRIPLE:");
            System.out.print("\n"+t.toString()+"\n");

        }
        System.out.println("Exp >>>>>>>>>>>>>>>>>>>  "+ Exp.BigExp(new BigDecimal("2.71828"), new BigDecimal("3.75")));
    }
}
