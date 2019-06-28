package com.example.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignUp,btnLogIn;
    private EditText edtUserName ,edtEmail,edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setTitle("Sign Up");

        btnSignUp = findViewById(R.id.btnSignup_signup_activity);
        btnLogIn = findViewById(R.id.btnLogin_signup_activity);
        edtUserName = findViewById(R.id.user_name_signup_activity);
        edtEmail = findViewById(R.id.mail_signup_activity);
        edtPassword = findViewById(R.id.password_signup_activity);

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnSignUp);
                }

                return false;
            }
        });

        btnSignUp.setOnClickListener(this);

        btnLogIn.setOnClickListener(this);
        if (ParseUser.getCurrentUser() != null)
        {
          //  ParseUser.getCurrentUser().logOut();
            transitiontoSocialMedia();
        }

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignup_signup_activity:

                if (edtEmail.getText().toString().equals("") || edtUserName.getText().toString().equals("") || edtPassword.getText().toString().equals(""))
                {
                    Toast.makeText(this, "Email, Password, Username is required", Toast.LENGTH_SHORT).show();
                }else {
                    final ParseUser parseUser = new ParseUser();
                    parseUser.setUsername(edtUserName.getText().toString());
                    parseUser.setEmail(edtEmail.getText().toString());
                    parseUser.setPassword(edtPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing up " + edtUserName.getText().toString());
                    progressDialog.show();

                    parseUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(SignUp.this, parseUser.getUsername() + " is signed up", Toast.LENGTH_SHORT).show();
                                transitiontoSocialMedia();
                            } else {
                                Toast.makeText(SignUp.this, "There was an error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }

                break;

            case R.id.btnLogin_signup_activity:

                Intent intent = new Intent(SignUp.this,LoginActivity.class);
                startActivity(intent);
                finish();

                break;
        }
    }

    public void rootLayoutTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void transitiontoSocialMedia()
    {
        Intent intent = new Intent(SignUp.this,SocialMedia.class);
        startActivity(intent);
        finish();
    }
}
