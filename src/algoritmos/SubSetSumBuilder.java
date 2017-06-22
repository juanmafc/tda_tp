package algoritmos;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SubSetSumBuilder{

    public SubSet build(Integer n){

        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        Integer suma = 0;
        while(set.size() != n){
            Integer x = random.nextInt(10000);
            suma += x;
            set.add(x);
        }
        Integer t = random.nextInt(suma);

        return new SubSet(set, t);
    }

}
