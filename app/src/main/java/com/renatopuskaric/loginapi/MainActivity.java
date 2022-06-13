package com.renatopuskaric.loginapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.renatopuskaric.loginapi.constants.Constant;
import com.renatopuskaric.loginapi.database.AppDatabase;
import com.renatopuskaric.loginapi.models.User;
import com.renatopuskaric.loginapi.services.RestApiService;
import com.renatopuskaric.loginapi.viewmodels.AppViewModel;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private AppViewModel appViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MaterialButton button_logout = findViewById(R.id.button_logout);
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDatabase(Constant.DATABASE_NAME);

                SharedPreferences preferences = getSharedPreferences("Token", MODE_PRIVATE);
                preferences.edit().remove("accessToken").commit();

                finishAndRemoveTask();

            }
        });

        try {
            rest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        database();
    }

    private void rest() throws InterruptedException {

        final TextView textView = findViewById(R.id.textView_user);

        SharedPreferences sharedPreferences = getSharedPreferences("Token", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken", "");

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + accessToken)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();


        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(RestApiService.ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestApiService restApi = retrofit.create(RestApiService.class);



        Call<User> user = restApi.getApiUserData();
        user.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                User user= response.body();
                textView.setText(user.toString());

            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }

        });

    }

    private void database(){

        AppViewModel appViewModel = new ViewModelProvider(this).get(AppViewModel.class);
        LiveData<List<User>> liveUserList = appViewModel.getAllUsers();

        liveUserList.observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> usersList) {
                for (User user: usersList) {
                    appViewModel.insert(user);
                }
            }
        });

        /*String currentDBPath = getDatabasePath("MyDatabase").getAbsolutePath();
        textView.setText(currentDBPath);*/
    }
}