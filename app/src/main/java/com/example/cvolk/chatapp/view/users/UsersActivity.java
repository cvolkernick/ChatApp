package com.example.cvolk.chatapp.view.users;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.cvolk.chatapp.R;
import com.example.cvolk.chatapp.adapters.UsersAdapter;
import com.example.cvolk.chatapp.managers.AuthManager;
import com.example.cvolk.chatapp.model.UserProfile;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity implements UsersContract.View, AuthManager.ISignOutInteraction {

    private AuthManager authManager;
    private RecyclerView rvUsers;
    private LinearLayoutManager layoutManager;
    private List<UserProfile> userProfiles = new ArrayList<>();
    private UsersAdapter usersAdapter;
    private UsersPresenter usersPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        injectManagers();

        usersPresenter = new UsersPresenter();
        bindRecyclerView();
    }

    private void bindRecyclerView() {

        rvUsers = findViewById(R.id.rvUsers);
        layoutManager = new LinearLayoutManager(this);

        rvUsers.setLayoutManager(layoutManager);

        usersAdapter = new UsersAdapter(userProfiles, this);
        rvUsers.setAdapter(usersAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        usersPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        usersPresenter.detachView();
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

    @Override
    public void onReceiveUser(UserProfile userProfile) {

    }

    @Override
    public void onReceiveUserList(List<UserProfile> userList) {
        userList.clear();
        userList.addAll(userList);
        usersAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {

        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }
}
