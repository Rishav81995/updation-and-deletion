package com.example.pc26.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5;
    Button b1,b2;
    EditText e1,e2,e3,e4,e5;
    UserDetailsposo userDetailsposo;
    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase database ;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        e1 = (EditText) findViewById(R.id.et1);
        e2 = (EditText) findViewById(R.id.et2);
        e3 = (EditText) findViewById(R.id.et3);
        e4 = (EditText) findViewById(R.id.et4);
        e5 = (EditText) findViewById(R.id.et5);
        b1 = (Button)findViewById(R.id.btn1);
        b2 = (Button)findViewById(R.id.btn2);
        mFirebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("users");

       Intent intent= getIntent();
      final UserDetailsposo  userDetailsposo= (UserDetailsposo) intent.getExtras().get("kk");
        Log.e("kk","delhi"+userDetailsposo.getKey());
         e1.setText(""+userDetailsposo.getName());
        e3.setText(""+userDetailsposo.getPhone_no());
        e2.setText(""+userDetailsposo.getAddress());
        e4.setText(""+userDetailsposo.getGender());
        e5.setText(""+userDetailsposo.getCourse());
        //final String key = ref.getKey();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String,Object> users = new HashMap<>();
                users.put("name",e1.getText().toString());
                users.put("address",e2.getText().toString());
                users.put("phone_no",e3.getText().toString());
                users.put("course",e5.getText().toString());
                users.put("gender",e4.getText().toString());

                ref.child(userDetailsposo.getKey()).updateChildren(users);
                Toast.makeText(getApplicationContext(),"Data Updated",Toast.LENGTH_SHORT).show();


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdateActivity.this,RecyclerActivity.class);
                startActivity(i);
            }
        });
    }
}
