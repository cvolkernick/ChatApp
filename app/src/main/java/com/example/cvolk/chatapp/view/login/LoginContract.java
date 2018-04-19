package com.example.cvolk.chatapp.view.login;

import com.example.cvolk.chatapp.model.UserCredentials;
import com.example.cvolk.chatapp.view.base.BasePresenter;
import com.example.cvolk.chatapp.view.base.BaseView;
import com.google.firebase.auth.FirebaseUser;

public interface LoginContract {

    interface View extends BaseView {

        void onLoginSuccess(FirebaseUser user);

        void onLoginFailure(String error);
    }

    interface Presenter extends BasePresenter<View> {

        void signIn(UserCredentials userCredentials);

        void register(UserCredentials userCredentials);

        boolean checkSession();
    }
}
