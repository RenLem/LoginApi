package com.renatopuskaric.loginapi.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.renatopuskaric.loginapi.models.User;
import com.renatopuskaric.loginapi.repositories.AppRepository;


import java.util.List;

public class AppViewModel extends AndroidViewModel {

   private final AppRepository appRepository;

   private final LiveData<List<User>> allUsers;

   public AppViewModel(@NonNull Application application) {
      super(application);

      appRepository = new AppRepository(application);
      allUsers = appRepository.getAllUsers();
   }

   public LiveData<List<User>> getAllUsers() {
      return allUsers;
   }

   public void insert(User user) {
      appRepository.insert(user);
   }
   public void deleteAll(){
      appRepository.deleteAllUsers();
   }
}
