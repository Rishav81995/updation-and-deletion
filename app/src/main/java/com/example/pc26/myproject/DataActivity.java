package com.example.pc26.myproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataActivity extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5;
    Button b1,b2,b3,b4;
    EditText e1,e2,e3;
    RadioButton r1,r2;
    Spinner s1;
    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase database ;
    DatabaseReference ref;
   // private ProgressBar progressBar;
    //private int progressStatus = 0;
    //private TextView textView;
    //private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        mFirebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("user registration ");
        e1 = (EditText) findViewById(R.id.et1);
        e2 = (EditText) findViewById(R.id.et2);
        e3 = (EditText) findViewById(R.id.et3);
        b1 = (Button)findViewById(R.id.btn1);
       // Log.e("ttt",""+b1.getBackground()+"  "+b1.getBackground().getColorFilter());
        b2 = (Button)findViewById(R.id.btn2);
        b3 = (Button)findViewById(R.id.btn3);
        r1 = (RadioButton) findViewById(R.id.rb1);
        r2 = (RadioButton)findViewById(R.id.rb2);
        t4 = (TextView)findViewById(R.id.tv4);
        s1 = (Spinner)findViewById(R.id.spinner);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("JAVA");
        arrayList.add("ANDROID");
        arrayList.add("C Language");
        arrayList.add("CPP Language");
        arrayList.add("Python");
        arrayList.add("Machine Learning");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(arrayAdapter);
        r1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    r2.setVisibility(View.GONE);
                    r1.setVisibility(View.VISIBLE);
                }else
                {
                    r2.setVisibility(View.VISIBLE);
                    r1.setVisibility(View.GONE);
                }
            }
        });
        r2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    r2.setVisibility(View.GONE);
                    r1.setVisibility(View.VISIBLE);
                }else
                {
                    r2.setVisibility(View.VISIBLE);
                    r1.setVisibility(View.GONE);
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String a= e1.getText().toString();
                String b= e2.getText().toString();
                String c= e3.getText().toString();
                e1.setText(" ");
                e2.setText(" ");
                e3.setText(" ");
                if(r1.isChecked())
                {
                    r1.setEnabled(false);

                }
                else if(r2.isChecked())
                {
                    r2.setEnabled(false);
                }



            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a= e1.getText().toString();
                String b= e2.getText().toString();
                String c= e3.getText().toString();
                if (a.isEmpty()) {
                    e1.setError("Please enter Name");
                    e1.requestFocus();
                }
                else if (b.isEmpty())
                {
                    e2.setError("Please enter Address ");
                    e2.requestFocus();
                }
                else if (c.isEmpty())
                {
                    e3.setError("Please enter Phone No ");
                    e3.requestFocus();
                }
                else if (a.isEmpty() && b.isEmpty() && c.isEmpty())
                {
                    Toast.makeText(DataActivity.this, "Feilds are Empty", Toast.LENGTH_SHORT).show();
                }
                else if (!a.isEmpty() && !b.isEmpty() && !c.isEmpty())
                {

                    DatabaseReference usersRef = ref.child("users").push();
                    Map<String,String> users = new HashMap<>();
                    users.put("name",e1.getText().toString());
                    users.put("address",e2.getText().toString());
                    users.put("phone_no",e3.getText().toString());
                    users.put("course",s1.getSelectedItem().toString());
                    if(r1.isChecked()){
                        users.put("gender","male");
                    }else {
                        users.put("gender","female");
                    }


                    usersRef.setValue(users);

                }
                Toast.makeText(DataActivity.this," Saved",Toast.LENGTH_LONG).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  ref.child("users").child("").removeValue();
                Intent intent = new Intent(DataActivity.this,RecyclerActivity.class);
                startActivity(intent);
                ProgressDialog progressBar = new ProgressDialog(DataActivity.this);
             //   progressBar.setCancelable(true);//you can cancel it by pressing back button
                progressBar.setMessage(" Please wait ...");
               // progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
               // progressBar.setProgress(0);//initially progress is 0
               // progressBar.setMax(100);//sets the maximum value 100
               progressBar.show();


              /*  DatabaseReference usersRef = ref.child("users");
                usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("rrr", "onCancelled", databaseError.toException());

                    }
                });*/

            }

        });

    }
}
