package com.renatopuskaric.loginapi.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.renatopuskaric.loginapi.models.User;

import java.util.List;

@Dao
public interface UserDao {

 @Query("SELECT * FROM user")
 LiveData<List<User>> getAll();

 @Insert
 void insertAll(User... users);

 @Insert(onConflict = OnConflictStrategy.IGNORE)
 void insert(User user);

 @Delete
 void delete(User user);

 @Query("DELETE FROM user")
 void deleteAll();

}
