package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.service;

import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Decryption;

/**
 * Created by BrandonWard on 10/13/2014.
 */
public class DecryptionSvcImpl implements IDecryption {

    private Decryption decryption;

    @Override
    public Decryption getDecryption() {

        return decryption;
    }
}
