package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.business;

import java.util.ArrayList;
import java.util.List;

import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Ciphertext;
import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Decryption;
import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Gene;
import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Position;

/**
 * Created by BrandonWard on 10/13/2014.
 */
public class DecryptionManager {//The Manager needs to keep track of the Ciphertext and the Decryption.
    //TODO: The Manager needs fitness criteria, determine some composite fitness score

    private Ciphertext cipher;
    private char[] splitCipher;
    private Decryption decryption;
    private boolean[] locked = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};//26 falses, because at the start, all letters can be swapped.
    private double[] singleLetterFrequencies =
            //a    b    c    d     e    f    g    h    i    j    k    l    m    n    o    p    q    r    s    t    u    v    w    x    y    z
            {8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0, 0.2, 0.8, 4.0, 2.4, 6.7, 7.5, 1.9, 0.1, 6.0, 6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1};
    private String[] commonDigraphs = {"th", "he", "in", "er", "an", "re", "nd", "at", "on", "nt", "ha", "es", "st", "en", "ed", "to", "it", "ou", "ea", "hi", "is", "or", "ti", "as", "te", "et", "ng", "of", "al", "de", "se", "le", "sa", "si", "ar", "ve", "ra", "ld", "ur"};
    private double[] commonDigraphsFrequencies = {1.52, 1.28, 0.94, 0.94, 0.82, 0.68, 0.63, 0.59, 0.57, 0.56, 0.56, 0.56, 0.55, 0.55, 0.53, 0.52, 0.5, 0.5, 0.47, 0.46, 0.46, 0.43, 0.34, 0.33, 0.27, 0.19, 0.18, 0.16, 0.09, 0.09, 0.08, 0.08, 0.06, 0.05, 0.04, 0.04, 0.04, 0.02, 0.02};
    private String[] commonTrigraphs = {"the", "and", "ing", "her", "hat", "his", "tha", "ere", "for", "ent", "ion", "ter", "was", "you", "ith", "ver", "all", "wit", "thi", "tio"};
    private double[] commonTrigraphsFrequencies = {3.508, 1.594, 1.147, 0.822, 0.651, 0.597, 0.594, 0.561, 0.555, 0.531, 0.506, 0.461, 0.460, 0.437, 0.422, 0.397, 0.395, 0.378};
    private String[] commonWordStarts = {"t", "a", "i", "s", "o", "c", "m", "f", "p", "w"};
    private double[] commonWordStartsFrequencies = {0.159, 0.155, 0.082, 0.076, 0.071, 0.06, 0.043, 0.041, 0.04, 0.038};
    private String[] commonWordEnds = {"e", "s", "d", "t", "n", "y", "r", "o", "l", "f"};
    private double[] commonWordEndsFrequencies = {0.192, 0.144, 0.092, 0.086, 0.079, 0.073, 0.069, 0.047, 0.046, 0.041};

    public DecryptionManager(Ciphertext cipher) {
        this.cipher = cipher;
        splitCipher = cipher.getCiphertext().toCharArray();
        List<Character> list = new ArrayList<Character>(26);
        for (int i = 97; i <= 122; i++) {
            list.add((char) i);
        }
        Gene gene = new Gene(list);
        decryption.setGene(gene);
    }

    public void nextDecryption() {
        double prevFitness = decryption.getFitness();//Save previous fitness and decryption
        Decryption prevDecrypt = decryption;//Test after changing decryption to see if new iteration should be kept
        decryption = newDecryption();
        calculateFitness();
        //TODO: calculate decryption
        //TODO: calculate which positions need to be locked
    }

    public Decryption newDecryption() {
        //Split into arrays of kept and unkept bits
        List<Character> shuffle = decryption.getGene().getGene();
        int j = 0;//track index of lock
        int k = 0;//track index of unlock
        List<Position> lock = new ArrayList<Position>();
        List<Position> unlock = new ArrayList<Position>();
        for (int i = 0; i < locked.length; i++) {
            Position position = new Position();
            position.setPosition(i);
            position.setCharacter(shuffle.get(i));
            if (locked[i]) {
                lock.add(position);
            } else {
                unlock.add(position);
            }
            //TODO: Shuffle unlocked positions
            //TODO: put unlocked positions back into list where locked == false
            //TODO: put locked positions back into list where locked == true and position == position
            //TODO: assign this Gene to a new Decryption and return the Decryption to have fitness calculated.
        }
        return null;
    }

    public void calculateFitness() {
        decryption.setFitness(calculateSingleFit()
                + calculateDigraphFit()
                + calculateTrigraphFit()
                + calculateStartFit()
                + calculateEndFit());
    }

    private double calculateSingleFit() {
        double[] frequencyCount = countSingleLetter();
        double singleFit = 0;
        for (int i = 0; i < frequencyCount.length; i++) {
            singleFit += frequencyCount[i] * singleLetterFrequencies[i];
        }
        return singleFit;
    }

    private double calculateDigraphFit() {
        double[] frequencyCount = countDoubleLetter();
        double doubleFit = 0;
        for (int i = 0; i < frequencyCount.length; i++) {
            doubleFit += frequencyCount[i] * commonDigraphsFrequencies[i];
        }
        return doubleFit;
    }

    private double calculateTrigraphFit() {
        double[] frequencyCount = countTripleLetter();
        double tripleFit = 0;
        for (int i = 0; i < frequencyCount.length; i++) {
            tripleFit += frequencyCount[i] * commonTrigraphsFrequencies[i];
        }
        //TODO: Calculate Trigraph Fitness
        return 0;
    }

    private double calculateStartFit() {
        //TODO: Calculate Start Fitness
        return 0;
    }

    private double calculateEndFit() {
        //TODO: Calculate End Fitness
        return 0;
    }

    private double[] countSingleLetter() {
        double[] frequencyCount = new double[26];
        for (int i = 0; i < frequencyCount.length; i++) {
            int k = 0;
            for (int j = 0; j < splitCipher.length; j++) {
                if (i + (int) 'a' == splitCipher[j]) {
                    k++;
                }
            }
            frequencyCount[i] = k;
        }
        return frequencyCount;
    }

    private double[] countDoubleLetter() {
        double[] frequencyCount = new double[commonDigraphs.length];
        for (int i = 0; i < frequencyCount.length; i++) {
            int k = 0;
            for (int j = 0; j < splitCipher.length; j++) {
                if (commonDigraphs[i].equals(String.valueOf(splitCipher, j, 2))) {
                    k++;
                }
            }
            frequencyCount[i] = k;
        }
        return frequencyCount;
    }

    private double[] countTripleLetter() {
        double[] frequencyCount = new double[commonTrigraphs.length];
        for (int i = 0; i < frequencyCount.length; i++) {
            int k = 0;
            for (int j = 0; j < splitCipher.length; j++) {
                if (commonTrigraphs[i].equals(String.valueOf(splitCipher, j, 3))) {
                    k++;
                }
            }
            frequencyCount[i] = k;
        }
        return frequencyCount;
    }
}
