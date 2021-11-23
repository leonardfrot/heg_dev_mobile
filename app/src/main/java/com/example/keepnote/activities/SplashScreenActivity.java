package com.example.keepnote.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.keepnote.R;

public class SplashScreenActivity extends AppCompatActivity {

    private final int SPLASH_SCREEN_TIMEOUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // rediriger vers la page principale "MainActivity" après 3 secondes.


        // handler post delayed
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // démarrer une page
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

        }, SPLASH_SCREEN_TIMEOUT);

    }
}