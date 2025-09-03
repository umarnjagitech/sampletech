package com.umarndungotech.sampletech;

import static com.umarndungotech.sampletech.R.*;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        TextView homeText = findViewById(R.id.home_text);
        homeText.setText("The Meet is great here!");

        Button homeButton = findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.v("Umarnjagitech", "Welcome from the home button!");
                counter++;
                homeText.setText(String.format("Clicked: %d", counter));

                if(counter==10) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://umarndungo.netlify.app/"));
                    startActivity(browserIntent);
                }
            }
        });

    }
}