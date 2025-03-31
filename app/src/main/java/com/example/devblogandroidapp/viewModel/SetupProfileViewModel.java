package com.example.devblogandroidapp.viewModel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import androidx.activity.result.ActivityResultLauncher;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import androidx.databinding.library.baseAdapters.BR;

import com.example.devblogandroidapp.activity.SetupProfileActivity;
import com.example.devblogandroidapp.model.User;
import com.example.devblogandroidapp.repository.UserRepository;

public class SetupProfileViewModel extends BaseObservable {
    private String email;
    private String name;
    private String username;
    private ObservableField<Bitmap> profileImage = new ObservableField<>();
    private Context context;
    private UserRepository userRepository;
    private ActivityResultLauncher<Intent> pickImageLauncher;

    public SetupProfileViewModel(Context context) {
        this.context = context;
        userRepository = new UserRepository(context);
        getDataFromRoom();
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public ObservableField<Bitmap> getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Bitmap bitmap) {
        profileImage.set(bitmap);
        notifyPropertyChanged(BR.profileImage);
    }

    private void getDataFromRoom(){
        User user = userRepository.getCurrentUser();
        if (user != null) {
            setEmail(user.getEmail());
            setName(user.getName());
            setUsername(user.getUsername());
        }
    }

    public void onPickImage() {
        if (context instanceof SetupProfileActivity) {
            ((SetupProfileActivity) context).launchPickImage();
        }
    }

}
