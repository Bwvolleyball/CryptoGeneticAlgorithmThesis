package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.presentation;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateEncryptionFragment.CreateEncryptionInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateEncryptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateEncryptionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String MESSAGE_PARAM = "messageCEF";
    private static final String ENCRYPT_PARAM = "encryptionCEF";

    // TODO: Rename and change types of parameters
    private String message;
    private String encryption;

    private CreateEncryptionInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param message the passed message (if any).
     * @param encryption the passed encryption (if any).
     * @return A new instance of fragment CreateEncryptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateEncryptionFragment newInstance(String message, String encryption) {
        CreateEncryptionFragment fragment = new CreateEncryptionFragment();
        Bundle args = new Bundle();
        args.putString(MESSAGE_PARAM, message);
        args.putString(ENCRYPT_PARAM, encryption);
        fragment.setArguments(args);
        return fragment;
    }

    public CreateEncryptionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            message = getArguments().getString(MESSAGE_PARAM);
            encryption = getArguments().getString(ENCRYPT_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_encryption, container, false);
        // Inflate the layout for this fragment
        Button encrypt = (Button) view.findViewById(R.id.encryptBtn);
        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEncryptButtonPressed(v);
            }
        });
        if (message != null) {
            EditText messageBox = (EditText) view.findViewById(R.id.messageTxt);
            messageBox.setText(message);
        }
        if (encryption != null) {
            EditText encryptBox = (EditText) view.findViewById(R.id.encryptTxt);
            encryptBox.setText(encryption);
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onEncryptButtonPressed(View view) {
        if (mListener != null) {
            String msg = ((EditText) view.findViewById(R.id.messageTxt)).getText().toString();
            mListener.onEncryptButtonInteraction(view, msg);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (CreateEncryptionInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement CreateEncryptionInteractionListener");
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
    public interface CreateEncryptionInteractionListener {
        // TODO: Update argument type and name
        public void onEncryptButtonInteraction(View view, String msg);
    }

}
