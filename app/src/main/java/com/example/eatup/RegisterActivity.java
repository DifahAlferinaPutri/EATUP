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

import com.example.eatup.ui.model.SignUpResponse;
import com.example.eatup.ui.retrofit.PortalClient;
import com.example.eatup.ui.retrofit.RegisterInformation;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    EditText txt_email, txt_password, txt_firstname, txt_lastname, txt_confirmpass;
    Button btn_login, btn_register;

    ProgressDialog pDialog;
    String success;
    ConnectivityManager conMgr;


    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";


    SharedPreferences sharedpreferences;
    Boolean session = false;
    String id, nama, nim, email;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txt_email = findViewById(R.id.editTextTextEmailAddress2);
        txt_firstname = findViewById(R.id.editTextTextPersonName);
        txt_lastname = findViewById(R.id.editTextTextPersonName2);
        txt_password = findViewById(R.id.editTextTextPassword2);
        txt_confirmpass = findViewById(R.id.editTextTextPassword3);
        btn_login = findViewById(R.id.btn_formLogin);
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

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txt_email.getText().toString();
                String firstname = txt_firstname.getText().toString();
                String lastname = txt_lastname.getText().toString();
                String password = txt_password.getText().toString();
                String confirmpass = txt_confirmpass.getText().toString();

                // mengecek kolom yang kosong
                if (email.trim().length() > 0 && firstname.trim().length() > 0 && lastname.trim().length() > 0 && password.trim().length() > 0 && confirmpass.trim().length() > 0) {
                    if(password.equals(confirmpass)) {
                        if (conMgr.getActiveNetworkInfo() != null
                                && conMgr.getActiveNetworkInfo().isAvailable()
                                && conMgr.getActiveNetworkInfo().isConnected()) {
                            register(email, firstname, lastname, password, confirmpass);
                        } else {
                            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext() ,"Confirm password tidak sesuai", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext() ,"Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
                }

            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

    }

    //Register
    private void register(final String email, final String first_name, final String last_name, final String password, final String confirmpass) {

        RegisterInformation registerInformation = new RegisterInformation();
        registerInformation.setEmail(email);
        registerInformation.setFirstName(first_name);
        registerInformation.setLastName(last_name);
        registerInformation.setPassword(password);
        registerInformation.setConfirmpass(confirmpass);

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
        Call<SignUpResponse> call = client.register(registerInformation);

        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                SignUpResponse signUpResponse = response.body();
                Log.d("P3S4N", "onResponse - Status : " + response.code());
                if(signUpResponse != null){
                    Toast.makeText(getApplicationContext(), "Berhasil daftar", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Gagal daftar", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void toggleState(Boolean onProgress){
        ProgressBar progressBar = findViewById(R.id.progressBar);

        if(onProgress){
            progressBar.setVisibility(View.VISIBLE);
            btn_register.setVisibility(View.GONE);
            txt_email.setEnabled(false);
            txt_firstname.setEnabled(false);
            txt_lastname.setEnabled(false);
            txt_password.setEnabled(false);
            txt_confirmpass.setEnabled(false);
        }else {
            progressBar.setVisibility(View.GONE);
            btn_register.setVisibility(View.VISIBLE);
            txt_email.setEnabled(true);
            txt_firstname.setEnabled(true);
            txt_lastname.setEnabled(true);
            txt_password.setEnabled(true);
            txt_confirmpass.setEnabled(true);
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