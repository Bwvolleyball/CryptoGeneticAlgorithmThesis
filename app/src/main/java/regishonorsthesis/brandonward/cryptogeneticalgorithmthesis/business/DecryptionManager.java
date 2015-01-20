package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Ciphertext;
import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Decryption;
import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Gene;
import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Position;
import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Trigraph;

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
        initializeGene();
    }

    public DecryptionManager() {
        initializeGene();
    }

    public DecryptionManager(String ciphertext) {
        if (cipher == null) {
            cipher = new Ciphertext();
        }
        cipher.setCiphertext(ciphertext);
        splitCipher = ciphertext.toCharArray();
        initializeGene();
    }

    private void initializeGene() {
        List<Character> list = new ArrayList<Character>(26);
        for (int i = (int) 'a'; i <= (int) 'z'; i++) {
            list.add((char) i);
        }
        //Collections.shuffle(list);
        Gene gene = new Gene(list);
        decryption = new Decryption(gene);
    }

    public String decrypt(String encryption) {
        encryption = frequencyGuess(encryption);
        //TODO: Apply gene to decryption
        //encryption = executeDecryption(encryption);
        return encryption;
    }

    private String frequencyGuess(String encryption) {
        //List<Trigraph> triCount = trigraphFrequencies(encryption);
        triAdjust(trigraphFrequencies(encryption), "the".toCharArray(), 0);
        encryption = executeDecryption(encryption);
        triAdjust(trigraphFrequencies(encryption), "and".toCharArray(), 1);
        encryption = executeDecryption(encryption);
        triAdjust(trigraphFrequencies(encryption), "ing".toCharArray(), 2);
        encryption = executeDecryption(encryption);
        triAdjust(trigraphFrequencies(encryption), "her".toCharArray(), 3);
        encryption = executeDecryption(encryption);
        return encryption;
    }

    private List<Trigraph> trigraphFrequencies(String encryption) {//This function will create a list of all trigraphs with frequencies.
        char[] message = encryption.toCharArray();
        List<Trigraph> triCount = new LinkedList<Trigraph>();
        for (int i = 0; i < (message.length - 3); i++) {
            if (!(message[i] == ' ' || message[i + 1] == ' ' || message[i + 2] == ' ')) {
                Trigraph trigraphn = new Trigraph();
                Character[] temp = new Character[3];
                temp[0] = message[i];
                temp[1] = message[i + 1];
                temp[2] = message[i + 2];
                trigraphn.setTrigraph(temp);
                if (!(triCount.contains(trigraphn))) {
                    Trigraph trigraph = new Trigraph();
                    trigraph.setTrigraph(temp);
                    trigraph.setCount(1);
                    triCount.add(trigraph);
                } else {//trigraphs.contains(temp)
                    Iterator<Trigraph> iter = triCount.iterator();
                    while (iter.hasNext()) {
                        Trigraph t = iter.next();
                        if (t.equals(trigraphn)) {
                            int count = t.getCount() + 1;
                            t.setCount(count);
                            break;
                        }
                    }
                }
            }
        }
        Collections.sort(triCount, new Comparator<Trigraph>() {
            @Override
            public int compare(Trigraph lhs, Trigraph rhs) {
                if (lhs.getCount() == rhs.getCount()) {
                    return 0;
                } else if (lhs.getCount() > rhs.getCount()) {
                    return -1;
                } //else lhs.getCount() < rhs.getCount()
                return 1;//TODO: I may need to flip this accordingly.
            }
        });//After this for loop, the letters passed in should be the char[] with the highest combo
        return triCount;
    }

    public void triAdjust(List<Trigraph> trigraphs, char[] trigraph, int index) {
        //TODO: Make this load the trigraph from list, switch it with passed trigraph, and lock in switched ones.
        if (trigraphs.size() <= index) {
            return;//Meaning that the index would be out of the index, and therefore not allowed, so do nothing.
            //This is a rare exception, but just in case.
        }
        Character[] switching = trigraphs.get(index).getTrigraph();
        List<Character> gene = decryption.getGene().getGene();
        for (int i = 0; i < switching.length; i++) {
            int indexLetters = gene.indexOf(trigraph[i]);
            int indexSwitching = gene.indexOf(switching[i]);
            if (!(locked[indexSwitching] == false && locked[indexLetters] == true)) {
                char lettersChar = gene.get(indexLetters);
                char switchingChar = gene.get(indexSwitching);
                gene.set(indexLetters, switchingChar);
                gene.set(indexSwitching, lettersChar);
                locked[indexLetters] = true;
            } else {
                char temp = gene.get(trigraph[i] - 'a');
                gene.set(trigraph[i] - 'a', switching[i]);
                gene.set(indexSwitching, temp);
                locked[trigraph[i] - 'a'] = true;
            }
        }
        decryption.getGene().setGene(gene);
    }

    private String executeDecryption(String encryption) {
        List<Character> gene = decryption.getGene().getGene();
        char[] toDecrypt = encryption.toCharArray();
        for (int i = 0; i < toDecrypt.length; i++) {
            if (toDecrypt[i] >= 'a' && toDecrypt[i] <= 'z') {
                toDecrypt[i] = (char) ('a' + (char) gene.indexOf(toDecrypt[i]));
            }
        }
        initializeGene();//TODO: This could mess it up more?
        return String.copyValueOf(toDecrypt);
    }

    private void nextDecryption() {
        double prevFitness = decryption.getFitness();//Save previous fitness and decryption
        Decryption prevDecrypt = decryption;//Test after changing decryption to see if new iteration should be kept
        decryption = newDecryption();
        calculateFitness();
        //TODO: calculate decryption
        //TODO: calculate which positions need to be locked
    }

    private Decryption newDecryption() {
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

    private void calculateFitness() {
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
        return tripleFit;
    }

    private double calculateStartFit() {
        double[] frequencyCount = countStartLetter();
        double startFit = 0;
        for (int i = 0; i < frequencyCount.length; i++) {
            startFit += frequencyCount[i] * commonWordStartsFrequencies[i];
        }
        return startFit;
    }

    private double calculateEndFit() {
        double[] frequencyCount = countEndLetter();
        double endFit = 0;
        for (int i = 0; i < frequencyCount.length; i++) {
            endFit += frequencyCount[i] * commonWordEndsFrequencies[i];
        }
        return endFit;
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

    private double[] countStartLetter() {
        double[] frequencyCount = new double[commonWordStarts.length];
        for (int i = 0; i < frequencyCount.length; i++) {
            int k = 0;
            for (int j = 0; j < splitCipher.length; j++) {
                if (splitCipher[j] == ' ' && commonWordStarts[i].equals(splitCipher[j + 1])) {
                    k++;
                }
            }
            frequencyCount[i] = k;
        }
        return frequencyCount;
    }

    private double[] countEndLetter() {
        double[] frequencyCount = new double[commonWordEnds.length];
        for (int i = 0; i < frequencyCount.length; i++) {
            int k = 0;
            for (int j = 0; j < splitCipher.length; j++) {
                if (splitCipher[j] == ' ' && commonWordEnds[i].equals(splitCipher[j + 1])) {
                    k++;
                }
            }
            frequencyCount[i] = k;
        }
        return frequencyCount;
    }
}
