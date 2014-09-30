package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.service;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Decryption;
import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Gene;
import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Population;

/**
 * Created by BrandonWard on 9/30/2014.
 */
public class IPopulationImpl implements IPopulation {

    private Population population;

    public IPopulationImpl() {//Starting with 10 genes, this can change if it is found to be better another way
        population = new Population();
        //Create Initial Genes
        for (int i = 0; i < 10; i++) {
            List<Character> initialGene = new LinkedList<Character>();
            for (int j = 97; j <= 122; j++) {
                initialGene.add((char) j);//create a list with each letter of the alphabet
            }
            Collections.shuffle(initialGene);//shuffle the list randomly
            Gene gene = new Gene(initialGene);//convert the list to a set
            Decryption decryption = new Decryption(gene);//plug into a decryption
            population.add(decryption);//add to the population
        }

    }

    @Override
    public Decryption addToPopulation(Decryption decryption) {
        population.add(decryption);//add the decryption to the population
        return decryption;
    }

    @Override
    public Decryption getBestDecryption() {
        Set<Decryption> decryptions = population.getPopulation();//retrieve the decryptions
        Iterator<Decryption> iter = decryptions.iterator();//iterate through them
        Decryption bestDecryption = null;
        Decryption currentDecryption;
        while (iter.hasNext()) {
            currentDecryption = iter.next();
            if (bestDecryption == null || bestDecryption.getFitness() < currentDecryption.getFitness()) {//select the decryption whenever the best one is null, or has a lower fitness
                bestDecryption = currentDecryption;
            }
        }
        return bestDecryption;
    }
}
