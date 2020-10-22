package com.example.Instagramodoki;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(ParseUser.getCurrentUser() != null){
            goMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
//        btnLogin.setOnClickListener(new View.OnClickListener(){
//        @Override
//        public void onClick(View v){
//            Log.i(TAG, "onClick login button");
//            String username = etUsername.getText().toString();
//            String password = etPassword.getText().toString();
//            loginUser(username, password);
//        }
//
//    });

        btnSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
}

private void loginUser(String username, String password){
    Log.i(TAG, "Attempting to login user " + username);
    ParseUser.logInInBackground(username, password, new LogInCallback() {
        @Override
        public void done(ParseUser user, ParseException e) {
            if (e !=null){
                Log.e(TAG, "Issue with login", e);
                Toast.makeText(LoginActivity.this, "Issue with login ", Toast.LENGTH_SHORT).show();
                return;
            }
            //TODO: navigate to the main activity if the user has signed in properly
            goMainActivity();
            Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
        }
    });
}


private void goMainActivity(){
    Intent i = new Intent(this, MainActivity.class);
    startActivity(i);
    finish();
}

public void signupUser(){
    final String username = etUsername.getText().toString();
    final String password = etPassword.getText().toString();
    if(username.isEmpty() || password.isEmpty()){
        Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
        return;
    }

    ParseUser user = new ParseUser();
    user.setUsername(username);
    user.setPassword(password);



    user.signUpInBackground(new SignUpCallback() {
        public void done(ParseException e) {
            if (e == null) {
                // Hooray! Let them use the app now.
                loginUser(username, password);
            } else {
                // Sign up didn't succeed. Look at the ParseException
                Toast.makeText(LoginActivity.this, "SignUp error", Toast.LENGTH_SHORT).show();
                etUsername.setText("");
                etPassword.setText("");
                // to figure out what went wrong
            }
        }
    });


}

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnLogin:
                Log.i(TAG, "onClick login button");
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            loginUser(username, password);
                break;


            case R.id.btnSignup:
                Toast.makeText(this, "signup", Toast.LENGTH_SHORT).show();
                signupUser();
                break;
            }

    }
}
