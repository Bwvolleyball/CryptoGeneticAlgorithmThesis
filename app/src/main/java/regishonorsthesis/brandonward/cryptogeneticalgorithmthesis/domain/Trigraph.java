package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain;

/**
 * Created by BrandonWard on 1/14/2015.
 */
public class Trigraph {

    private Character[] trigraph;
    private int count;

    public Trigraph() {
        trigraph = new Character[3];
        setCount(0);
    }

    public Character[] getTrigraph() {
        return trigraph;
    }

    public void setTrigraph(String trigraph) {
        char[] temp = trigraph.toCharArray();
        this.trigraph[0] = temp[0];
        this.trigraph[1] = temp[1];
        this.trigraph[2] = temp[2];
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
