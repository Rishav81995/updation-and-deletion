
package com.example.pc26.myproject;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Random;

public class Otp extends AppCompatActivity {
    EditText e1, e2, e3;
    Button b1, b2;
    int randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        e1 = (EditText) findViewById(R.id.et1);
        e2 = (EditText) findViewById(R.id.et2);
        e3 = (EditText) findViewById(R.id.et3);
        b1 = (Button) findViewById(R.id.btn1);
        b2 = (Button) findViewById(R.id.btn2);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String apikey = "apikey=" + "KDBCJEznFl8-qLHqQIirboP8DrqcA9qTOXXLH144Te";
                    Random random = new Random();
                    randomNumber = random.nextInt(999999);
                    String message = "&message=" + "hey" + e1.getText().toString() + "your otp is" + randomNumber;
                   // String sender = "&sender=" + "Rishav Gupta";
                    String numbers = "&numbers=" + e2.getText().toString();
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                    String data = apikey + numbers + message ;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                        rd.close();

                    Toast.makeText(getApplicationContext(), "OTP send Sucessfully", Toast.LENGTH_LONG).show();
                    Log.e("hhh","hello"+stringBuffer.toString());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error Sms" + e, Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Error" + e, Toast.LENGTH_LONG).show();
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(randomNumber==Integer.valueOf(e3.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "you are logged in sucessfully", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Wrong OTP", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

