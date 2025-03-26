package com.example.devblogandroidapp.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.example.devblogandroidapp.MainActivity;
import com.example.devblogandroidapp.R;
import com.example.devblogandroidapp.databinding.ActivityRegisterBinding;
import com.example.devblogandroidapp.viewModel.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ActivityRegisterBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        RegisterViewModel registerViewModel = new RegisterViewModel(this);
        binding.setRegisterViewModel(registerViewModel);
        binding.setLifecycleOwner(this);


    }

}