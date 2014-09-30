package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain;

/**
 * Created by BrandonWard on 9/25/2014.
 */
public class Decryption {
    private String decryption; //decryption string, calculated from Gene
    private Gene gene;//each decryption needs a gene
    private double fitness; //fitness double, calculated from decryption
    private int count; //count, might toss this, supposed to be the number of Genes of this type

    public Decryption(Gene gene) {
        this.gene = gene;
    }

    public String getDecryption() {
        return decryption;
    }

    public void setDecryption(String decryption) {
        this.decryption = decryption;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }
}
