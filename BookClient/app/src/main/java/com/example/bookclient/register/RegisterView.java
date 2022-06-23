package com.example.bookclient.register;

public interface RegisterView {
    void showProgress();
    void hideProgress();
    void showToast(String message);
    void onRequestSuccess(String message);
    void onRequestError(String message);
    void navigateToLoginScreen();
    void stay();
    void end();

}
