package com.example.cvolk.chatapp.managers;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cvolk.chatapp.model.UserCredentials;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class AuthManager {

    private FirebaseAuth mAuth;

    Activity activity;
    ILoginInteraction loginListener;
    ISignOutInteraction signOutListener;
    FirebaseUser user;

    public static AuthManager instance = null;

    private AuthManager() {}

    public static AuthManager getDefault(Activity activity) {

        if (instance == null) {
            instance = new AuthManager(activity);

        }
        instance.attach(activity);
        return instance;
    }

    private AuthManager(Activity activity) {
        this.activity = activity;
        mAuth = FirebaseAuth.getInstance();
    }

    public  void attach(Object object) {

        if (object instanceof ILoginInteraction) {
            Log.d(TAG, "attach: ");
            this.loginListener = (ILoginInteraction) object;
        }
        if (object instanceof ISignOutInteraction) {

            this.signOutListener = (ISignOutInteraction) object;
        }
        if (object instanceof Activity) {
            this.activity = (Activity) object;
        }
    }
    
    public void register(UserCredentials userCredentials) {

        user = null;
        String email = userCredentials.getEmail();
        String password = userCredentials.getPassword();
        
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail: success");

                            user = mAuth.getCurrentUser();
                            loginListener.onLoginSuccess(user);
                        }
                        else {
                            Log.d(TAG, "createuserWithEmail: failure", task.getException());

                            loginListener.onLoginError(task.getException().getMessage());
                        }
                    }
                });
    }

    public void signIn(UserCredentials userCredentials) {

        user = null;
        String email = userCredentials.getEmail();
        String password = userCredentials.getPassword();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail: success");

                            user = mAuth.getCurrentUser();
                            loginListener.onLoginSuccess(user);
                        }
                        else {
                            Log.d(TAG, "signInWithEmail: failure", task.getException());

                            loginListener.onLoginError(task.getException().getMessage());
                        }
                    }
                });
    }

    public void signOut() {

        mAuth.signOut();
        signOutListener.onSignOut(user == null);
    }
    
    public boolean checkSession() {
        
        return getUser() != null;
    }

    public FirebaseUser getUser() {
        
        return mAuth.getCurrentUser();
    }

    public interface ILoginInteraction {

        void onLoginSuccess(FirebaseUser user);

        void onLoginError(String error);
    }

    public interface ISignOutInteraction {

        void onSignOut(boolean isSignedOut);
    }
}
