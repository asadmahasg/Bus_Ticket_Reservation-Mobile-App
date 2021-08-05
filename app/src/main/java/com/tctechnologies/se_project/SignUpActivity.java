package com.tctechnologies.se_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    // Constants
    public static final String CHAT_PREFS = "ChatPrefs";
    public static final String DISPLAY_NAME_KEY = "username";

    // Firebase instance variable
    private FirebaseAuth mAuth;

    // Input variables
    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText mUsernameView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEmailView = (EditText) findViewById(R.id.Email);
        mPasswordView = (EditText) findViewById(R.id.Password);
        mUsernameView = (EditText) findViewById(R.id.Name);

        // Getting Firebase instance
        mAuth = FirebaseAuth.getInstance();
        final Button SignUpButton = (Button) findViewById(R.id.SignedUp);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp(view);
            }
        });

    }

    // Executed when SignUp Button is pressed.
    public void signUp(View v) {
        attemptRegistration();
    }

    private void attemptRegistration() {
        // Reset errors displayed in the form.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean canel = false;
        View focusView = null;

        // Check for the valid password
        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.InvalidPass));
            focusView = mPasswordView;
            canel = true;
        }

        // Check for the valid email
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.Emptyfield));
            focusView = mEmailView;
            canel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.Invalidemail));
            focusView = mEmailView;
            canel = true;
        }

        if (canel) {
            focusView.requestFocus();
        } else {
            createFirebaseUser();
        }
    }

    private boolean isPasswordValid(String password) {
        // minimum of 8 chars
        return password.length() > 7;
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private void createFirebaseUser() {
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Log.d("eClinic", "createUser on complete: " + task.isSuccessful());

                if (!task.isSuccessful()) {
                    Log.d("eClinic", "Registration failed");
                    Toast.makeText(SignUpActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                } else {

                    saveDisplayName();
                    Intent intent = new Intent(SignUpActivity.this,LogInActivity.class);
                    finish();
                    startActivity(intent);

                }
            }
        });
    }

    private void saveDisplayName()
    {
        String displayName = mUsernameView.getText().toString();
        SharedPreferences prefs = getSharedPreferences(CHAT_PREFS, 0);
        prefs.edit().putString(DISPLAY_NAME_KEY, displayName).apply();
    }


}