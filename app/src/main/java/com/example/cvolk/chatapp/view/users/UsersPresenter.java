package com.example.cvolk.chatapp.view.users;

import com.example.cvolk.chatapp.managers.DBManager;
import com.example.cvolk.chatapp.model.UserProfile;

import java.util.List;

public class UsersPresenter implements UsersContract.Presenter, DBManager.IDataRetrieval {

    UsersContract.View view;
    DBManager dbManager;

    public UsersPresenter() {
        dbManager = new DBManager();
    }

    @Override
    public void attachView(UsersContract.View view) {
        this.view = view;

        dbManager.retrieveUsers();
    }

    @Override
    public void detachView() {

    }

    @Override
    public void onRetrieveUserList(List<UserProfile> userList) {

        view.onReceiveUserList(userList);
    }
}
