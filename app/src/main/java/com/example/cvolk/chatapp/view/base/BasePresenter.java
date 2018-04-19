package com.example.cvolk.chatapp.view.base;

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();

}
