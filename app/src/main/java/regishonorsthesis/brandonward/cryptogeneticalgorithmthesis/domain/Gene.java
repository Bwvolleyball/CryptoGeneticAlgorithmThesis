package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by BrandonWard on 9/25/2014.
 */
public class Gene extends DomainAbs {
    private List<Character> gene;

    public Gene() {
        gene = new ArrayList<Character>(26);
    }

    public Gene(char[] genes) {
        gene = new ArrayList<Character>(26);
        for (int i = 0; i < 26; i++) {
            gene.add(genes[(char) i]);
        }
    }

    public Gene(List<Character> nGene) {
        gene = new ArrayList<Character>(26);
        this.gene.addAll(nGene);
    }

    public List getGene() {
        return gene;
    }

    public void setGene(List gene) {
        this.gene = gene;
    }
}
