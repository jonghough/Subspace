package linear;


public class DotProduct implements InnerProduct{


    @Override
    public double product(Vector a, Vector b) {
        double total = 0;
        for(int i = 0; i < a.flatten().length; i++){
            total += a.at(i,0) * b.at(i,0);
        }
        return total;
    }
}
