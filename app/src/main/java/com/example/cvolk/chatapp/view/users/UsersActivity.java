package com.example.cvolk.chatapp.view.users;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.cvolk.chatapp.R;
import com.example.cvolk.chatapp.managers.AuthManager;

public class UsersActivity extends AppCompatActivity implements AuthManager.ISignOutInteraction {

    private AuthManager authManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        injectManagers();

    }

    private void injectManagers() {
        authManager = AuthManager.getDefault(this);
    }

    public void onSignOut(View view) {
        authManager.signOut();
    }

    @Override
    public void onSignOut(boolean isSignedOut) {

        finish();
    }
}
