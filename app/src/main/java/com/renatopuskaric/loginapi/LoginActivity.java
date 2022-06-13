package com.renatopuskaric.loginapi;

import static com.renatopuskaric.loginapi.R.color.dark_white;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.renatopuskaric.loginapi.constants.Constant;
import com.renatopuskaric.loginapi.models.BodyPojo;
import com.renatopuskaric.loginapi.models.LogPojo;
import com.renatopuskaric.loginapi.services.RestApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextInputEditText tiEditText_email = findViewById(R.id.textInput_email);
        final TextInputEditText tiEditText_password = findViewById(R.id.textInput_password);
        final Button button_login = findViewById(R.id.button_login);



        Editable email = tiEditText_email.getText();
        Editable password = tiEditText_password.getText();


        button_login.setOnClickListener(view -> {

            if (TextUtils.isEmpty(email) | TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "Molim upišite email i lozinku.", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    rest(email, password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void rest(Editable email, Editable password) throws Exception {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestApiService.ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestApiService restApi = retrofit.create(RestApiService.class);

        BodyPojo bodyPojo = new BodyPojo(email.toString(), password.toString(), Constant.APP_ID);

        Intent accessIntent = new Intent(getApplicationContext(), MainActivity.class);

        Call<LogPojo> logPojoCall = restApi.getApiLoginData(bodyPojo);
        logPojoCall.enqueue(new Callback<LogPojo>() {
            @Override
            public void onResponse(Call<LogPojo> call, Response<LogPojo> response) {
                int responseCode = response.code();

                if (responseCode < 400) {
                    LogPojo logPojo = response.body();
                    String accessToken = logPojo.getAccessToken();
                    String refreshToken = logPojo.getRefreshToken();
                    String userName = logPojo.getUsername();
                    Toast.makeText(getApplicationContext(), accessToken + "\r\n" + refreshToken + " " + userName, Toast.LENGTH_LONG).show();

                    SharedPreferences sharedPref = getSharedPreferences("Token", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("accessToken", accessToken);
                    editor.apply();


                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Login nije uspio. Pokušajte ponovno.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LogPojo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }

        });

    }

}