package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.business;

/**
 * Created by BrandonWard on 1/22/2015.
 */
public interface IDecryptionMgr extends IMgr {

    public final String CLASS_NAME = "IDecryptionMgr";

    public String decrypt(String encryption);
}
