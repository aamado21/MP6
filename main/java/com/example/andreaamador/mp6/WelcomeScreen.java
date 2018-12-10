package com.example.andreaamador.mp6;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class WelcomeScreen extends AppCompatActivity {
    @Override
    //sets layout file as content view
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.welcomescreen);

        //handler used to schedule the splash screen
        Handler handle = new Handler();
        //delays it to appear
        handle.postDelayed(new Runnable() {
            @Override
            //runs the splash screen
            public void run() {
                Intent intent = new Intent(WelcomeScreen.this, MapsActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2500);

    }
}
