package com.example.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtMail,edtPassword;
    private Button btnLogIn,btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Log In");

        edtMail=findViewById(R.id.mail_login_activity);
        edtPassword=findViewById(R.id.password_login_activity);

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
                    onClick(btnLogIn);
                }
                return false;
            }
        });

        btnLogIn=findViewById(R.id.btnLogin_login_activity);
        btnSignUp=findViewById(R.id.btnSignup_login_activity);

        btnLogIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null)
        {
            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnLogin_login_activity:

                if (edtMail.getText().toString().equals("") || edtPassword.getText().toString().equals(""))
                {
                    Toast.makeText(this, "Email or Password Required", Toast.LENGTH_SHORT).show();
                }else {
                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Loging In");
                    progressDialog.show();
                    ParseUser.logInInBackground(edtMail.getText().toString(), edtPassword.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null && e == null)
                            {
                                Toast.makeText(LoginActivity.this, user.getUsername()+ " is Logged in successfully", Toast.LENGTH_SHORT).show();
                                transitiontoSocialMedia();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }

                break;

            case R.id.btnSignup_login_activity:
                Intent intent = new Intent(LoginActivity.this,SignUp.class);
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
        Intent intent = new Intent(LoginActivity.this,SocialMedia.class);
        startActivity(intent);
        finish();
    }
}
