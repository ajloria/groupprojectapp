package tcss450.uw.edu.phishapp;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import tcss450.uw.edu.phishapp.model.Credentials;

public class MainActivity extends AppCompatActivity implements
        LoginFragment.OnLoginFragmentInteractionListener,
        RegisterFragment.OnRegisterFragmentInteractionListener,
        WaitFragment.OnFragmentInteractionListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //We make activity_main.xml to be a frame layout to put the fragment inside.
        if (savedInstanceState == null) {
            if (findViewById(R.id.frame_main_container) != null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.frame_main_container, new LoginFragment())
                        .commit();
            }
        }
    }

    @Override
    public void onRegisterClick() {
        Bundle args = new Bundle();
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_main_container, new RegisterFragment())
                .addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }

    //This method could be used for communicating between fragments
    @Override
    public void onLoginSuccess(Credentials theUser, String jwt) {
        Bundle args = new Bundle();

        int count = getSupportFragmentManager().getBackStackEntryCount();
        //Delete everything off back stack.
        for (int i = 0; i < count; ++i) {
            getSupportFragmentManager().popBackStack();
        }
        //This will open up a new activity.
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        //Pass the credentials to the new activity.
        intent.putExtra("Login", theUser);
        intent.putExtra(getString(R.string.keys_intent_jwt), jwt);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRegisterSuccess(Credentials theUser) {

        Bundle args = new Bundle();

        int count = getSupportFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < count; ++i) {
            getSupportFragmentManager().popBackStack();
        }

        args.putSerializable("Login", theUser);
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setArguments(args);
        Log.wtf("Test", "In on register success" + theUser.getEmail());
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_main_container, loginFragment);
        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onWaitFragmentInteractionShow() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_main_container, new WaitFragment(), "WAIT")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onWaitFragmentInteractionHide() {
        getSupportFragmentManager()
                .beginTransaction()
                .remove(getSupportFragmentManager().findFragmentByTag("WAIT"))
                .commit();
    }
}
