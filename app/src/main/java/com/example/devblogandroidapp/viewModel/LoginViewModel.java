package com.example.devblogandroidapp.viewModel;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.example.devblogandroidapp.BR;
import com.example.devblogandroidapp.api.request.LoginRequest;
import com.google.gson.Gson;

public class LoginViewModel extends BaseObservable {
    private String email;
    private String password;
    public ObservableField<String> errorMessage = new ObservableField<>();
    public ObservableField<Boolean> isShowErrorMessage = new ObservableField<>(false);

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public void onClickLoginButton(){
        errorMessage.set("");
        isShowErrorMessage.set(false);
        if (TextUtils.isEmpty(email)){
            errorMessage.set("Email must not be empty");
            isShowErrorMessage.set(true);
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            errorMessage.set("Email is not valid");
            isShowErrorMessage.set(true);
            return;
        }
        if (TextUtils.isEmpty(password) || password.length() < 8){
            errorMessage.set("Password must be at least 8 characters");
            isShowErrorMessage.set(true);
            return;
        }

        LoginRequest loginRequest = new LoginRequest(email, password);
        Gson gson = new Gson();
        String strJson = gson.toJson(loginRequest);
        Log.i("LoginRequest", strJson);
    }

    public void onClickSignUpButton(){

    }
}
