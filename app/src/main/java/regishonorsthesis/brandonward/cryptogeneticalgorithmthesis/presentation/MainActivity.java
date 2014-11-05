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
import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.business.EncryptionManager;

public class MainActivity extends Activity implements CreateEncryptionFragment.CreateEncryptionInteractionListener, MainFragment.MainFragmentInteractionListener {
    //TODO: Create simple Encryption Mechanism that will shuffle a gene and allow for creating an encrypted message to be put back into decryption machine

    private String VISIBLE_FRAGMENT_TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())//On create, launch the app with the main fragment
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
    public void onEncryptButtonInteraction(String msg) {
        //TODO: This is from the encryption menu, when the user wants to encrypt their message.
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
        //TODO: Change View to Encryption Menu -> This should work, need to test on phone.
        getFragmentManager().beginTransaction().replace(R.id.container, CreateEncryptionFragment.newInstance("enter message to be encrypted", "")).commit();
    }

    @Override
    public void onDecryptInteraction() {
        //TODO: Change View to Decryption Menu
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
