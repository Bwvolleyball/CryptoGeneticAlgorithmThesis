package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.business;

import java.util.ArrayList;
import java.util.List;

import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Ciphertext;
import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Decryption;
import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Gene;

/**
 * Created by BrandonWard on 10/13/2014.
 */
public class DecryptionManager {//The Manager needs to keep track of the Ciphertext and the Decryption.
    //TODO: The Manager needs fitness criteria, determine some composite fitness score

    private Ciphertext cipher;
    private Decryption decryption;
    private double[] singleLetterFrequencies =
            //  a   b   c   d    e   f   g   h   i   j   k   l   m   n   o   p   q   r   s   t   u   v   w   x   y   z
            {8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0, 0.2, 0.8, 4.0, 2.4, 6.7, 7.5, 1.9, 0.1, 6.0, 6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1};
    private String[] commonDigraphs = {/*TODO: Fill in CommonDigraphs here*/};
    private double[] commonDigraphsFrequencies = {/*TODO: Fill in matching frequencies here*/};
    private String[] commonTrigraphs = {/*TODO: Fill in CommonTrigraphs here*/};
    private double[] commonTrigraphsFrequencies = {/*TODO: Fill in matching frequencies here*/};
    private String[] commonWordStarts = {/*TODO: Fill in CommonWordStarts here*/};
    private double[] commonWordStartsFrequencies = {/*TODO: Fill in matching frequencies here*/};
    private String[] commonWordEnds = {/*TODO: Fill in CommonWordEnds here*/};
    private double[] commonWordEndsFrequencies = {/*TODO: Fill in matching frequencies here*/};

    public DecryptionManager(Ciphertext cipher) {
        this.cipher = cipher;
        List<Character> list = new ArrayList<Character>(26);
        for (int i = 97; i <= 122; i++) {
            list.add((char) i);
        }
        Gene gene = new Gene(list);
        decryption.setGene(gene);
    }

    public void nextDecryption() {

    }

}
