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
    private Decryption decryption;
    private boolean[] locked = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};//26 falses, because at the start, all letters can be swapped.
    private double[] singleLetterFrequencies =
            //a    b    c    d     e    f    g    h    i    j    k    l    m    n    o    p    q    r    s    t    u    v    w    x    y    z
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
        return 0;
    }

    private double calculateDigraphFit() {
        return 0;
    }

    private double calculateTrigraphFit() {
        return 0;
    }

    private double calculateStartFit() {
        return 0;
    }

    private double calculateEndFit() {
        return 0;
    }

}
