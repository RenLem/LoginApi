package com.renatopuskaric.loginapi.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.renatopuskaric.loginapi.daos.UserDao;
import com.renatopuskaric.loginapi.database.AppDatabase;
import com.renatopuskaric.loginapi.models.User;

import java.util.List;

public class AppRepository {

   private final UserDao userDao;
   private final LiveData<List<User>> allUsers;

   public AppRepository(Application application){
      AppDatabase db = AppDatabase.getDatabase(application);
      userDao = db.userDao();
      allUsers = userDao.getAll();
   }

   public LiveData<List<User>> getAllUsers() {
      return allUsers;
   }

   public void insert(User user) {
      AppDatabase.databaseExecutor.execute(() -> {
         userDao.insert(user);
      });
   }
   public void deleteAllUsers(){
      AppDatabase.databaseExecutor.execute(() -> {
         userDao.deleteAll();
      });
   }
}
