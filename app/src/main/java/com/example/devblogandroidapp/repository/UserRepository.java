package com.example.devblogandroidapp.repository;

import android.content.Context;

import com.example.devblogandroidapp.database.UserDAO;
import com.example.devblogandroidapp.database.UserDatabase;
import com.example.devblogandroidapp.model.User;

public class UserRepository {
    private UserDAO userDAO;

    public UserRepository (Context context){
        userDAO = UserDatabase.getInstance(context).userDAO();
    }

    public void saveUser(User user){
        userDAO.insert(user);
    }

    public User getCurrentUser(){
        return userDAO.getCurrentUser();
    }
}
