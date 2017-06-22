package algoritmos;


import java.util.Set;

public class SubSet{
    private Set<Integer> set;
    private Integer t;

    public SubSet(Set<Integer> set, Integer t) {
        this.set = set;
        this.t = t;
    }

    public Set<Integer> getSet(){
        return this.set;
    }

    public Integer getT(){
        return this.t;
    }
}
