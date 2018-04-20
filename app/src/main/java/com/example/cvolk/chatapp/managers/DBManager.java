package com.example.cvolk.chatapp.managers;

import android.util.Log;

import com.example.cvolk.chatapp.model.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DBManager {

    private static IDataRetrieval listener;

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

    public void retrieveUsers() {

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<UserProfile> users = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserProfile userProfile = snapshot.getValue(UserProfile.class);
                    users.add(userProfile);
                }

                Log.d(TAG, "onDataChange: " + users);

                listener.onRetrieveUserList(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public interface IDataRetrieval {

        void onRetrieveUserList(List<UserProfile> userList);
    }
}
