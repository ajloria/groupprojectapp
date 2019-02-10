package tcss450.uw.edu.phishapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegSuccessInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class RegSuccessInfoFragment extends Fragment {


    public RegSuccessInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reg_success_info, container, false);

        Button b = (Button) v.findViewById(R.id.fragRegSuccessLogin_button);
        //Use a method reference to add the OnClickListener
        b.setOnClickListener(this::onLoginButtonClicked);

        
        return v;

    }

    private void onLoginButtonClicked(View view) {
        LoginFragment nextFrag= new LoginFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_main_container, nextFrag)
                .commit();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
    }
}
