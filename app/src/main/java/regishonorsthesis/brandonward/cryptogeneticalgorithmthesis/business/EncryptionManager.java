package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Encryption;

/**
 * Created by BrandonWard on 10/28/2014.
 */
public class EncryptionManager {

    private Encryption encryption;

    public EncryptionManager() {
        encryption = new Encryption();
    }

    public EncryptionManager(String msg) {
        encryption.setMsg(msg);
    }

    public String Encrypt(String msg) {
        return CreateEncryption(msg);
    }

    public String Encrypt(Encryption encryption) {
        return CreateEncryption(encryption.getMsg());
    }

    private String CreateEncryption(String msg) {
        msg = msg.toLowerCase();
        char[] encryptGene = RandomEncryption();
        char[] charMsg = msg.toCharArray();
        for (int i = 0; i < charMsg.length; i++) {
            if ((int) charMsg[i] - (int) 'a' >= 0 && (int) charMsg[i] - (int) 'a' < 26) {
                charMsg[i] = encryptGene[(((int) ((char) charMsg[i])) - (int) ((char) 'a'))];//Change each part of charMsg to the shuffled letter in encryptGene.
            }
        }
        String encryption = String.copyValueOf(charMsg);
        this.encryption.setEncryption(encryption);
        return encryption;
    }

    private char[] RandomEncryption() {
        List<Character> encryptGene = new ArrayList<Character>(26);
        for (int i = (int) 'a'; i <= (int) 'z'; i++) {
            encryptGene.add((char) i);
        }
        Collections.shuffle(encryptGene);
        Object[] objGene = encryptGene.toArray();
        char[] charGene = new char[objGene.length];
        for (int i = 0; i < objGene.length; i++) {
            charGene[i] = (Character) objGene[i];
        }
        return charGene;
    }

}
