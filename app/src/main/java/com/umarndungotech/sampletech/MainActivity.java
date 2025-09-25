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

import java.io.BufferedReader;
import java.io.*;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.URL;
import java.net.HttpURLConnection;

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
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(() -> {
                    try {
                        /* // Send a HTTP GET Request by first connecting to the server
                        URL url = new URL("http://www.android.com/");
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));*/

                        Socket socket = new Socket("www.android.com", 80);
                        socket.setSoTimeout(2000);
                        OutputStream outputStream = socket.getOutputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                        // Send a raw HTTP GET request to the server
                        String request = "GET / HTTP/1.1\r\nHost: www.android.com\r\nUser-Agent: app\r\nAccept: */*\r\n\r\n";
                        outputStream.write(request.getBytes());
                        outputStream.flush();
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            //sb.append(line).append('\n');
                            String finalLine = line;
                            runOnUiThread(() -> homeText.append(finalLine));
                        }
                        String result = sb.toString();
                        //runOnUiThread(() -> homeText.setText(result));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        });

    }
}