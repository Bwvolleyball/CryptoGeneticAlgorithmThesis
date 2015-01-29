package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.business;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.R;

/**
 * Created by BrandonWard on 1/29/2015.
 */
public class DecryptionManagerQuadragram implements IDecryptionMgr {

    private Map<String, Integer> quadragrams;
    private Context context;

    public DecryptionManagerQuadragram(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        quadragrams = new HashMap<String, Integer>();
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
                String key = result[0];
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
        //TODO: IF Key(Quadragram) doesn't exist, assume score of 1(ran out of room for android);
        return encryption;
    }


}
