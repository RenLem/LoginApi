package com.renatopuskaric.loginapi.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.renatopuskaric.loginapi.constants.Constant;
import com.renatopuskaric.loginapi.daos.UserDao;
import com.renatopuskaric.loginapi.models.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {



   public abstract UserDao userDao();

   private static volatile AppDatabase INSTANCE;
   private final static int NUM_OF_THREADS = 3;
   public static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUM_OF_THREADS);

   public static AppDatabase getDatabase(final Context context){
      if (INSTANCE == null){
         synchronized (AppDatabase.class) {
            if (INSTANCE == null){
               INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, Constant.DATABASE_NAME)
                       .addCallback(sAppDatabaseCallback)
                       .build();
            }
         }
      }

      return INSTANCE;
   }

   private static final AppDatabase.Callback sAppDatabaseCallback = new AppDatabase.Callback() {
      @Override
      public void onCreate(@NonNull SupportSQLiteDatabase db){
         super.onCreate(db);

         databaseExecutor.execute(() -> {
            UserDao userDao = INSTANCE.userDao();
            userDao.deleteAll();

            /*User user = new User("baka", "access", "refresh");
            userDao.insert(user);*/
         });
      }
   };
}
