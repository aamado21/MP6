package com.example.andreaamador.mp6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DescriptionScreen extends AppCompatActivity {

    private static final String TAG = "DescriptionScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_screen);

        Button backButton = (Button) findViewById(R.id.backToMap);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DescriptionScreen.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
