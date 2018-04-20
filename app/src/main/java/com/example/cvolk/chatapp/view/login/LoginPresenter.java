package com.example.cvolk.chatapp.view.login;

import com.example.cvolk.chatapp.managers.AuthManager;
import com.example.cvolk.chatapp.managers.DBManager;
import com.example.cvolk.chatapp.model.UserCredentials;
import com.example.cvolk.chatapp.model.UserProfile;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter implements LoginContract.Presenter, AuthManager.ILoginInteraction {

    LoginContract.View view;
    AuthManager authManager;
    DBManager dbManager;

    public LoginPresenter(AuthManager authManager) {

        authManager.attach(this);
        this.authManager = authManager;
        dbManager = new DBManager();


    }

    @Override
    public void signIn(UserCredentials userCredentials) {

        authManager.signIn(userCredentials);
    }

    @Override
    public void register(UserCredentials userCredentials, UserProfile userProfile) {

        authManager.register(userCredentials);
        dbManager.saveUser(userProfile);
    }

    @Override
    public boolean checkSession() {
        return authManager.checkSession();
    }

    @Override
    public void attachView(LoginContract.View view) {

        this.view = view;
    }

    @Override
    public void detachView() {

        this.view = null;
    }

    @Override
    public void onLoginSuccess(FirebaseUser user) {

        view.onLoginSuccess(user);
    }

    @Override
    public void onLoginError(String error) {

        view.onLoginFailure(error);
    }
}
