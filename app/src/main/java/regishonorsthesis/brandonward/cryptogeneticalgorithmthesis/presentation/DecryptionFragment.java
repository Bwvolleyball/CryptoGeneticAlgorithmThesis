package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.presentation;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.presentation.DecryptionFragment.OnDecryptionFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DecryptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DecryptionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CIPHER_PARAM = "cipher1";
    private static final String MSG_PARAM = "message2";

    // TODO: Rename and change types of parameters
    private String cipher;
    private String message;

    private OnDecryptionFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param cipher received cipher text.
     * @param msg    received decrypted message(if any).
     * @return A new instance of fragment Decryption.
     */
    // TODO: Rename and change types and number of parameters
    public static DecryptionFragment newInstance(String cipher, String msg) {
        DecryptionFragment fragment = new DecryptionFragment();
        Bundle args = new Bundle();
        args.putString(CIPHER_PARAM, cipher);
        args.putString(MSG_PARAM, msg);
        fragment.setArguments(args);
        return fragment;
    }

    public DecryptionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cipher = getArguments().getString(CIPHER_PARAM);
            message = getArguments().getString(MSG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_decryption, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onDecryptButtonPressed() {
        if (mListener != null) {
            mListener.onDecryptPressed();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnDecryptionFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnDecryptionFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onDecryptPressed();
    }

}
