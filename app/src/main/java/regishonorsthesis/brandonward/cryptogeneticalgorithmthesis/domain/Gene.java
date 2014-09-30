package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by BrandonWard on 9/25/2014.
 */
public class Gene {
    private Set<Character> gene;

    public Gene() {
        gene = new LinkedHashSet<Character>(26);
    }

    public Gene(char[] genes) {
        gene = new LinkedHashSet<Character>(26);
        for (int i = 0; i < 26; i++) {
            gene.add(genes[i]);
        }
    }

    public Gene(List<Character> gene) {
        this.gene.addAll(gene);
    }

    public Set getGene() {
        return gene;
    }

    public void setGene(Set gene) {
        this.gene = gene;
    }
}
