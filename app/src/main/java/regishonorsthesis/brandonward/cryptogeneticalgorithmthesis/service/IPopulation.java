package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.service;

import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Ciphertext;
import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Decryption;
import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Population;

/**
 * Created by BrandonWard on 9/25/2014.
 */
public interface IPopulation {

    public Decryption addToPopulation(Decryption decryption);

    public Decryption getBestDecryption();

}
