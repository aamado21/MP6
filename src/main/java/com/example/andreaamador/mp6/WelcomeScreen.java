package com.example.andreaamador.mp6;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class WelcomeScreen extends AppCompatActivity {
    @Override
    protected void onCreate (Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.welcomescreen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeScreen.this, MapsActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2500);

    }
}
