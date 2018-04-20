package com.example.cvolk.chatapp.view.users;

import com.example.cvolk.chatapp.model.UserProfile;
import com.example.cvolk.chatapp.view.base.BasePresenter;
import com.example.cvolk.chatapp.view.base.BaseView;

import java.util.List;

public interface UsersContract {

    interface View extends BaseView {

        void onReceiveUser(UserProfile userProfile);

        void onReceiveUserList(List<UserProfile> userList);
    }

    interface Presenter extends BasePresenter<UsersContract.View> {

        //todo
    }
}
