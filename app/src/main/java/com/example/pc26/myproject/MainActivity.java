package com.example.pc26.myproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    Button b1,b2;
    TextView t3;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("User Registration");
        e1 = (EditText)findViewById(R.id.et1);
        e2 = (EditText)findViewById(R.id.et2);
        b1 = (Button)findViewById(R.id.btn1);
        b2 = (Button)findViewById(R.id.btn2);
        t3 = (TextView)findViewById(R.id.tv3);
        mFirebaseAuth =  FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = e1.getText().toString();
                String b = e2.getText().toString();
                if (a.isEmpty()) {
                    e1.setError("Please enter user name");
                    e1.requestFocus();
                }
                else if (b.isEmpty())
                {
                    e2.setError("Please enter Password ");
                    e2.requestFocus();
                }
                else if (a.isEmpty() && b.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Feilds are Empty", Toast.LENGTH_SHORT).show();
                }
                else if (!a.isEmpty() && !b.isEmpty())
                {
                  mFirebaseAuth.createUserWithEmailAndPassword(a,b).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>()
                  {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                         // Log.e("www"," THIS IS ME " +task.getResult());
                          if(task.isSuccessful() )
                          {
                              Intent i = new Intent(MainActivity.this,HomeActivity.class);
                              startActivity(i);
                              finish();
                          }
                          else
                          {
                              Toast.makeText(MainActivity.this, "Sign in Unsuccesful ,Please try Again", Toast.LENGTH_SHORT).show();

                          }
                      }
                  });
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Error Occured!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = e1.getText().toString();
                String b = e2.getText().toString();
                e1.setText("  ");
                e2.setText("  ");


            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
                finish();

            }
        });
      /*  ArrayList<Character> l1 =new ArrayList<>();
        l1.add('q');
        l1.add('e');
        l1.add('d');
        for(Character i : l1)
        {

            System.out.print("detail is"+ i);
        }*/
    }
}
