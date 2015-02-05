package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain;

/**
 * Created by BrandonWard on 2/3/2015.
 */
public class Quadragram {


    private Character[] quadragram;
    private int count;

    public Quadragram() {
        setQuadragram(new Character[4]);
        setCount(0);
    }

    public Character[] getQuadragram() {
        return quadragram;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setQuadragram(Character[] quadragram) {
        this.quadragram = quadragram;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Trigraph)) {
            return false;
        }
        Quadragram quadragram = (Quadragram) obj;
        if (this.quadragram[0] != quadragram.quadragram[0]) {
            return false;
        }
        if (this.quadragram[1] != quadragram.quadragram[1]) {
            return false;
        }
        if (this.quadragram[2] != quadragram.quadragram[2]) {
            return false;
        }
        if (this.quadragram[3] != quadragram.quadragram[3]) {
            return false;
        }
        return true;
    }
}
