package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.presentation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.R;
import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.business.DecryptionManager;
import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.business.EncryptionManager;

public class MainActivity extends Activity implements CreateEncryptionFragment.CreateEncryptionInteractionListener, MainFragment.MainFragmentInteractionListener, DecryptionFragment.OnDecryptionFragmentInteractionListener {

    private String VISIBLE_FRAGMENT_TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment newFragment = MainFragment.newInstance();
        VISIBLE_FRAGMENT_TAG = newFragment.toString();
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, newFragment, VISIBLE_FRAGMENT_TAG)//On create, launch the app with the main fragment
                    .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (VISIBLE_FRAGMENT_TAG != null) {
            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentByTag(VISIBLE_FRAGMENT_TAG)).commit();
        }
    }

    @Override
    public void onEncryptButtonInteraction(String msg) {
        //This is from the encryption menu, when the user wants to encrypt their message.
        EncryptionManager mgr = new EncryptionManager();
        String encryption = mgr.Encrypt(msg);

        FragmentManager fragmentManager = getFragmentManager();
        Fragment newFragment = new CreateEncryptionFragment().newInstance(msg, encryption);
        VISIBLE_FRAGMENT_TAG = newFragment.toString();
        fragmentManager.beginTransaction()
                .replace(R.id.container, newFragment, VISIBLE_FRAGMENT_TAG)
                .setTransition(FragmentTransaction.TRANSIT_NONE)
                .commit();

    }

    @Override
    public void onEncryptInteraction() {
        //Change View to Encryption Menu
        Fragment newFragment = CreateEncryptionFragment.newInstance("enter message to be encrypted", "");
        FragmentManager mgr = getFragmentManager();
        mgr.beginTransaction().addToBackStack(VISIBLE_FRAGMENT_TAG).replace(R.id.container, newFragment).commit();
        VISIBLE_FRAGMENT_TAG = newFragment.toString();
    }

    @Override
    public void onDecryptInteraction() {
        //Change View to Decryption Menu
        Fragment newFragment = DecryptionFragment.newInstance("paste encrypted message here", "");
        FragmentManager mgr = getFragmentManager();
        mgr.beginTransaction().addToBackStack(VISIBLE_FRAGMENT_TAG).replace(R.id.container, newFragment).commit();
        VISIBLE_FRAGMENT_TAG = newFragment.toString();
    }

    @Override
    public void onDecryptPressed(String encryption) {
        //From Decryption Menu, this will decrypt the message
        DecryptionManager mgr = new DecryptionManager();
        String decryption = mgr.decrypt(encryption);

        FragmentManager fragmentManager = getFragmentManager();
        Fragment newFragment = new DecryptionFragment().newInstance(encryption, decryption);
        VISIBLE_FRAGMENT_TAG = newFragment.toString();
        fragmentManager.beginTransaction()
                .replace(R.id.container, newFragment, VISIBLE_FRAGMENT_TAG)
                .setTransition(FragmentTransaction.TRANSIT_NONE)
                .commit();

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

    }
}
