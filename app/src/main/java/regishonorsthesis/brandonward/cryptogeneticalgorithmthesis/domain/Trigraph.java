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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTrigraph(Character[] trigraph) {
        this.trigraph = trigraph;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Trigraph)) {
            return false;
        }
        Trigraph trigraph = (Trigraph) obj;
        if (this.trigraph[0] != trigraph.trigraph[0]) {
            return false;
        }
        if (this.trigraph[1] != trigraph.trigraph[1]) {
            return false;
        }
        if (this.trigraph[2] != trigraph.trigraph[2]) {
            return false;
        }
        return true;
    }
}
