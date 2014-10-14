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

    public DecryptionManager(Ciphertext cipher) {
        this.cipher = cipher;
        List<Character> list = new ArrayList<Character>(26);
        for (int i = 97; i <= 122; i++) {
            list.add((char) i);
        }
        Gene gene = new Gene(list);
        decryption.setGene(gene);
    }

}
