package combinatorics;


public class BellNumbers {

    /**
     * Generates the Bell Number of the given index, where B(1) is 1.
     * This is recursive.
     * @param index
     * @return
     */
    public static long generateBellNumber(int index){
        if(index < 0)
            throw new IllegalArgumentException("Argument must be non-negative");

        if(index <= 1)
            return 1;

        else{
            long sum = 0;
            for(int i = 0; i < index; i++){
                long prevBellNum = generateBellNumber(i);
                long binomialCoeff = Binomial.coefficient(index - 1, i);
                sum += binomialCoeff * prevBellNum;
                if(sum < 0) throw new RuntimeException("Long integer overflow exception");
            }
            return sum;
        }
    }
}
