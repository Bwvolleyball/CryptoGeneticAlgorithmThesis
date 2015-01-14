package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain;

/**
 * Created by BrandonWard on 1/14/2015.
 */
public class Trigraph {

    private Character[] trigraph;
    private int count;

    public Trigraph() {
        setTrigraph(new Character[3]);
        setCount(0);
    }

    public Character[] getTrigraph() {
        return trigraph;
    }

    public void setTrigraph(Character[] trigraph) {
        this.trigraph = trigraph;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
