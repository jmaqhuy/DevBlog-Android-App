package com.example.devblogandroidapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.devblogandroidapp.model.User;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("SELECT * FROM User WHERE id = :id")
    User getUserById(String id);

    @Query("DELETE FROM User WHERE id = :id")
    void deleteUserById(String id);

    @Query("SELECT * FROM User WHERE currentUser = 1")
    User getCurrentUser();

    @Query("DELETE FROM User")
    void deleteAllUser();
}
