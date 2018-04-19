package com.example.cvolk.chatapp.view.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cvolk.chatapp.R;
import com.example.cvolk.chatapp.managers.AuthManager;
import com.example.cvolk.chatapp.model.UserCredentials;
import com.example.cvolk.chatapp.view.users.UsersActivity;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private static final String TAG = LoginActivity.class.getSimpleName() + "TAG";

    private EditText etEmail;
    private EditText etPassword;
    private String email;
    private String password;

    private LoginPresenter presenter;
    UserCredentials userCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();

        presenter = new LoginPresenter(AuthManager.getDefault(this));
        userCredentials = new UserCredentials(email, password);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);

        if (presenter.checkSession()) {
            startUsersActivity();
        }
    }

    private void startUsersActivity() {

        Intent intent = new Intent(getApplicationContext(), UsersActivity.class);
        startActivity(intent);
    }

    private void bindViews() {

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
    }

    public void onSignIn(View view) {

        getCredentials();
        presenter.signIn(userCredentials);
    }

    public void onRegister(View view) {

        getCredentials();

        if (!email.equals("") && !password.equals("")) {
            presenter.register(userCredentials);
        }
        else {
            Toast.makeText(this, "Email and Password cannot be blank.", Toast.LENGTH_SHORT).show();
        }

    }

    private void getCredentials() {

        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

        userCredentials.setEmail(email);
        userCredentials.setPassword(password);
    }

    @Override
    public void onLoginSuccess(FirebaseUser user) {

        Toast.makeText(this, "Welcome " + user.getEmail(), Toast.LENGTH_SHORT).show();
        startUsersActivity();
    }

    @Override
    public void onLoginFailure(String error) {
        showError("Login failed: " + error);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
