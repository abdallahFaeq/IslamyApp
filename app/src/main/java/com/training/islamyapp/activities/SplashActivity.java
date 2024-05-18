package com.training.islamyapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.training.islamyapp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_splash);

        new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        goToMainActivity();
                    }
                }, 1500);
    }
    public void goToMainActivity(){
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}