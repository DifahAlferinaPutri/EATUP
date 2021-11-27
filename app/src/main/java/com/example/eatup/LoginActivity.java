package com.example.eatup;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eatup.ui.model.AuthClass;
import com.example.eatup.ui.model.AuthData;
import com.example.eatup.ui.retrofit.LoginInformation;
import com.example.eatup.ui.retrofit.PortalClient;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    EditText txt_email, txt_password;
    Button btn_login, btn_register;
    ProgressDialog pDialog;
    ConnectivityManager conMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_email = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_1 = txt_email.getText().toString();
                String password_1 = txt_password.getText().toString();

                // mengecek kolom yang kosong
                if (email_1.trim().length() > 0 && password_1.trim().length() > 0) {
                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        checkLogin(email_1, password_1);
                    } else {
                        Toast.makeText(getApplicationContext() ,"No Internet Connection", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext() ,"Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
                }

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

    }

    //login
    private void checkLogin(final String email_1, final String password_1) {
        LoginInformation loginInformation = new LoginInformation();
        loginInformation.setEmail(email_1);
        loginInformation.setPassword(password_1);

        //Membuat Object client
        String API_BASE_URL = "http://172.20.10.6/EatUp/public/";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder
                .client(httpClient.build())
                .build();

        PortalClient client = retrofit.create(PortalClient.class);

        //Panggil method
        Call<AuthClass> call = client.checkLogin(loginInformation);
        toggleState(true);
        Log.d("P3S4N", "onResponse - Status : ");
        call.enqueue(new Callback<AuthClass>() {
            @Override
            public void onResponse(Call<AuthClass> call, Response<AuthClass> response) {
                AuthClass authClass = response.body();
                Log.d("P3S4N", "onResponse - Status : " + response.code());
                if(authClass != null){
                    AuthData data = authClass.getAuthData();
                    String token = data.getToken();
                    String first_name = data.getFirstName();
                    String last_name = data.getLastName();
                    String email = data.getEmail();

                    SharedPreferences preferences =
                            getSharedPreferences("com.example.eatup.PREFS", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString("ACCESS_TOKEN", token);
                    editor.putString("FIRST_NAME", first_name);
                    editor.putString("LAST_NAME", last_name);
                    editor.putString("EMAIL", email);
                    editor.putInt("TYPE", -1);
                    editor.apply();

                    Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Username / password anda salah", Toast.LENGTH_SHORT).show();
                }
                toggleState(false);
            }

            @Override
            public void onFailure(Call<AuthClass> call, Throwable t) {
                Log.d("P3S4N", t.getMessage());
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void toggleState(Boolean onProgress){
        ProgressBar progressBar = findViewById(R.id.progressBar);
        Button btn_login = findViewById(R.id.btn_login);
        EditText txt_email = findViewById(R.id.txt_email);
        EditText txt_password = findViewById(R.id.txt_password);

        if(onProgress){
            progressBar.setVisibility(View.VISIBLE);
            btn_login.setVisibility(View.GONE);
            txt_email.setEnabled(false);
            txt_password.setEnabled(false);
        }else {
            progressBar.setVisibility(View.GONE);
            btn_login.setVisibility(View.VISIBLE);
            txt_email.setEnabled(true);
            txt_password.setEnabled(true);
        }
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}