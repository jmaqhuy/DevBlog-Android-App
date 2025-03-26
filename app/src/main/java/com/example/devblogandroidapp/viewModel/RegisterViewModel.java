package com.example.devblogandroidapp.viewModel;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.example.devblogandroidapp.BR;
import com.example.devblogandroidapp.activity.LoginActivity;
import com.example.devblogandroidapp.api.ApiService;
import com.example.devblogandroidapp.api.request.RegisterRequest;
import com.example.devblogandroidapp.api.response.ApiResponse;
import com.example.devblogandroidapp.api.response.RegisterResponse;
import com.example.devblogandroidapp.utils.Constants;
import com.example.devblogandroidapp.utils.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends BaseObservable {
    private final String TAG = "RegisterViewModel";
    private String email;
    private String password;
    private String retypePassword;
    public ObservableField<String> errorMessage = new ObservableField<>();
    public ObservableField<Boolean> isShowErrorMessage = new ObservableField<>(false);
    public ObservableField<Boolean> inProgress = new ObservableField<>(false);
    private Context context;

    public RegisterViewModel(Context context) {
        this.context = context;
    }

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

    @Bindable
    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
        notifyPropertyChanged(BR.retypePassword);
    }

    public void onClickRegisterButton() {
        if (Boolean.TRUE.equals(inProgress.get())) {
            return;
        }
        inProgress.set(true);
        hideErrorMessage();

        if (TextUtils.isEmpty(email)) {
            showErrorMessage("Email must not be empty");
            inProgress.set(false);
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showErrorMessage("Email is not valid");
            inProgress.set(false);
            return;
        }
        if (TextUtils.isEmpty(password) || password.length() < 8) {
            showErrorMessage("Password must be at least 8 characters");
            inProgress.set(false);
            return;
        }

        if (!password.equals(retypePassword)) {
            showErrorMessage("Password and retype password must be the same");
            inProgress.set(false);
            return;
        }
        RegisterRequest request = new RegisterRequest();
        request.setEmail(email);
        request.setPassword(password);

        ApiService.apiService.register(request)
                .enqueue(new Callback<ApiResponse<RegisterResponse>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<RegisterResponse>> call,
                                           Response<ApiResponse<RegisterResponse>> response) {

                        inProgress.set(false);
                        if (response.isSuccessful()) {
                            ApiResponse<RegisterResponse> apiResponse = response.body();
                            Log.i(TAG, "Response: " + apiResponse);
                            PreferenceManager preferenceManager = new PreferenceManager(context);
                            preferenceManager.putString(Constants.TOKEN, apiResponse.getData().getToken());
                            // TODO: Change to Update Profile Activity
                            context.startActivity(new Intent(context, LoginActivity.class));
                            Log.i(TAG, "Register success");
                        } else {
                            if (response.code() == 400) {
                                try {
                                    // Đọc và parse error body
                                    ApiResponse<RegisterResponse> errorResponse =
                                            ApiService.errorConverter.convert(response.errorBody());
                                    Log.e(TAG, "Bad Request: " + errorResponse);
                                    showErrorMessage(errorResponse.getError().getMessage());
                                } catch (IOException e) {
                                    Log.e(TAG, "Error parsing error body", e);
                                }
                            } else {
                                Log.e(TAG, "Response error: " + response.code() + " - " + response.message());
                                showErrorMessage("Unknown error");
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<ApiResponse<RegisterResponse>> call, Throwable throwable) {
                        inProgress.set(false);
                        Log.e(TAG, "API call failed", throwable);
                        errorMessage.set(throwable.getMessage());
                        isShowErrorMessage.set(true);
                    }
                });
    }

    private void showErrorMessage(String message) {
        errorMessage.set(message);
        isShowErrorMessage.set(true);
    }

    private void hideErrorMessage() {
        errorMessage.set("");
        isShowErrorMessage.set(false);
    }
}
