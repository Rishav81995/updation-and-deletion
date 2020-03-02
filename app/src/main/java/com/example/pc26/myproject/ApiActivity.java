package com.example.pc26.myproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ApiActivity extends AppCompatActivity {
    Button b;
    Users users;
   public static TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        b = (Button)findViewById(R.id.btn1);
        data = (TextView)findViewById(R.id.tv1);
         /*users = new Users();
        users.setAge("23");
        users.setName("Ram");
        users.setSalary("6000");*/
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     FetchData process = new FetchData();
                     process.execute();
            }
        });

    }
}
