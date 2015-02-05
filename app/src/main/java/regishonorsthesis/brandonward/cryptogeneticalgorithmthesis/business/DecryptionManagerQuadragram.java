package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.business;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.R;
import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.domain.Quadragram;

/**
 * Created by BrandonWard on 1/29/2015.
 */
public class DecryptionManagerQuadragram implements IDecryptionMgr {

    private Map<Integer, Integer> quadragrams;
    private Context context;
    private List<Character> parentKey;
    private double parentScore;
    private String encryption;
    private double maxScore = -99e9;
    private List<Character> maxKey;

    public DecryptionManagerQuadragram(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        quadragrams = new HashMap<Integer, Integer>();
        LoadFile(R.raw.englishquadgrams);
    }

    private void LoadFile(int resId) {
        // The InputStream opens the resourceId and sends it to the buffer
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String readLine = null;

        try {
            // While the BufferedReader readLine is not null
            while ((readLine = br.readLine()) != null) {
                readLine = readLine.toUpperCase();
                String[] result = readLine.split(" ");
                int key = result[0].hashCode();
                int score = Integer.parseInt(result[1]);
                quadragrams.put(key, score);
                //Log.d("TEXT", readLine);
            }

            // Close the InputStream and BufferedReader
            is.close();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String decrypt(String encryption) {
        encryption = encryption.toUpperCase();
        char[] temp = encryption.toCharArray();
        char[] midTemp = new char[temp.length];
        int j = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] >= 'A' && temp[i] <= 'Z') {
                midTemp[j] = temp[i];
                j++;
            }
        }
        char[] endTemp = new char[j];
        for (int k = 0; k < j; k++) {
            endTemp[k] = midTemp[k];
        }
        encryption = String.copyValueOf(endTemp);
        this.encryption = encryption;
        List<Character> parentKey = new ArrayList<Character>(26);//Generate parentKey and shuffle it before setting it to the class
        for (int i = 'A'; i <= 'Z'; i++) {
            parentKey.add((char) i);
        }
        Collections.shuffle(parentKey);
        this.parentKey = parentKey;
        String deciphered;
        int iterations = 0;
        Random r = new Random();
        while (iterations < 10) {//Starting with 10 iterations because otherwise it really bogs down the system and freezes it for a long time
            Log.i("Decryption in Progress", "Iteration#: " + iterations);
            Toast.makeText(context, "Entering iteration " + iterations, Toast.LENGTH_SHORT).show();
            Collections.shuffle(this.parentKey);
            deciphered = decipher(parentKey);
            parentScore = score(quadragramFrequencies(deciphered));
            if (parentScore > maxScore) {
                maxScore = parentScore;
                maxKey = this.parentKey;
            }
            int count;
            for (count = 0; count < 1000; count++) {
                List<Character> childKey = this.parentKey;
                int a = (r.nextInt(26));
                int b = (r.nextInt(26));
                Character swap;
                swap = childKey.get(a);
                childKey.set(a, childKey.get(b));
                childKey.set(b, swap);
                String childDecipher = decipher(childKey);
                double childScore = score(quadragramFrequencies(childDecipher));
                if (childScore > parentScore) {
                    this.parentKey = childKey;
                    parentScore = childScore;
                    count = 0;
                }
                if (parentScore > maxScore) {
                    maxKey = this.parentKey;
                    maxScore = parentScore;
                }
                //TODO: Add random number generator to flip letters in child key, decipher with child key, determine if better than parent.
            }
            iterations++;
        }
        //TODO: Initial parentKey is set, now create while(true) loop to iterate through the hill climbing algorithm.
        //TODO: IF Key(Quadragram) doesn't exist, assume score of 1(ran out of room for android).
        //TODO: Return the decryption with the maxKey.
        return decipher(maxKey);
    }

    private String decipher(List<Character> key) {
        char[] toDecrypt = encryption.toCharArray();
        for (int i = 0; i < toDecrypt.length; i++) {
            if (toDecrypt[i] >= 'A' && toDecrypt[i] <= 'Z') {
                toDecrypt[i] = key.get((toDecrypt[i] - 'A'));
            }
        }
        return String.copyValueOf(toDecrypt);
    }

    private List<Quadragram> quadragramFrequencies(String encryption) {//This function will create a list of all trigraphs with frequencies.
        char[] message = encryption.toCharArray();
        List<Quadragram> quadCount = new LinkedList<Quadragram>();
        for (int i = 0; i < (message.length - 3); i++) {
            if (!(message[i] == ' ' || message[i + 1] == ' ' || message[i + 2] == ' ')) {
                Quadragram quadragramn = new Quadragram();
                Character[] temp = new Character[4];
                temp[0] = message[i];
                temp[1] = message[i + 1];
                temp[2] = message[i + 2];
                temp[3] = message[i + 3];
                quadragramn.setQuadragram(temp);
                if (!(quadCount.contains(quadragramn))) {
                    Quadragram quadragram = new Quadragram();
                    quadragram.setQuadragram(temp);
                    quadragram.setCount(1);
                    quadCount.add(quadragram);
                } else {//trigraphs.contains(temp)
                    Iterator<Quadragram> iter = quadCount.iterator();
                    while (iter.hasNext()) {
                        Quadragram q = iter.next();
                        if (q.equals(quadragramn)) {
                            int count = q.getCount() + 1;
                            q.setCount(count);
                            break;
                        }
                    }
                }
            }
        }
       /* Collections.sort(quadCount, new Comparator<Quadragram>() {//TODO: I don't think I need to sort this anymore, I just need the list.
            @Override
            public int compare(Quadragram lhs, Quadragram rhs) {
                if (lhs.getCount() == rhs.getCount()) {
                    return 0;
                } else if (lhs.getCount() > rhs.getCount()) {
                    return -1;
                } //else lhs.getCount() < rhs.getCount()
                return 1;//TODO: I may need to flip this accordingly.
            }
        });//After this for loop, the letters passed in should be the char[] with the highest combo*/
        return quadCount;
    }

    private double score(List<Quadragram> quadragramsL) {
        double score = 0;
        Iterator<Quadragram> iter = quadragramsL.iterator();
        while (iter.hasNext()) {
            Quadragram quadragram = iter.next();
            StringBuilder stringBuilder = new StringBuilder(4);
            for (Character c : quadragram.getQuadragram()) {
                stringBuilder.append(c);
            }
            String key = stringBuilder.toString();
            if (quadragrams.containsKey(key.hashCode())) {
                score += Math.log10((double) quadragrams.get(key.hashCode()) / (double) quadragram.getCount());

            } else {//the 1's don't fit, so take the log of 1/count.
                score += Math.log10((double) 1 / (double) quadragram.getCount());
            }

        }
        return score;
    }
}
