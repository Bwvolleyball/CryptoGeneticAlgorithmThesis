package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Decryption;

/**
 * Created by BrandonWard on 9/25/2014.
 */
public class Population {
    private Set<Decryption> population;//The set of all decryptions is the Population

    public Population() {
        population = new LinkedHashSet<Decryption>();
    }

    public Set<Decryption> getPopulation() {
        return population;
    }

    public void setPopulation(Set<Decryption> population) {
        this.population = population;
    }

    public Decryption add(Decryption decryption) {
        population.add(decryption);
        return decryption;
    }
}
