package com.example.cvolk.chatapp.managers;

import com.example.cvolk.chatapp.model.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBManager {

    private final FirebaseDatabase database;
    private final DatabaseReference usersRef;
    //private final DatabaseReference messageRef;

    public DBManager() {

        database = FirebaseDatabase.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        usersRef = database.getReference("users");
    }

    public void saveUser(UserProfile userProfile) {

        usersRef.push().setValue(userProfile);
    }
}
